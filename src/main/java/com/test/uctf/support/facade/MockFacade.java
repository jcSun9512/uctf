package com.test.uctf.support.facade;

import com.test.uctf.common.TestContext;
import com.test.uctf.common.TestRuntime;
import com.test.uctf.modal.MockCase;
import com.test.uctf.modal.MockModel;
import com.test.uctf.standart.BasePath;
import com.test.uctf.util.YamlUtil;
import org.apache.log4j.Logger;
import org.mockito.Mockito;
import org.springframework.aop.framework.Advised;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
  * @author jiacai.sjc
  * @version $Id: MockFacade.java, v 0.1 2017-05-23 下午12:49 jiacai.sjc Exp $$
  */
public class MockFacade {
    private static final Logger LOGGER = Logger.getLogger(MockFacade.class);

    public static <T> T mock(String beanId, String fieldName) {
        Object bean = TestRuntime.getTestContext().getBean(beanId);
        Field field = ReflectionUtils.findField(bean.getClass(), fieldName);
        Class<?> attributeClazz = field.getType();
        T mock = (T)Mockito.mock(attributeClazz);
        replace(bean, field, mock);
        return mock;
    }

    public static MockModel getMockModel(String path, String key, Map<String, Object> params) {
        if(params == null) params = new HashMap<String, Object>();
        path = BasePath.TEST_RESOURCES + path;
        MockModel mockModel = YamlUtil.loadYaml(path, key, params);
        return mockModel;
    }

    public static void executeMock(MockModel mockModel) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = classLoader.loadClass(mockModel.getFacadeClass());
            Object mock = mock(mockModel.getBeanId(), mockModel.getFacadeName());
            Method method = null;
            int size = mockModel.getParamList() == null ? 0 : mockModel.getParamList().size();
            Class[] params = new Class[size];
            if(size > 0 ) {
                for(int i = 0; i < size; i++) {
                    String temp = mockModel.getParamList().get(i);
                    params[i] = classLoader.loadClass(temp);
                }
            }
            method = ReflectionUtils.findMethod(clazz, mockModel.getMethodName(), params);

            Mockito.when(method.invoke(mock, params)).thenReturn(mockModel.getResult());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error(String.format("mock失败, mockModel = %s", mockModel));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void replace(Object bean, Field field, Object mock) {
        Object targetBean = bean;
        while(targetBean instanceof Advised) {
            try {
                Object target = ((Advised) targetBean).getTargetSource().getTarget();
                if(target == targetBean) break;
                else targetBean = target;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(String.format("获取bean源对象错误, bean=[%s]", bean.getClass()));
            }
        }

        bean = targetBean;

        field.setAccessible(true);
        try {
            Object obj = field.get(bean);
            field.set(bean, mock);

            TestContext testContext = TestRuntime.getTestContext();
            MockCase mockCase = new MockCase(bean, field, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
    }

}
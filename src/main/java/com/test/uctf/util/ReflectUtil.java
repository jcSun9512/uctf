package com.test.uctf.util;

import com.test.uctf.modal.Component;
import org.apache.log4j.Logger;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: ReflectUtil.java, v 0.1 2017-05-20 下午3:44 jiacai.sjc Exp $$
  */
public class ReflectUtil {
    private static final Logger LOGGER = Logger.getLogger(ReflectUtil.class);

    public static String[] getParamsName(Method method) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] params = u.getParameterNames(method);
        return params;
    }

    public static Object getFieldValue(Object obj, String filedName) {
        if(filedName == null) return null;
        if(filedName.contains(".")) {
            int index = filedName.indexOf(".");
            String key1 = filedName.substring(0, index);
            String key2 = filedName.substring(index, filedName.length());
            return getFieldValue(getFieldValue(obj, key1), key2);
        }

        Field field = ReflectionUtils.findField(obj.getClass(), filedName);
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } finally {
            field.setAccessible(false);
        }
    }

    public static Class<?>[] getParamsType(Method method) {
        return method.getParameterTypes();
    }


    public static <T> T invoke(Component component, Map<String, Object> params, Object... args) throws Exception {

        try {
            Class<?> clazz = component.getClazz();
            Method method = component.getMethod();
            if (params == null) params = new HashMap<String, Object>();
            String[] paramsName = getParamsName(method);
            if (paramsName.length == 0) {
                return (T)method.invoke(clazz.newInstance());

            }

            List<Object> argList = new ArrayList<Object>();
            if(args != null) {
                for(Object arg : args) argList.add(arg);
            }
            int index = 0;

            List<Object> list = new ArrayList<Object>();
            for (String param : paramsName) {
                if(index < argList.size()) {
                    list.add(argList.get(index));
                    index++;
                } else {
                    list.add(params.get(param));
                }
            }
            return (T)method.invoke(clazz.newInstance(), list.toArray());
        } catch (Exception e) {
            LOGGER.error(String.format("调用组件失败，组件Id=[%s], 组件路径=[%s]", component.getId(), component.getPath()));
            throw e;
        }
    }
}
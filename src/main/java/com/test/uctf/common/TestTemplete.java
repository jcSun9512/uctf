package com.test.uctf.common;

import com.test.uctf.modal.Component;
import com.test.uctf.modal.MockCase;
import com.test.uctf.modal.TestCase;
import com.test.uctf.util.ReflectUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: TestTemplete.java, v 0.1 2017-04-24 下午5:37 jiacai.sjc Exp $$
  */
public class TestTemplete {
    private static final Logger LOGGER = Logger.getLogger(TestTemplete.class);

    public static void executeTestCase(TestContext fatherContext, TestCase testCase) throws Exception {
        LOGGER.info(String.format("测试开始，dataId=[%s], logicId=[%s], description=[%s]",
                testCase.getDataId(), testCase.getLogicId(), testCase.getDescription()));
        TestContext testContext = new TestContext();
        TestRuntime.setTestContext(testContext);

        testContext.setFatherContext(fatherContext);
        testContext.setTestCase(testCase);

        testContext.setAllAttribute(testCase.getDataSource());
        testContext.setAttribute("testContext", testContext);

        invokeComponent(testCase.getLogicId());

        clearAction(testContext);
        LOGGER.info("测试结束\n---------------------------------------------------------------------------------\n");
    }

    public static <T> T invokeComponent(String id, Object... args) throws Exception {
        Component component = TestRuntime.getComponentFactory().getComponent(id);
        if(component == null) {
            LOGGER.error(String.format("组件不存在，组件Id=[%s]", id));
            return null;
        }
        TestContext testContext = TestRuntime.getTestContext();
        LOGGER.info(String.format("component=[%s]开始执行。", component.getProcess()));
        T result = ReflectUtil.invoke(component, testContext.getAttributes(), args);
        LOGGER.info(String.format("component=[%s]执行结束。", component.getProcess()));
        return result;
    }

    public static void invokeTestCase(String caseId, Map<String, Object> params) throws Exception {
        TestContext fatherContext = TestRuntime.getTestContext();
        List<TestCase> testCaseList = TestRuntime.getTestCaseList();
        TestCase testCase = null;
        for(int i = 0; testCaseList != null && i < testCaseList.size(); i++) {
            if(testCaseList.get(i).getCaseId().equals(caseId)) {
                testCase = testCaseList.get(i);
                testCase = testCase.reLoad();
                break;
            }
        }

        if(testCase == null) {
            LOGGER.error(String.format("依赖testCase失败，case不存在，caseId=[%s]", caseId));
            return;
        }

        LOGGER.info(String.format("依赖testcase，dataId=[%s], logicId=[%s], description=[%s]",
                testCase.getDataId(), testCase.getLogicId(), testCase.getDescription()));

        TestContext testContext = new TestContext();
        TestRuntime.setTestContext(testContext);

        testContext.setFatherContext(fatherContext);
        testContext.setTestCase(testCase);

        testContext.setAllAttribute(testCase.getDataSource());
        testContext.getAttributes().putAll(params);
        testContext.setAttribute("testContext", testContext);

        invokeComponent(testCase.getLogicId());

        clearAction(testContext);
        LOGGER.info("依赖结束");

        testContext = testContext.getFatherContext();
        TestRuntime.setTestContext(testContext);
    }

    private static void clearAction(TestContext testContext) {
        List<MockCase> list = testContext.getMockRepertory();
        testContext.setMockRepertory(new ArrayList<MockCase>());

        for(MockCase mockCase : list) {
            Object bean = mockCase.getBean();
            Field field = mockCase.getField();
            Object value = mockCase.getValue();
            field.setAccessible(true);
            try {
                field.set(bean, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
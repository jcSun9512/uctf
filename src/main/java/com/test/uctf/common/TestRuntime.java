package com.test.uctf.common;

import com.test.uctf.modal.Component;
import com.test.uctf.modal.GeneralConfig;
import com.test.uctf.modal.TestCase;
import com.test.uctf.standart.BasePath;
import com.test.uctf.util.YamlUtil;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
  * @author jiacai.sjc
  * @version $Id: TestRuntime.java, v 0.1 2017-04-24 下午5:07 jiacai.sjc Exp $$
  */
public class TestRuntime {
    private static final Logger LOGGER = Logger.getLogger(TestRuntime.class);

    private static ApplicationContext applicationContext;

    private static TestContext testContext;

    private static GeneralConfig generalConfig;

    private static ComponentFactory componentFactory;

    private static List<TestCase> testCaseList;


    private TestRuntime() {}


    public static void startTestRuntime() {
        defaultLoadConfig();
        defaultLoadApplication();
        defaultLoadComponent();
        defaultLoadTestData();
    }

    private static void defaultLoadConfig() {
        try {
            LOGGER.info("加载配置文件, 配置文件路径：" + BasePath.generalConfigPath);
            Properties properties = new Properties();
            FileInputStream read = new FileInputStream(BasePath.generalConfigPath);
            properties.load(read);

            GeneralConfig config = new GeneralConfig();
            String value = properties.getProperty("dataPath");
            if(value != null) config.setDataPath(BasePath.TEST_RESOURCES + value);

            value = properties.getProperty("componentPath");
            if(value != null) config.setComponentPath(BasePath.TEST_JAVA + value);

            value = properties.getProperty("userApplicationPath");
            if(value != null) config.setUserApplicationPath(value);

            TestRuntime.setGeneralConfig(config);

            read.close();

            LOGGER.info(String.format("加载配置文件完成.\n确认配置文件:\ndataPath: %s\ncomponentPath: %s\n",
                    generalConfig.getDataPath(), generalConfig.getComponentPath()));
        } catch (FileNotFoundException e) {
            LOGGER.debug("配置文件不存在", e);
        } catch (IOException e) {
            LOGGER.debug("配置文件读取失败", e);
        } finally {
            if(TestRuntime.getGeneralConfig() == null) {
                TestRuntime.setGeneralConfig(new GeneralConfig());
            }
        }

    }

    private static void defaultLoadApplication() {
        ApplicationContext context = new ClassPathXmlApplicationContext(BasePath.defaultApplicationPath, generalConfig.getUserApplicationPath());
        setApplicationContext(context);
    }

    private static void defaultLoadComponent() {
        componentFactory = new ComponentFactory();
        componentFactory.load(generalConfig.getComponentPath());
    }

    private static void defaultLoadTestData() {
        String path = generalConfig.getDataPath();
        testCaseList = new ArrayList<TestCase>();
        LOGGER.info("加载自定义TestCase, 扫描文件路径: " + path);
        File baseFile = new File(path);
        loadFileTree(baseFile);

        for(Component component : componentFactory.getAloneComponent()) {
            TestCase testCase = new TestCase();
            testCase.setDataId("");
            testCase.setDataSource(new HashMap<String, Object>());
            testCase.setLogicId(component.getId());
            testCase.setPath(null);
            testCaseList.add(testCase);
        }

        LOGGER.info("加载自定义TestCase完成");

        LOGGER.info("通过TestCase和Logic组件进行预检");
        precheck();
        LOGGER.info("预检完成");
    }

    private static void loadFileTree(File file) {
        if(!file.exists()) return;
        if(file.isFile() && file.getName().endsWith(".yaml")) {
            try{
                List<TestCase> list = YamlUtil.loadTestCase(file);
                for(TestCase testCase : list) {
                    testCase.setPath(file.getAbsolutePath());
                    testCaseList.add(testCase);
                }
            } catch (Exception e) {
                //在加载testcase的时候忽略加载文件错误
                LOGGER.warn("解析yaml文件错误，文件路径: " + file.getName());
            }
        }
        File[] list = file.listFiles();
        if(list == null) return;
        for(File child : list) {
            loadFileTree(child);
        }
    }

    private static void precheck() {
        List<Component> logicList = componentFactory.getLogicList();
        for(TestCase testCase : testCaseList) {
            String logicId = testCase.getLogicId();
            for(Component component : logicList) {
                if(component.getId().equals(logicId)){
                    testCase.setLogic(component);
                    String caseId = testCase.getDataId() + "#" + testCase.getLogicId();
                    testCase.setCaseId(caseId);
                    break;
                }
            }
        }
    }

    /**
     * Getter method for property testContext.
     *
     * @return property value of testContext
     */
    public static TestContext getTestContext() {
        return testContext;
    }

    /**
     * Getter method for property generalConfig.
     *
     * @return property value of generalConfig
     */
    public static GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    /**
     * Getter method for property applicationContext.
     *
     * @return property value of applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Getter method for property componentFactory.
     *
     * @return property value of componentFactory
     */
    public static ComponentFactory getComponentFactory() {
        return componentFactory;
    }

    /**
     * Getter method for property testCaseList.
     *
     * @return property value of testCaseList
     */
    public static List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    /**
     * Setter method for property testContext.
     *
     * @param testContext value to be assigned to property testContext
     */
    public static void setTestContext(TestContext testContext) {
        TestRuntime.testContext = testContext;
    }

    /**
     * Setter method for property generalConfig.
     *
     * @param generalConfig value to be assigned to property generalConfig
     */
    public static void setGeneralConfig(GeneralConfig generalConfig) {
        TestRuntime.generalConfig = generalConfig;
    }

    /**
     * Setter method for property applicationContext.
     *
     * @param applicationContext value to be assigned to property applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        TestRuntime.applicationContext = applicationContext;
    }

    /**
     * Setter method for property componentFactory.
     *
     * @param componentFactory value to be assigned to property componentFactory
     */
    public static void setComponentFactory(ComponentFactory componentFactory) {
        TestRuntime.componentFactory = componentFactory;
    }

    /**
     * Setter method for property testCaseList.
     *
     * @param testCaseList value to be assigned to property testCaseList
     */
    public static void setTestCaseList(List<TestCase> testCaseList) {
        TestRuntime.testCaseList = testCaseList;
    }
}
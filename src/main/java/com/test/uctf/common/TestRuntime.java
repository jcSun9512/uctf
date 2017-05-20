package com.test.uctf.common;

import com.test.uctf.modal.GeneralConfig;
import com.test.uctf.standart.BasePath;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    private TestRuntime() {}


    public static void startTestRuntime() {
        defaultLoadApplication();
        defaultLoadConfig();
        defaultLoadComponent();
        defaultLoadTestData();
    }

    private static void defaultLoadApplication() {
        ApplicationContext context = new ClassPathXmlApplicationContext(BasePath.defaultApplicationPath);
        setApplicationContext(context);
    }

    private static void defaultLoadConfig() {
        try {
            Properties properties = new Properties();
            FileInputStream read = new FileInputStream(BasePath.generalConfigPath);
            properties.load(read);

            GeneralConfig config = new GeneralConfig();
            String value = properties.getProperty("dataPath");
            if(value != null) config.setDataPath(BasePath.TEST_RESOURCES + value);

            value = properties.getProperty("componentPath");
            if(value != null) config.setComponentPath(BasePath.TEST_JAVA + value);

            TestRuntime.setGeneralConfig(config);

            read.close();
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

    private static void defaultLoadComponent() {
        componentFactory = (ComponentFactory) applicationContext.getBean("componentFactory");
        componentFactory.load(generalConfig.getComponentPath());
    }

    private static void defaultLoadTestData() {
        
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
}
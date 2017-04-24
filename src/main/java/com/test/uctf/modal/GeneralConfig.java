package com.test.uctf.modal;

import com.test.uctf.standart.BasePath;

/**
  * @author jiacai.sjc
  * @version $Id: GeneralConfig.java, v 0.1 2017-04-24 下午4:49 jiacai.sjc Exp $$
  */
public class GeneralConfig {
    private String dataPath = BasePath.defaultDataPath;

    private String componentPath = BasePath.defaultComponentPath;

    public GeneralConfig() {}

    /**
     * Getter method for property dataPath.
     *
     * @return property value of dataPath
     */
    public String getDataPath() {
        return dataPath;
    }

    /**
     * Getter method for property componentPath.
     *
     * @return property value of componentPath
     */
    public String getComponentPath() {
        return componentPath;
    }

    /**
     * Setter method for property dataPath.
     *
     * @param dataPath value to be assigned to property dataPath
     */
    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Setter method for property componentPath.
     *
     * @param componentPath value to be assigned to property componentPath
     */
    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }
}
package com.test.uctf.modal;

import com.test.uctf.standart.BasePath;

import java.util.ArrayList;
import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: GeneralConfig.java, v 0.1 2017-04-24 下午4:49 jiacai.sjc Exp $$
  */
public class GeneralConfig {
    private String dataPath = BasePath.defaultDataPath;

    private String componentPath = BasePath.defaultComponentPath;

    private String userApplicationPath = BasePath.defaultUserApplicationPath;

    private List<DBConfig> dbConfigList = new ArrayList<DBConfig>();

    public GeneralConfig(){}

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
     * Getter method for property userApplicationPath.
     *
     * @return property value of userApplicationPath
     */
    public String getUserApplicationPath() {
        return userApplicationPath;
    }

    /**
     * Getter method for property dbConfigList.
     *
     * @return property value of dbConfigList
     */
    public List<DBConfig> getDbConfigList() {
        return dbConfigList;
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

    /**
     * Setter method for property userApplicationPath.
     *
     * @param userApplicationPath value to be assigned to property userApplicationPath
     */
    public void setUserApplicationPath(String userApplicationPath) {
        this.userApplicationPath = userApplicationPath;
    }

    /**
     * Setter method for property dbConfigList.
     *
     * @param dbConfigList value to be assigned to property dbConfigList
     */
    public void setDbConfigList(List<DBConfig> dbConfigList) {
        this.dbConfigList = dbConfigList;
    }
}
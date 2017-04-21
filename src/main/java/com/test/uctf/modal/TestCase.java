package com.test.uctf.modal;

import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: TestCase.java, v 0.1 2017-04-21 下午1:47 jiacai.sjc Exp $$
  */
public class TestCase {

    private String dataId;

    private String logicId;

    private Map<String, Object> dataSource;

    public TestCase() {}

    /**
     * Getter method for property dataId.
     *
     * @return property value of dataId
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * Getter method for property logicId.
     *
     * @return property value of logicId
     */
    public String getLogicId() {
        return logicId;
    }

    /**
     * Getter method for property dataSource.
     *
     * @return property value of dataSource
     */
    public Map<String, Object> getDataSource() {
        return dataSource;
    }

    /**
     * Setter method for property dataId.
     *
     * @param dataId value to be assigned to property dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    /**
     * Setter method for property logicId.
     *
     * @param logicId value to be assigned to property logicId
     */
    public void setLogicId(String logicId) {
        this.logicId = logicId;
    }

    /**
     * Setter method for property dataSource.
     *
     * @param dataSource value to be assigned to property dataSource
     */
    public void setDataSource(Map<String, Object> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "dataId='" + dataId + '\'' +
                ", logicId='" + logicId + '\'' +
                ", dataSource=" + dataSource +
                '}';
    }
}
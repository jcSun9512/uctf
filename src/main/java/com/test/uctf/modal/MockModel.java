package com.test.uctf.modal;

import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: MockModel.java, v 0.1 2017-05-22 下午3:53 jiacai.sjc Exp $$
  */
public class MockModel {

    private String beanId;

    private String facadeName;

    private String facadeClass;

    private String methodName;

    private List<String> paramList;

    private Object result;

    public MockModel(){};

    /**
     * Getter method for property beanId.
     *
     * @return property value of beanId
     */
    public String getBeanId() {
        return beanId;
    }

    /**
     * Getter method for property facadeName.
     *
     * @return property value of facadeName
     */
    public String getFacadeName() {
        return facadeName;
    }

    /**
     * Getter method for property facadeClass.
     *
     * @return property value of facadeClass
     */
    public String getFacadeClass() {
        return facadeClass;
    }

    /**
     * Getter method for property methodName.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Getter method for property paramList.
     *
     * @return property value of paramList
     */
    public List<String> getParamList() {
        return paramList;
    }

    /**
     * Getter method for property result.
     *
     * @return property value of result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Setter method for property beanId.
     *
     * @param beanId value to be assigned to property beanId
     */
    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    /**
     * Setter method for property facadeName.
     *
     * @param facadeName value to be assigned to property facadeName
     */
    public void setFacadeName(String facadeName) {
        this.facadeName = facadeName;
    }

    /**
     * Setter method for property facadeClass.
     *
     * @param facadeClass value to be assigned to property facadeClass
     */
    public void setFacadeClass(String facadeClass) {
        this.facadeClass = facadeClass;
    }

    /**
     * Setter method for property methodName.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Setter method for property paramList.
     *
     * @param paramList value to be assigned to property paramList
     */
    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    /**
     * Setter method for property result.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MockModel{" +
                "beanId='" + beanId + '\'' +
                ", facadeName='" + facadeName + '\'' +
                ", facadeClass='" + facadeClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramList=" + paramList +
                ", result=" + result +
                '}';
    }
}
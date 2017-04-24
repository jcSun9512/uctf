package com.test.uctf.common;


import com.test.uctf.modal.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: TestContext.java, v 0.1 2017-04-24 下午4:56 jiacai.sjc Exp $$
  */
public class TestContext {

    private Map<String, Object> context;

    private TestCase testCase;

    private TestContext fatherContext;


    public TestContext() {
        context = new HashMap<String, Object>();
        testCase = null;
        fatherContext = null;
    }

    public <T> T getAttribute(String key) {
        return (T) context.get(key);
    }

    public Map<String, Object> getAttributes() {
        return context;
    }

    public void setAttribute(String key, Object value) {
        context.put(key, value);
    }

    /**
     * Getter method for property context.
     *
     * @return property value of context
     */
    public Map<String, Object> getContext() {
        return context;
    }

    /**
     * Getter method for property fatherContext.
     *
     * @return property value of fatherContext
     */
    public TestContext getFatherContext() {
        return fatherContext;
    }

    /**
     * Getter method for property testCase.
     *
     * @return property value of testCase
     */
    public TestCase getTestCase() {
        return testCase;
    }

    /**
     * Setter method for property context.
     *
     * @param context value to be assigned to property context
     */
    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    /**
     * Setter method for property fatherContext.
     *
     * @param fatherContext value to be assigned to property fatherContext
     */
    public void setFatherContext(TestContext fatherContext) {
        this.fatherContext = fatherContext;
    }

    /**
     * Setter method for property testCase.
     *
     * @param testCase value to be assigned to property testCase
     */
    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
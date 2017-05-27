package com.test.uctf.modal;

import com.test.uctf.standart.annotation.Mock;

import java.lang.reflect.Field;

/**
  * @author jiacai.sjc
  * @version $Id: MockCase.java, v 0.1 2017-05-23 下午1:21 jiacai.sjc Exp $$
  */
public class MockCase {

    private Object bean;

    private Field field;

    private Object value;

    private MockCase() {}

    public MockCase(Object bean, Field field, Object value) {
        this.bean = bean;
        this.field = field;
        this.value = value;
    }

    /**
     * Getter method for property bean.
     *
     * @return property value of bean
     */
    public Object getBean() {
        return bean;
    }

    /**
     * Getter method for property field.
     *
     * @return property value of field
     */
    public Field getField() {
        return field;
    }

    /**
     * Getter method for property value.
     *
     * @return property value of value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Setter method for property bean.
     *
     * @param bean value to be assigned to property bean
     */
    public void setBean(Object bean) {
        this.bean = bean;
    }

    /**
     * Setter method for property field.
     *
     * @param field value to be assigned to property field
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * Setter method for property value.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MockCase{" +
                "bean=" + bean +
                ", field=" + field +
                ", value=" + value +
                '}';
    }
}
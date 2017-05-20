package com.test.uctf.modal;

import com.test.uctf.standart.Process;

import java.lang.reflect.Method;

/**
  * @author jiacai.sjc
  * @version $Id: Component.java, v 0.1 2017-04-24 下午5:13 jiacai.sjc Exp $$
  */
public class Component {

    private Process process;

    private String id;

    private String description;

    private Class<?>  clazz;

    private Method method;

    private String path;

    private Component() {}

    public Component(Process process, String id, String description, Class<?> clazz, Method method, String path) {
        this.process = process;
        this.id = id;
        this.description = description;
        this.clazz = clazz;
        this.method = method;
        this.path = path;
    }

    /**
     * Getter method for property process.
     *
     * @return property value of process
     */
    public Process getProcess() {
        return process;
    }

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter method for property description.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for property clazz.
     *
     * @return property value of clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * Getter method for property method.
     *
     * @return property value of method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Getter method for property path.
     *
     * @return property value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter method for property process.
     *
     * @param process value to be assigned to property process
     */
    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Setter method for property description.
     *
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter method for property clazz.
     *
     * @param clazz value to be assigned to property clazz
     */
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Setter method for property method.
     *
     * @param method value to be assigned to property method
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Setter method for property path.
     *
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Component{" +
                "process=" + process +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", clazz=" + clazz +
                ", method=" + method +
                ", path='" + path + '\'' +
                '}';
    }
}
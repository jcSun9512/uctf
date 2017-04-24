package com.test.uctf.modal;

import com.test.uctf.standart.Process;

/**
  * @author jiacai.sjc
  * @version $Id: Component.java, v 0.1 2017-04-24 下午5:13 jiacai.sjc Exp $$
  */
public class Component {

    private Process process;

    private String id;

    private String path;

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
     * Setter method for property path.
     *
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
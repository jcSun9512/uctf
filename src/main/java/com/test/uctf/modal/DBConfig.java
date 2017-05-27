package com.test.uctf.modal;

/**
  * @author jiacai.sjc
  * @version $Id: DBConfig.java, v 0.1 2017-05-27 下午2:59 jiacai.sjc Exp $$
  */
public class DBConfig {

    private String url;

    private String user;

    private String password;

    public DBConfig(){}

    /**
     * Getter method for property url.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Getter method for property user.
     *
     * @return property value of user
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter method for property password.
     *
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property url.
     *
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Setter method for property user.
     *
     * @param user value to be assigned to property user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Setter method for property password.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
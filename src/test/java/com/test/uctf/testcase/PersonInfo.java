package com.test.uctf.testcase;

import org.springframework.beans.factory.annotation.Autowired;

/**
  * @author jiacai.sjc
  * @version $Id: PersonInfo.java, v 0.1 2017-05-21 下午1:26 jiacai.sjc Exp $$
  */
public class PersonInfo {

    @Autowired
    private Person person;

    public Person out() {
        return  person.out();
    }



    public PersonInfo() {}

    /**
     * Getter method for property person.
     *
     * @return property value of person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Setter method for property person.
     *
     * @param person value to be assigned to property person
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
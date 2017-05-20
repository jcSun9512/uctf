/**
 *   * Alipay.com Inc.
 *   * Copyright (c) 2004-2017 All Rights Reserved.
 *   
 */
package com.test.uctf.testcase.component;

import com.test.uctf.standart.annotation.Prepare;
import com.test.uctf.testcase.Person;

/**
  * @author jiacai.sjc
  * @version $Id: TestPrepare.java, v 0.1 2017-05-18 下午2:41 jiacai.sjc Exp $$
  */
public class TestPrepare {

    @Prepare(id = "preparePerson")
    public Person preparePerson() {
        return new Person("jiacai", 23);
    }
}
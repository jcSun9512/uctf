package com.test.uctf.testcase.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.standart.annotation.Prepare;

/**
  * @author jiacai.sjc
  * @version $Id: TestPrepare.java, v 0.1 2017-05-18 下午2:41 jiacai.sjc Exp $$
  */
public class TestPrepare {

    @Prepare(id = "testPersonId")
    public String preparePerson(TestContext testContext, int personId) {
        if(personId == 1) return "success";
        return "fail";
    }

}
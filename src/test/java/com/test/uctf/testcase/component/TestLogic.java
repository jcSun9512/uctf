package com.test.uctf.testcase.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.common.TestTemplete;
import com.test.uctf.standart.annotation.Logic;
import com.test.uctf.testcase.Person;
import com.test.uctf.testcase.PersonInfo;

/**
  * @author jiacai.sjc
  * @version $Id: TestLogic.java, v 0.1 2017-05-20 下午2:16 jiacai.sjc Exp $$
  */
public class TestLogic {

    @Logic(id="testLogicId")
    public void execute(TestContext testContext) throws Exception {

        String name = TestTemplete.invokeComponent("testPersonId");

        TestTemplete.invokeComponent("testMockId");

        PersonInfo info = (PersonInfo) testContext.getBean("personInfo");
        Person actual = info.out();

        testContext.setAttribute("actual", actual);

        TestTemplete.invokeComponent("testCheck", testContext, name);

    }
}
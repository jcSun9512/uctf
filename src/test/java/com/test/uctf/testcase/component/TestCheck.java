package com.test.uctf.testcase.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.common.TestRuntime;
import com.test.uctf.standart.annotation.Check;
import com.test.uctf.support.facade.CheckFacade;
import com.test.uctf.testcase.PersonInfo;
import org.testng.Assert;

import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: TestCheck.java, v 0.1 2017-05-22 下午5:57 jiacai.sjc Exp $$
  */
public class TestCheck {

    @Check(id="testCheck")
    public void check(TestContext testContext, String personId) {
        Map<String, Object> expect = CheckFacade.getExceptionObject("testcase/check.yaml", personId, testContext.getAttributes());
        CheckFacade.check(testContext.getAttribute("actual"), expect);
    }
}
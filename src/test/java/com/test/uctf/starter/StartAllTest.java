package com.test.uctf.starter;

import com.test.uctf.common.TestTemplete;
import com.test.uctf.modal.TestCase;
import org.testng.annotations.Test;

/**
  * @author jiacai.sjc
  * @version $Id: StartAllTest.java, v 0.1 2017-04-21 下午1:37 jiacai.sjc Exp $$
  */
public class StartAllTest extends StarterBase{

    @Test(dataProvider = "buildAllTestCase")
    public void test(TestCase testCase) throws Exception {
        TestTemplete.executeTestCase(null, testCase);
    }

}
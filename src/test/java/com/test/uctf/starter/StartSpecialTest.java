package com.test.uctf.starter;

import com.test.uctf.common.TestTemplete;
import com.test.uctf.modal.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: StartSpecialTest.java, v 0.1 2017-04-21 下午1:38 jiacai.sjc Exp $$
  */
public class StartSpecialTest extends StarterBase{
    @Test(dataProvider = "buildSpecialTestCase")
    public void test(TestCase testCase) throws Exception {
        TestTemplete.executeTestCase(null, testCase);
    }

    @Override
    public List<String> listTestCase() {
        List<String> list = new ArrayList<String>();
        /** 增加需要测试的id */
        list.add("testDataId");

        return list;
    }
}
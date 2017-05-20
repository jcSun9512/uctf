package com.test.uctf.starter;

import com.test.uctf.common.TestRuntime;
import com.test.uctf.modal.TestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: StarterBase.java, v 0.1 2017-04-21 下午1:25 jiacai.sjc Exp $$
  */

public class StarterBase {

    @DataProvider(name="buildAllTestCase")
    public Object[][] buildAllTestCase() {
        List<TestCase> testCaseList = new ArrayList<TestCase>();
        TestCase case1 = new TestCase();
        case1.setDataId("buildAllTestCase1");
        TestCase case2 = new TestCase();
        case2.setDataId("buildAllTestCase2");
        testCaseList.add(case1);
        testCaseList.add(case2);
        return transforIterable(testCaseList);
    }

    @DataProvider(name="buildSpecialTestCase")
    public Object[][] buildSpecialTestCase() {
        List<TestCase> testCaseList = new ArrayList<TestCase>();
        TestCase case1 = new TestCase();
        case1.setDataId("buildSpecialTestCase1");
        TestCase case2 = new TestCase();
        case2.setDataId("buildSpecialTestCase2");
        testCaseList.add(case1);
        testCaseList.add(case2);
        return transforIterable(testCaseList);
    }

    @BeforeClass
    public void startTestRuntime() {
        TestRuntime.startTestRuntime();
    }

    private Object[][] transforIterable(List<TestCase> list) {
        int number = list.size();
        Object[][] objectGroup = new Object[number][];
        for(int i = 0; i < number; i++) {
            objectGroup[i] = new Object[] { list.get(i) };
        }
        return objectGroup;
    }

    public List<String> listTestCase() {
        return new ArrayList<String>();
    }
}
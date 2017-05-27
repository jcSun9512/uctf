package com.test.uctf.starter;

import com.test.uctf.common.TestRuntime;
import com.test.uctf.modal.TestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
        List<TestCase> testCaseList = buildTestCase(null);
        return transforIterable(testCaseList);
    }

    @DataProvider(name="buildSpecialTestCase")
    public Object[][] buildSpecialTestCase() {
        List<TestCase> testCaseList = buildTestCase(listTestCase());
        return transforIterable(testCaseList);
    }

    public List<String> listTestCase() {
        return new ArrayList<String>();
    }

    @BeforeClass
    public void startTestRuntime() {
        TestRuntime.startTestRuntime();
    }

    private List<TestCase> buildTestCase(List<String> caseIdList) {
        List<TestCase> testCaseList = TestRuntime.getTestCaseList();
        if(caseIdList == null) return testCaseList;

        List<TestCase> result = new ArrayList<TestCase>();
        for(String caseId : caseIdList) {
            for(TestCase testCase : testCaseList) {
                if(testCase.getCaseId().equals(caseId)) {
                    result.add(testCase);
                    break;
                }
            }
        }
        return result;
    }

    private Object[][] transforIterable(List<TestCase> list) {
        int number = list.size();
        Object[][] objectGroup = new Object[number][];
        for(int i = 0; i < number; i++) {
            objectGroup[i] = new Object[] { list.get(i) };
        }
        return objectGroup;
    }
}
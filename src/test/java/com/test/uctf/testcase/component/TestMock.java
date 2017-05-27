package com.test.uctf.testcase.component;

import com.test.uctf.common.TestRuntime;
import com.test.uctf.modal.MockModel;
import com.test.uctf.standart.annotation.Mock;
import com.test.uctf.support.facade.MockFacade;
import com.test.uctf.testcase.Person;
import com.test.uctf.testcase.PersonInfo;

/**
  * @author jiacai.sjc
  * @version $Id: TestMock.java, v 0.1 2017-05-21 下午1:28 jiacai.sjc Exp $$
  */
public class TestMock {

    @Mock(id = "testMockId")
    public void mock() {
        MockModel mockModel = MockFacade.getMockModel("testcase/mock.yaml", "success", TestRuntime.getTestContext().getAttributes());
        MockFacade.executeMock(mockModel);
    }
}
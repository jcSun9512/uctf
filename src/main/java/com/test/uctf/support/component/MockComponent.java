package com.test.uctf.support.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.modal.MockModel;
import com.test.uctf.standart.annotation.Mock;
import com.test.uctf.support.facade.MockFacade;
import org.apache.log4j.Logger;

import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: MockComponent.java, v 0.1 2017-05-23 下午2:29 jiacai.sjc Exp $$
  */
public class MockComponent {
    private static final Logger LOGGER = Logger.getLogger(MockComponent.class);

    @Mock(id="standardMock", description="标准mock组件，通过配置文件进行mock操作")
    public static void mock(TestContext testContext, String mockPath, String mockKey) {
        if(mockPath == null || mockKey == null) {
            LOGGER.error(String.format("指定数据错误，mockPath=[%s]，mockKey=[%s]", mockPath, mockKey));
            return;
        }
        MockModel mockModel = MockFacade.getMockModel(mockPath, mockKey, testContext.getAttributes());
        MockFacade.executeMock(mockModel);
    }

    @Mock(id="standardMultMock", description = "标准mock组件，通过配置文件进行mock操作")
    public static void multMock(TestContext testContext, List<String> mockMultPath, List<String> mockMultKey) {
        if(mockMultPath == null || mockMultKey == null) {
            LOGGER.error(String.format("指定数据错误，mockPath=[%s]，mockKey=[%s]", mockMultPath, mockMultKey));
            return;
        }
        if(mockMultKey.size() == mockMultPath.size()) {
            int size = mockMultKey.size();
            for(int i = 0; i < size; i++) {
                mock(testContext, mockMultPath.get(i), mockMultKey.get(i));
            }

        } else {
            LOGGER.error(String.format("指定数据数目不匹配，mockPath.size=[%s]，mockKey.size=[%s]", mockMultPath.size(), mockMultKey.size()));
            return;
        }

    }
}
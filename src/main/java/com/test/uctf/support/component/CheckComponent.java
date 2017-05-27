package com.test.uctf.support.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.standart.annotation.Check;
import com.test.uctf.support.facade.CheckFacade;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: CheckComponent.java, v 0.1 2017-05-23 下午4:55 jiacai.sjc Exp $$
  */
public class CheckComponent {

    private static final Logger LOGGER = Logger.getLogger(MockComponent.class);

    @Check(id="standardCheck", description="标准check组件，通过配置文件进行检查操作")
    public static void check(TestContext testContext, String actualValue, String checkPath, String checkKey) {
        if(checkPath == null || checkKey == null) {
            LOGGER.error(String.format("指定数据错误，checkPath=[%s]，checkKey=[%s]", checkPath, checkKey));
            return;
        }
        Map<String, Object> map = CheckFacade.getExceptionObject(checkPath, checkKey, testContext.getAttributes());
        CheckFacade.check(actualValue, map);
    }

    @Check(id="standardMultCheck", description = "标准check组件，通过配置文件进行检查操作")
    public static void multCheck(TestContext testContext, List<String> actualMultValue, List<String> checkMultPath, List<String> checkMultKey) {
        if(checkMultPath == null || checkMultKey == null) {
            LOGGER.error(String.format("指定数据错误，checkMultPath=[%s]，checkMultKey=[%s]", checkMultPath, checkMultKey));
            return;
        }
        if(checkMultPath.size() == checkMultKey.size() && checkMultKey.size() == actualMultValue.size()) {
            int size = checkMultPath.size();
            for(int i = 0; i < size; i++) {
                check(testContext, actualMultValue.get(i), checkMultPath.get(i), checkMultKey.get(i));
            }

        } else {
            LOGGER.error(String.format("指定数据数目不匹配，actualMultValue.size=[%s], checkMultPath.size=[%s]，checkMultKey.size=[%s]", actualMultValue.size(), checkMultPath.size(), checkMultKey.size()));
            return;
        }

    }
}
package com.test.uctf.support.component;

import com.test.uctf.common.TestContext;
import com.test.uctf.standart.annotation.Prepare;
import com.test.uctf.support.facade.PrepareFacade;
import org.apache.log4j.Logger;

/**
  * @author jiacai.sjc
  * @version $Id: PrepareComponent.java, v 0.1 2017-05-22 下午4:34 jiacai.sjc Exp $$
  */
public class PrepareComponent {

    private static final Logger LOGGER = Logger.getLogger(PrepareComponent.class);

    @Prepare(id="standardPrepare", description = "标准准备组件，通过配置文件进行数据准备")
    public static <T>T prepare(TestContext testContext, String preparePath, String prepareKey) {
        if(preparePath == null || prepareKey == null) {
            LOGGER.error(String.format("指定数据错误，preparePath=[%s]，prepareKey=[%s]", preparePath, prepareKey));
            return null;
        }
        return (T)PrepareFacade.prepareObject(preparePath, prepareKey, testContext.getAttributes());
    }
}
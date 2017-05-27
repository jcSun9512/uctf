package com.test.uctf.testcase.component;

import com.test.uctf.standart.annotation.Logic;

/**
  * @author jiacai.sjc
  * @version $Id: ALONELogic.java, v 0.1 2017-05-23 下午5:35 jiacai.sjc Exp $$
  */
public class ALONELogic {

    @Logic(id="aloneLogic", alone = true)
    public void testAlone() {
        System.out.println("testAloneLogic");
    }

}
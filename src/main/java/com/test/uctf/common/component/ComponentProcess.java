package com.test.uctf.common.component;

import com.test.uctf.modal.Component;
import com.test.uctf.standart.Process;

import java.lang.reflect.Method;

/**
   * @author jiacai.sjc
   * @version $Id: ComponentProcess.java, v 0.1 2017-05-19 下午5:00 jiacai.sjc Exp $$
   */
public interface ComponentProcess {

    public boolean handle(Process process);

    public boolean handle(Method method);

    public void execute(Component component);

    public Process getProcess();

    public String getId(Method method);

    public String getDescription(Method method);

}
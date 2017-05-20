package com.test.uctf.common.component;

import com.test.uctf.modal.Component;
import com.test.uctf.standart.Process;
import com.test.uctf.standart.annotation.Mock;

import java.lang.reflect.Method;

/**
  * @author jiacai.sjc
  * @version $Id: MockComponent.java, v 0.1 2017-04-24 下午5:42 jiacai.sjc Exp $$
  */
public class MockComponent implements ComponentProcess{

    private final static Process PROCESS = Process.mock;

    public void execute(Component component) {

    }

    public boolean handle(Process process) {
        return process == PROCESS;
    }

    public boolean handle(Method method) {
        return method.isAnnotationPresent(Mock.class);
    }

    public Process getProcess() {
        return PROCESS;
    }

    public String getId(Method method) {
        return method.getAnnotation(Mock.class).id();
    }

    public String getDescription(Method method) {
        return method.getAnnotation(Mock.class).description();
    }

}
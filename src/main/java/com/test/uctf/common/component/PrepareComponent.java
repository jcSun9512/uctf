package com.test.uctf.common.component;

import com.test.uctf.modal.Component;
import com.test.uctf.standart.Process;
import com.test.uctf.standart.annotation.Prepare;

import java.lang.reflect.Method;

/**
  * @author jiacai.sjc
  * @version $Id: PrepareComponent.java, v 0.1 2017-04-24 下午5:41 jiacai.sjc Exp $$
  */
public class PrepareComponent implements ComponentProcess{

    private final static Process PROCESS = Process.prepare;

    public void execute(Component component) {

    }

    public boolean handle(Process process) {
        return process == PROCESS;
    }

    public boolean handle(Method method) {
        return method.isAnnotationPresent(Prepare.class);
    }

    public Process getProcess() {
        return PROCESS;
    }

    public String getId(Method method) {
        return method.getAnnotation(Prepare.class).id();
    }

    public String getDescription(Method method) {
        return method.getAnnotation(Prepare.class).description();
    }
}
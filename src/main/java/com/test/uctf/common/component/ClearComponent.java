package com.test.uctf.common.component;

import com.test.uctf.modal.Component;
import com.test.uctf.standart.Process;
import com.test.uctf.standart.annotation.Clear;

import java.lang.reflect.Method;

/**
  * @author jiacai.sjc
  * @version $Id: ClearComponent.java, v 0.1 2017-04-24 下午5:42 jiacai.sjc Exp $$
  */
public class ClearComponent implements ComponentProcess{

    private final static Process PROCESS = Process.clear;

    public void execute(Component component) {

    }

    public boolean handle(Process process) {
        return process == PROCESS;
    }

    public boolean handle(Method method) {
        return method.isAnnotationPresent(Clear.class);
    }

    public Process getProcess() {
        return PROCESS;
    }

    public String getId(Method method) {
        return method.getAnnotation(Clear.class).id();
    }

    public String getDescription(Method method) {
        return method.getAnnotation(Clear.class).description();
    }
}
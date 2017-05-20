package com.test.uctf.common.component;


import com.test.uctf.modal.Component;
import com.test.uctf.standart.Process;
import com.test.uctf.standart.annotation.Logic;

import java.lang.reflect.Method;

/**
  * @author jiacai.sjc
  * @version $Id: LogicComponent.java, v 0.1 2017-05-18 下午2:52 jiacai.sjc Exp $$
  */
public class LogicComponent implements ComponentProcess{

    private final static Process PROCESS = Process.logic;

    public void execute(Component component) {

    }

    public boolean handle(Process process) {
        return process == PROCESS;
    }

    public boolean handle(Method method) {
        return method.isAnnotationPresent(Logic.class);
    }

    public Process getProcess() {
        return PROCESS;
    }

    public String getId(Method method) {
        return method.getAnnotation(Logic.class).id();
    }

    public String getDescription(Method method) {
        return method.getAnnotation(Logic.class).description();
    }
}
package com.test.uctf.standart.annotation;

import java.lang.annotation.*;

/**
   * @author jiacai.sjc
   * @version $Id: Check.java, v 0.1 2017-05-18 下午2:48 jiacai.sjc Exp $$
   */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Check {

    String id();

    String description() default "";

}
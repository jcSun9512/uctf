package com.test.uctf.standart.annotation;

import java.lang.annotation.*;

/**
   * @author jiacai.sjc
   * @version $Id: Logic.java, v 0.1 2017-05-18 下午2:46 jiacai.sjc Exp $$
   */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logic {

    String id();

    String description() default "";

}
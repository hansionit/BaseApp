package com.hansion.baseapp.model.rxbus;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description：
 * Author: Hansion
 * Time: 2016/12/5 17:15
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    int code() default -1;
    ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;
}

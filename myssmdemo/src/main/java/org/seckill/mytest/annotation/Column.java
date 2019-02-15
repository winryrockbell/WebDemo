package org.seckill.mytest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YH on 2019/1/23.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //运行时的注解

public @interface Column {
    String value();
}

package org.seckill.mytest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YH on 2019/1/23.
 */
//Type表明作用域是类或接口
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) //运行时的注解

public @interface Table {
    String value();
}

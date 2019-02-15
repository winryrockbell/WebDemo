package org.seckill.mytest.reflect;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created by YH on 2019/1/21.
 */
public class ClassDemo2 {
    public static void main(String[] args){
        DefaultListableBeanFactory d = new DefaultListableBeanFactory();
       // ClassUtil.printClassMessage(s1);
       // ClassUtil.getField(s1);
        ClassUtil.getField(d);
    }
}

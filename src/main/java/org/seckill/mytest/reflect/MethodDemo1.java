package org.seckill.mytest.reflect;

import java.lang.reflect.Method;

/**
 * Created by YH on 2019/1/21.
 */
public class MethodDemo1 {
    public static void main(String[] args){
        A a = new A();
        Class c = a.getClass();
        //获取方法，方法根据名称和参数列表决定
        try {
            //Method m = c.getMethod("print",new Class[]{int.class,int.class});
            Method m = c.getMethod("print",int.class,int.class);
            //反射调用方法,返回Object或void
            m.invoke(a,new Object[]{10,20});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class A{
    public void print(int a,int b){
        System.out.println(a+b);
    }
    public void print(String a,String b){
        System.out.println(a.toUpperCase()+","+b.toUpperCase());
    }
}

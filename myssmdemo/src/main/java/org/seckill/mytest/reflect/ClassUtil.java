package org.seckill.mytest.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by YH on 2019/1/21.
 */
public class ClassUtil {

    //获取public方法的信息
    public static void printClassMessage(Object obj){
        Class c = obj.getClass();//传递的是哪个类的对象，c就是对应的类类型
        System.out.println("类名:" + c.getName());
        //获取类的public函数，包括继承的方法
        Method[] ms = c.getMethods();
        for(Method m : ms){
            //得到返回值的类类型
            Class returnType = m.getReturnType();
            System.out.print(returnType.getSimpleName() + " ");
            System.out.print(m.getName() + "(");
            //获取参数的类类型
            Class[] paramTypes = m.getParameterTypes();
            for(Class params : paramTypes){
                System.out.print(params.getName() + ",");
            }
            System.out.println(")");
        }
        //获取自己的所有方法，不管继承与修饰符
        //Method[] ms2 = c.getDeclaredMethods();
    }

    //反射获取类的成员变量
    public static void getField(Object obj){
        Class c = obj.getClass();
        System.out.println("类名:" + c.getName());
        //获取public的成员变量
        //获取自己声明的成员变量
        //c.getDeclaredFields
        Field[] fs = c.getDeclaredFields();
        for(Field field: fs){
            Class fieldType = field.getType();
            //类型名称
            String typeName = fieldType.getSimpleName();
            //成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    //获取构造函数信息
    public static void printConMessage(Object obj){
        Class c = obj.getClass();
        System.out.println("类名:" + c.getName());
        //一样，可获取public，也可以获取自己的
        Constructor[] cs = c.getDeclaredConstructors();
        for(Constructor con :cs){
            System.out.print(con.getName() + "(");
            Class[] params = con.getParameterTypes();
            for(Class param : params){
                System.out.print(param.getSimpleName() + ",");
            }
            System.out.println(")");
        }
    }
}

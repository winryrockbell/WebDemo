package org.seckill.mytest.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by YH on 2019/1/21.
 */
public class MethodDemo2 {
    public static void main(String[] args){
        ArrayList list = new ArrayList();
        ArrayList<String> list1 = new ArrayList<String>();
        Class c1 = list.getClass();
        Class c2 = list1.getClass();
        System.out.println(c1==c2);
        /*打印true，因此可以得出编译之后集合的泛型是去泛型化的
          Java中集合的泛型，是防止错误输入的，仅仅在编译的时候有效。
          运行的时候就没用了，
          验证：反射来绕过编译,反射的操作都是在编译之后的操作
         */
        try {
            Method m = c1.getMethod("add", Object.class);
            m.invoke(list1,100);
            m.invoke(list1,"hello");
            System.out.println(list1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

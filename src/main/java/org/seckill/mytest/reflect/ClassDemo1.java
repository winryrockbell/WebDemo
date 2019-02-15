package org.seckill.mytest.reflect;

/**
 * Created by YH on 2019/1/21.
 */
public class ClassDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        //反射Class的创建方式
        //1.任何一个类都隐藏着一个静态的变量class
       // Class c1 = InnerClass.class;
        //2.通过对象的getClass
       // InnerClass i1 = new InnerClass();
      //  Class c2 = i1.getClass();
        //不管c1 还是c2都是类类型，每个类都是Class的单个对象，因此是相等的
       // System.out.println(c1 == c2);
        //3.
       // Class c3 = null;
       // c3 = Class.forName("org.seckill.mytest.ClassDemo1");
        //我们完全可以通过这个类类型来创建对应类的实例
       /* try {
            InnerClass i2 = (InnerClass)c1.newInstance();//需要无参构造方法
            i2.print();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        Class c1 = InnerClass.class;
        System.out.print(c1.getPackage().getName());
    }
}

class InnerClass{
    void print(){
        System.out.println("66");
    }
};

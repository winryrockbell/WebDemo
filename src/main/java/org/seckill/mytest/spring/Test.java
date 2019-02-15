package org.seckill.mytest.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by YH on 2019/1/21.
 */
public class Test {
    public int a = 1;
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.getBean("someBean");

    }
    public static void main(String[] args){
        String FILE_NAME = "spring/spring-dao.xml";
        System.out.println(Test.class.getClassLoader().getResource(FILE_NAME).getPath());
    }
}

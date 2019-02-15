package org.seckill.mytest.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by YH on 2019/1/23.
 */
public class Test {
    public static void main(String[] args){
        Integer a = Integer.valueOf(6);
    }

    private static String query(Filter f){
        StringBuilder sb = new StringBuilder();
        //获取类类型
        Class c = f.getClass();
        //获取注解信息,是否存在@Table注解
        boolean isFlag = c.isAnnotationPresent(Table.class);
        if(!isFlag){
            return null;
        }else{
            //获取@Table注解实例对象
            Table t = (Table)c.getAnnotation(Table.class);
            String tableName = t.value();
            sb.append("select * from ").append(tableName).append(" where 1=1 ");
            //获取字段注解@Column
            Field[] fields = c.getDeclaredFields();
            for(Field field: fields){
                boolean isExist = field.isAnnotationPresent(Column.class);
                if(!isExist){
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                //上面就拿到了注解的值，也就是类对应的表的值
                String MethodName = "get"+ field.getName().substring(0,1).toUpperCase()+
                        field.getName().substring(1);
                Object value = null;
                try {
                    Method getMethod = c.getMethod(MethodName);
                    value =  getMethod.invoke(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sb.append("and ").append(columnName+"=").append(value);
            }
            return sb.toString();
        }
    }
}

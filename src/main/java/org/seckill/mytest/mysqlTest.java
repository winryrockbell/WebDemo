package org.seckill.mytest;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by YH on 2019/1/10.
 */
public class mysqlTest {
    public static void mysqlTest() {
        String driver = "com.mysql.jdbc.Driver";// 数据库驱动
        String url = "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8";// **指的是数据库名称
        String username = "root";// 数据库用户名
        String password = "123456";// 数据库密码
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);// 加载数据库驱动
            conn = DriverManager.getConnection(url, username, password);// 连接Connection对象
            stat = conn.createStatement();// 创建Statement对象
            String sql = "select * from seckill";
            rs = stat.executeQuery(sql);// 执行sql语句返回结果集
            while (rs.next()) {
                System.out.println(rs.getInt("seckill_id"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        System.out.println(Long.parseLong("1567489412"));
    }
}

package com.pilicon.client.jpa;

import com.pilicon.client.ClientApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC工具类
 * 1 获取connection
 * 2 释放资源
 */
public class JDBCUtil {
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver-class-name}")
    private String driverClass;

    @Value("${jdbc.username}")
    private String user;

    @Value("${jdbc.password}")
    private String password;


    /**
     * 获取connection
     * @return 所获得到的jdbc的connection
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        //不建议把配置硬编码到代码中
        //配置性的写到配置文件中
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://120.27.231.86:3306/blanc_";
        String user =   "root";
        String password = "w4247794";


        Class.forName(driverClass);
        Connection connection=DriverManager.getConnection(url,user,password);
        return connection;
    }
}

package com.edutech.progressive.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class DatabaseConnectionManager {
    private static final Properties properties=new Properties();

    @Autowired
    private static Environment env;


    private static void loadProperties(){
        String url=env.getProperty("spring.datasource.url");
        String username=env.getProperty("spring.datasource.username");
        String password= env.getProperty("spring.datasource.password");

        properties.put("url", url);
        properties.put("username", username);
        properties.put("password",password);

       // properties.load(new FileInputStream("application.properties"));

    }

    public static Connection getConnection()throws SQLException{
        loadProperties();
        return DriverManager.getConnection((String)properties.get("url"),(String)properties.get("username"),(String)properties.get("password"));
    }


}

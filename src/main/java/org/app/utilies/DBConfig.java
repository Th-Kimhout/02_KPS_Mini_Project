package org.app.utilies;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
    public static Connection getConnection () {
        Connection con = null;
        CredentialLoader.loadProperties();
        try {
            con =  DriverManager.getConnection(
                    CredentialLoader.properties.getProperty("db_url"),
                    CredentialLoader.properties.getProperty("db_username"),
                    CredentialLoader.properties.getProperty("db_password"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}

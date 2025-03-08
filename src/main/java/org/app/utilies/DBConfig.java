package org.app.utilies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static Connection con = null;

    public static Connection getConnection() {
        CredentialLoader.loadProperties();
        try {
            con = DriverManager.getConnection(
                    CredentialLoader.properties.getProperty("db_url"),
                    CredentialLoader.properties.getProperty("db_username"),
                    CredentialLoader.properties.getProperty("db_password"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void closedConnection() {
        try {
            DBConfig.con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

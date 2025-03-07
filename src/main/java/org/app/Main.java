package org.app;

import org.app.utilies.DBConfig;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println(DBConfig.getConnection().isValid(5));
        } catch (Exception e) {
        }
    }
}
package org.app;

import org.app.utilies.DBConfig;
import org.app.view.ClientView;

import static org.app.view.ClientView.deleteProduct;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(DBConfig.getConnection().isValid(5));
        } catch (Exception e) {
        }

        ClientView.deleteProduct();
    }
}
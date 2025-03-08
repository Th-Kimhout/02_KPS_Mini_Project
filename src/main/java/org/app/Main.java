package org.app;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.DBConfig;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println(DBConfig.getConnection().isValid(5));
            ProductRepoImp test = new ProductRepoImp();
            test.updateProduct(1,new Product(1,"D",1,1,new Date(1)));
        } catch (Exception e) {
        }
    }
}
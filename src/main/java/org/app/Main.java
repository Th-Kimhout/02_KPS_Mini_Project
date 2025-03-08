package org.app;

import org.app.controller.ProductController;
import org.app.utilies.DBConfig;
import org.app.utilies.Limit_rows;
import org.app.view.ClientView;
import org.app.view.InsertProducts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        ProductController pc = new ProductController();
        InsertProducts.addProduct();



//        ClientView.displayAllProducts();
//
//        ClientView.addNewProduct();

//        System.out.println(Limit_rows.getLimitRows());
//        System.out.print("Set row table");
//
//        Scanner sc = new Scanner(System.in);
//
//        int row = sc.nextInt();
//
//        System.out.println(Limit_rows.updateLimitRow(row));
//
//        System.out.println("Row after update " + Limit_rows.getLimitRows());

    }
}
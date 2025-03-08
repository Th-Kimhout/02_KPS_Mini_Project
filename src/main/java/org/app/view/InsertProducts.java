package org.app.view;

import org.app.controller.ProductController;
import org.app.model.Product;
import org.app.utilies.UserInput;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class InsertProducts {

   static  ProductController productController = new ProductController();
    public static void addProduct() {
        Date date = new Date();
        Scanner sc = new Scanner(System.in);

        int productId = 30;
        UserInput.Input("Enter product name" , "^[a-zA-Z]+$" , "Invalid product name" );

        String productName = sc.nextLine();

        UserInput.Input("Enter price" , "^\\d+(\\.\\d{1,2})?$\n" , "Invalid price" );
        double price = sc.nextDouble();
        UserInput.Input("Enter quantity" , "^\\d+$" , "Invalid quantity" );
        int quantity = sc.nextInt();

        Product product = new Product(productId , productName , price , quantity , java.sql.Date.valueOf(LocalDate.now()) );

       productController.addProduct(product);

    }
}

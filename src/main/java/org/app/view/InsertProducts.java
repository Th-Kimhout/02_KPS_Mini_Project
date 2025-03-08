package org.app.view;

import org.app.controller.ProductController;
import org.app.model.Product;
import org.app.utilies.UserInput;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class InsertProducts {

    public static Product addProduct() {
        Product product = new Product();
        Date date = new Date();
        Scanner sc = new Scanner(System.in);

        int productId = 30;
        UserInput.Input("Enter product name" , "^[a-zA-Z]+$" , "Invalid product name" );

        String productName = sc.nextLine();

        UserInput.Input("Enter price" , "^\\d+(\\.\\d{1,2})?$\n" , "Invalid price" );
        double price = sc.nextDouble();
        UserInput.Input("Enter quantity" , "^\\d+$" , "Invalid quantity" );
        int quantity = sc.nextInt();

        product.setId(productId);
        product.setProduct_name(productName);
        product.setProduct_quantity(quantity);
        product.setProduct_unit_price(price);
        product.setProduct_created_date(java.sql.Date.valueOf(LocalDate.now()));

        return product;
    }
}

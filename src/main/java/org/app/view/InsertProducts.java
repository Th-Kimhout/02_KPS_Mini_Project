package org.app.view;

import org.app.controller.ProductController;
import org.app.model.Product;
import org.app.utilies.UserInput;

import java.time.LocalDate;
import java.util.Scanner;


public class InsertProducts {

   static  ProductController productController = new ProductController();

    static Scanner sc = new Scanner(System.in);
    public static void addProduct(int id ) {


        do{
            String productName = UserInput.Input("Enter product name :", "^[a-zA-Z ]+$", "Invalid product name");

            String price =  UserInput.Input("Enter price :", "^\\d+(\\.\\d{1,2})?$", "Invalid price");

            String quantity = UserInput.Input("Enter quantity :", "^\\d+$", "Invalid quantity");


            System.out.println("Press Enter to continue...");

            sc.nextLine();

            double finalPrice = Double.parseDouble(price);
            int quantityInt = Integer.parseInt(quantity);


            Product product = new Product( id,productName , finalPrice , quantityInt , java.sql.Date.valueOf(LocalDate.now()) );

            productController.addProduct(product);
            break;

        }while (true);

    }
}

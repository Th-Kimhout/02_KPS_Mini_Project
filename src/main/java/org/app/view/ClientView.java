package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.UserInput;
import org.app.utilies.Utility;

import java.util.Scanner;

public class ClientView {

    static ProductController productController = new ProductController();

    public static void displayAllProducts() {
        productController.getAllProducts().forEach(
                (product) -> {
                    System.out.println(product.getProduct_name());
                }
        );
    }

    public static void Display() {
        GetProduct.GetProductByName();
        GetProduct.GetProductById();
    }

    public static void deleteProduct() {
        String productID;

        productID = UserInput.Input("Enter Product ID to delete: ", "^[0-9]+$", "Product ID must be integer. Product ID cannot be empty, text and special character!");
        try {
            productController.deleteProduct(Integer.parseInt(productID));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            deleteProduct();
        }
    }
}




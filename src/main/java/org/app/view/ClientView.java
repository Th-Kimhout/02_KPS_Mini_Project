package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

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

    public static void updateProducts() {
        String productById = UserInput.Input("Input Id to Update : ","^[\\d]+$","Allow Input only Number");
        try{
            productController.updateProduct(Integer.parseInt(productById));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            updateProducts();
        }
    }


}

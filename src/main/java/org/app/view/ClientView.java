package org.app.view;

import org.app.controller.ProductController;

public class ClientView {

    static ProductController productController = new ProductController();

  public   static void displayAllProducts(){
        productController.getAllProducts().forEach(
                (product) -> {
                    System.out.println(product.getProduct_name());
                }


        );
    }


}

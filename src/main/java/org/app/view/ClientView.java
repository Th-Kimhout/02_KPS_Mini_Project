package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.TableConfig;

import java.util.Scanner;

public class ClientView {

    static ProductController productController = new ProductController();

  public static void displayAllProducts(){
        productController.getAllProducts().forEach(
                (product) -> {
                    System.out.println(product.getProduct_name());
                }
        );
  }
  public static void Display(){
      GetProduct.GetProductByName();
      GetProduct.GetProductById();
  }





}

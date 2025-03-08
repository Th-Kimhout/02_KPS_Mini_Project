package org.app.view;

import org.app.controller.ProductController;
import org.app.model.Product;
import org.app.utilies.TableConfig;

import java.util.Scanner;

public class ClientView {

    static ProductController productController = new ProductController();

  public static void displayAllProducts(){

        for(Product product : productController.getAllProducts()){
            TableConfig.getTable(product.getId() , product.getProduct_name()  , product.getProduct_unit_price() , product.getProduct_quantity() , product.getProduct_created_date());

        }


    }


}

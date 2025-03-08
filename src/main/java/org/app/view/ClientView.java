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
    public static void addNewProduct(){
      do{
          Scanner sc = new Scanner(System.in);
          System.out.print("Enter product name: ");
          String name = sc.nextLine();
          System.out.print("Enter product unit price: ");
          double price = sc.nextDouble();
          System.out.print("Enter product quantity: ");
          int quantity = sc.nextInt();
          System.out.println("Press enter to continue");

          sc.nextLine();
          productController.addProduct(new Product(name, price, quantity));
      }while(true);

    }

}

package org.app.view;
import org.app.model.Product;
import org.app.utilies.TableConfig;

import java.util.ArrayList;
import java.util.Scanner;
import static org.app.view.ClientView.productController;

public class GetProduct {
    public static void GetProductById() {
        System.out.print("Enter ID : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        ArrayList<Product> product = new ArrayList<>();
        product.add(productController.getProductById(id));
        if (productController.getProductById(id) == null) {
            System.out.println("No product found with this ID");
        }else {
            TableConfig.getTable(product);
        }
    }
    public static void GetProductByName() {
        System.out.println("Enter Name : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (productController.getProductByName(name).isEmpty()) {
            System.out.println("No product found with this name");
        }else{
            TableConfig.getTable(productController.getProductByName(name));
        }

    }
}


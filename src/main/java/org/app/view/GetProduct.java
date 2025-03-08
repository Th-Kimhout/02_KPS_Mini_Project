package org.app.view;
import org.app.model.Product;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

import java.util.ArrayList;
import java.util.Scanner;
import static org.app.view.ClientView.productController;

public class GetProduct {
    public static void GetProductById() {
        String input = UserInput.Input("Enter ID : ", "^\\d+$", "Invalid ID");
        int id = Integer.parseInt(input);
        ArrayList<Product> product = new ArrayList<>();
        product.add(productController.getProductById(id));
        if (productController.getProductById(id) == null) {
            System.out.println("No product found with this ID");
        }else {
            TableConfig.getTable(product);
        }
    }
    public static void GetProductByName() {
        String name =  UserInput.Input("Enter name : ", "^[a-zA-Z]+$", "Invalid name");
        if (productController.getProductByName(name).isEmpty()) {
            System.out.println("No product found with this name");
        }else{
            TableConfig.getTable(productController.getProductByName(name));
        }

    }
}


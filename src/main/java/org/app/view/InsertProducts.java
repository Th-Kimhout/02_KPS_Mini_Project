package org.app.view;

import org.app.model.Product;

import java.util.Scanner;

public class InsertProducts {


    public Product addProduct(Product product) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        System.out.print("Enter product description: ");


    }
}

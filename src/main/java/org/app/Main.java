package org.app;

import org.app.model.Product;
import org.app.utilies.Backup;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> productList = new ArrayList<>(List.of(
                new Product("Apple", 1.20, 50),
                new Product("Banana", 0.50, 100),
                new Product("Orange", 0.80, 75),
                new Product("Grapes", 2.50, 40),
                new Product("Mango", 1.75, 60),
                new Product("Pineapple", 3.00, 30),
                new Product("Watermelon", 5.00, 20),
                new Product("Strawberry", 2.20, 90),
                new Product("Blueberry", 3.50, 55),
                new Product("Peach", 1.90, 65)));

        Backup backup = new Backup();
        backup.doBackup();

    }
}
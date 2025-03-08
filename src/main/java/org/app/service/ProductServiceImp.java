package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class ProductServiceImp implements ProductService {
    ProductRepoImp productRepoImp = new ProductRepoImp();
    HashMap<String, Product> productTransaction = new HashMap<>();

    @Override
    public ArrayList<Product> getAllProducts() {
        return productRepoImp.getAllProducts();
    }

    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(int id, Product product, String field) {
        return false;
    }

    @Override
    public void deleteProduct(int id) {
        try {
            Scanner scanner = new Scanner(System.in);
            Product foundProduct = getProductById(id);
            String yN;

            TableConfig.getTable(List.of(foundProduct));

            loop:
            do {
                System.out.print("Are you sure to delete product id: " + id + "? (Y/N) : ");
                yN = scanner.nextLine();
                switch (yN.toUpperCase()) {
                    case "Y":
                        System.out.println();
                        productRepoImp.deleteProduct(id);
                        System.out.println("Product deleted successfully");
                        System.out.println("Enter to continue...");
                        scanner.nextLine();
                        break loop;
                    case "N":
                        System.out.println("Exit");
                        ;
                        break loop;
                    default:
                        System.out.println("Please input Y or N");
                }
            } while (true);

        } catch (NullPointerException e) {
            throw new NullPointerException("Product not found!");
        }
    }

    @Override
    public Product getProductById(int id) {
        return productRepoImp.getProductById(id);
    }

    @Override
    public ArrayList<Product> getProductByName(String name) {
        return productRepoImp.getProductByName(name);
    }


    @Override
    public boolean saveInsertTransaction() {
        for (HashMap.Entry<String, Product> entry : productTransaction.entrySet()) {
            if (entry.getKey().equals("ui")) {
                return productRepoImp.addProduct(entry.getValue());
            }
        }
        return false;
    }

    @Override
    public boolean saveUpdateTransaction() {
        for (HashMap.Entry<String, Product> entry : productTransaction.entrySet()) {
            if (entry.getKey().equals("uu")) {
                return productRepoImp.updateProduct(entry.getValue().getId(), entry.getValue());
            }
        }
        return false;
    }
}

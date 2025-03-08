package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.TableConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProductServiceImp implements ProductService {
    ProductRepoImp productRepoImp = new ProductRepoImp();
    HashMap<String, Product> productTransaction = new HashMap<>();

    @Override
    public ArrayList<Product> getAllProducts() {
        return productRepoImp.getAllProducts();
    }

    @Override
    public void addProduct(Product product) {
        productTransaction.put("ui", product);
    }

    @Override
    public void updateProduct(int id, Product product) {

        productTransaction.put("uu", product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
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

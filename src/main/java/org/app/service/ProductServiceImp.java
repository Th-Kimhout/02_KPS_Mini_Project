package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;

import java.util.ArrayList;
import java.util.HashMap;

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
    public boolean deleteProduct(int id) {
        return productRepoImp.deleteProduct(id);
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public ArrayList<Product> getProductByName(String name) {
        return null;
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

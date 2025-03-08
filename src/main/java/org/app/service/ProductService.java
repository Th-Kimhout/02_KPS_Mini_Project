package org.app.service;

import org.app.model.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> getAllProducts();

    void addProduct(Product product);

    void updateProduct(int id, Product product);

    boolean deleteProduct(int id);

    Product getProductById(int id);

    ArrayList<Product> getProductByName(String name);


    boolean saveInsertTransaction();

    boolean saveUpdateTransaction();
}

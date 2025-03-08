package org.app.service;

import org.app.model.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> getAllProducts();

    void addProduct()    ;

    void updateProduct(int id);

    void deleteProduct(int id);

    Product getProductById(int id);

    void getProductByName(String name);

    boolean saveInsertTransaction();

    boolean saveUpdateTransaction();
}

package org.app.service;

import org.app.model.Product;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> getAllProducts();

    Product addProduct()    ;

    boolean updateProduct(int id, Product product, String field);

    boolean deleteProduct(int id);

    Product getProductById(int id);

    ArrayList<Product> getProductByName(String name);


    boolean saveInsertTransaction();

    boolean saveUpdateTransaction();
}

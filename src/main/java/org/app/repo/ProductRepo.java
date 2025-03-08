package org.app.repo;

import org.app.model.Product;

import java.util.ArrayList;

public interface ProductRepo {
    ArrayList<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean updateProduct(int id, Product product);
    boolean updateFieldProduct(int id, String field, String value);
    Product getProductById(int id);
    ArrayList<Product> getProductByName(String name);
}

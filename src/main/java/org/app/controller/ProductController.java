package org.app.controller;

import org.app.model.Product;
import org.app.service.ProductServiceImp;

import java.util.ArrayList;

public class ProductController {

    ProductServiceImp productService = new ProductServiceImp();

    public ArrayList<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void addProduct() {
        productService.addProduct();
    }

    public boolean updateProduct(int id) {
        return productService.updateProduct(id);
    }

    public void deleteProduct(int id) {
        productService.deleteProduct(id);
    }

    public void getProductById(int id) {
        productService.getProductById(id);
    }

    public void getProductByName(String name) {
         productService.getProductByName(name);
    }

    public void commitTransaction() {
         productService.commitTransaction();
    }

    public void displayUnsavedProducts() {
        productService.displayUnsavedProducts();
    }
}

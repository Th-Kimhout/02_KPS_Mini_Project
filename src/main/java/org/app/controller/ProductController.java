package org.app.controller;

import org.app.model.Product;
import org.app.service.ProductServiceImp;

import java.util.ArrayList;

public class ProductController {

    ProductServiceImp productService = new ProductServiceImp();

public ArrayList<Product> getAllProducts(){
return productService.getAllProducts();
}

    public void addProduct(Product product) {
        productService.addProduct(product);
    }

public boolean updateProduct(int id){
    return productService.updateProduct(id);
}

public boolean deleteProduct(int id){
    return productService.deleteProduct(id);
}

    public Product getProductById(int id) {
        return productService.getProductById(id);
    }

    public ArrayList<Product> getProductByName(String name) {
        return productService.getProductByName(name);
    }

    public boolean saveInsertTransaction() {
       return productService.saveInsertTransaction();
    }
}

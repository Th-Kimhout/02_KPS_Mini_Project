package org.app.controller;

import org.app.model.Product;
import org.app.service.ProductServiceImp;

import java.util.ArrayList;

public class ProductController {

    ProductServiceImp productService = new ProductServiceImp();

public ArrayList<Product> getAllProducts(){
return productService.getAllProducts();
}

public boolean addProduct(Product product){
    return productService.addProduct(product);
}

public boolean updateProduct(int id, Product product, String field){
    return productService.updateProduct( id, product, field);
}

public void deleteProduct(int id){
    productService.deleteProduct(id);
}

public Product getProductById(int id){
    return productService.getProductById(id);
}
public ArrayList<Product> getProductByName(String name){
    return productService.getProductByName(name);
}
}

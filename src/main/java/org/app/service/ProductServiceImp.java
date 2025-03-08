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
    public void addProduct(Product product) {
        productTransaction.put("ui", product);
    }

    @Override
    public boolean updateProduct(int id) {
        try{
            Product tempProduct = getProductById(id);
            int getMenuValue;
            do {
                TableConfig.getTable(List.of(tempProduct));
                getMenuValue = Integer.parseInt(TableConfig.displayUpdateMenu());
                switch (getMenuValue) {
                    case 1 ->
                            tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z ]+$", "Invalid Input. Allow only Text!"));
                    case 2 ->
                            tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d+(\\.\\d{1,2})?$", "Invalid Input. Allow only Number!")));
                    case 3 ->
                            tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d+$", "Invalid Input. Allow only Number!")));
                    case 4 -> {
                        tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z ]+$", "Invalid Input. Allow only Text!"));
                        tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d+(\\.\\d{1,2})?$", "Invalid Input. Allow only Number!")));
                        tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d+$", "Invalid Input. Allow only Number!")));
                    }
                }
                productTransaction.put("uu",tempProduct);
            } while (getMenuValue != 5);
        }catch (NullPointerException e){
            throw new NullPointerException("Product Not Found!");
        }
        return true;
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

package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.Color;

import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;


import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProductServiceImp implements ProductService {
    ProductRepoImp productRepoImp = new ProductRepoImp();
    ArrayList<Product> productInsertTransaction = new ArrayList<>();
    ArrayList<Product> productUpdateTransaction = new ArrayList<>();
    int productId = productRepoImp.getNextProductId();

    @Override
    public ArrayList<Product> getAllProducts() {
        return productRepoImp.getAllProducts();
    }

    @Override
    public void addProduct() {
        int incrementID = productId++;
        System.out.println("Product ID: " + incrementID);

        String productName = UserInput.Input(Color.BRIGHT_YELLOW + "Enter product name : " + Color.RESET, "^[a-zA-Z\\d ]+$", Color.BRIGHT_RED + "[!] Invalid product name!" + Color.RESET);

        String price = UserInput.Input(Color.BRIGHT_YELLOW + "Enter price : " + Color.RESET, "^\\d{1,7}(\\.\\d{1,2})?$", Color.BRIGHT_RED + "[!] Invalid price! Price can only be number and less than 6 numbers!" + Color.RESET);

        String quantity = UserInput.Input(Color.BRIGHT_YELLOW + "Enter quantity : " + Color.RESET, "^\\d{1,8}$", Color.BRIGHT_RED + "[!] Invalid quantity! Quantity can only be number and less than 8 numbers!" + Color.RESET);

        System.out.println(Color.BRIGHT_GREEN + "Product created successfully" + Color.RESET);
        System.out.println(Color.BRIGHT_YELLOW + "Enter to continue..." + Color.RESET);

        new Scanner(System.in).nextLine();


        double finalPrice = Double.parseDouble(price);

        int quantityInt = Integer.parseInt(quantity);

        productInsertTransaction.add(new Product(incrementID, productName, finalPrice, quantityInt, new Date(System.currentTimeMillis())));
    }

    @Override
    public void updateProduct(int id) {
        Product tempProduct = getProductById(id);
        int getMenuValue;
        do {
            getMenuValue = Integer.parseInt(TableConfig.displayUpdateMenu());
            switch (getMenuValue) {
                case 1 ->
                        tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z \\d]+$", "[!] Invalid Input!"));
                case 2 ->
                        tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d{1,7}(\\.\\d{1,2})?$", "[!] Invalid price! Price can only be number and less than 6 numbers!")));
                case 3 ->
                        tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d{1,8}$", "[!] Invalid quantity! Quantity can only be number and less than 8 numbers!")));
                case 4 -> {
                    tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z \\d]+$", "Invalid Input"));
                    tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d{1,7}(\\.\\d{1,2})?$", "[!] Invalid price! Price can only be number and less than 6 numbers!(x.xx)")));
                    tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d{1,8}$", "[!] Invalid quantity! Quantity can only be number and less than 8 numbers!")));
                }
            }
        } while (getMenuValue != 5);
        productUpdateTransaction.add(tempProduct);
    }

    @Override
    public void deleteProduct(int id) {
        Scanner scanner = new Scanner(System.in);
        Product foundProduct = getProductById(id);
        String yN;

        if (foundProduct == null) {
            throw new NullPointerException("[!] Please input Product ID again!");
        }

        loop:
        do {
            System.out.print(Color.BRIGHT_YELLOW + "Are you sure to delete product id: " + Color.RESET + Color.BRIGHT_RED + id + Color.RESET + " ? (Y/N) : ");
            yN = scanner.nextLine();
            switch (yN.toUpperCase()) {
                case "Y":
                    productRepoImp.deleteProduct(id);
                    System.out.println(Color.BRIGHT_GREEN + "\nProduct deleted successfully" + Color.RESET);
                    System.out.println(Color.BRIGHT_YELLOW + "Enter to continue..." + Color.RESET);
                    scanner.nextLine();
                    break loop;
                case "N":
                    System.out.println(Color.BRIGHT_GREEN + "\nYou're canceled deleted product." + Color.RESET);
                    System.out.println(Color.BRIGHT_YELLOW + "Enter to continue..." + Color.RESET);
                    scanner.nextLine();
                    break loop;
                default:
                    System.out.println(Color.BRIGHT_RED + "Please input Y or N" + Color.RESET);
            }
        } while (true);
    }

    @Override
    public Product getProductById(int id) {
        Product foundProduct = productRepoImp.getProductById(id);
        if (foundProduct == null) {
            throw new NullPointerException(Color.BRIGHT_RED + "[!] No product found with this ID!" + Color.RESET);
        } else {
            TableConfig.getTable(List.of(foundProduct));
        }
        return foundProduct;
    }

    @Override
    public void getProductByName(String name) {
        ArrayList<Product> productList = productRepoImp.getProductByName(name);
        if (productList.isEmpty()) {
            throw new NullPointerException(Color.BRIGHT_RED + "[!] No product found with this name!" + Color.RESET);

        } else {
            TableConfig.getTable(productList);
        }
    }

    @Override
    public void commitTransaction() {
        System.out.println("""
                Choose:
                si) Save Insert
                su) Save Update
                """);
        String choice = UserInput.Input("Choice : ", "^(si|su|b)$", "Invalid Input. ui, uu or b only!");
        switch (choice) {
            case "si" -> {
                if (productInsertTransaction.isEmpty()) {
                    System.out.println(Color.BRIGHT_RED + "Nothing to insert!" + Color.RESET);
                    return;
                }
                for (Product product : productInsertTransaction) {
                    productRepoImp.addProduct(product);
                    System.out.println(Color.BRIGHT_GREEN + "Add Product ID : " + product.getId() + " Successfully!" + Color.RESET);
                }
                productInsertTransaction = new ArrayList<>();
            }
            case "su" -> {
                if (productUpdateTransaction.isEmpty()) {
                    System.out.println(Color.BRIGHT_RED + "Nothing to update!" + Color.RESET);
                    return;
                }
                for (Product product : productUpdateTransaction) {
                    productRepoImp.updateProduct(product.getId(), product);
                    System.out.println(Color.BRIGHT_GREEN + "Update Product ID : " + product.getId() + " Successfully!" + Color.RESET);
                }
                productUpdateTransaction = new ArrayList<>();
            }
        }
        System.out.println(Color.BRIGHT_YELLOW + "Press Enter to continue..." + Color.RESET);
        new Scanner(System.in).nextLine();
    }

    public void displayUnsavedProducts() {
        System.out.print("""
                Display Unsaved Products
                ui) Unsaved Insert
                uu) Unsaved Update
                b)  Exit
                """);
        String choice = UserInput.Input("Choice : ", "^(ui|uu|b)$", "Invalid Input. ui, uu or b only!");
        switch (choice) {
            case "ui" -> TableConfig.getTable(productInsertTransaction);

            case "uu" -> TableConfig.getTable(productUpdateTransaction);


        }
        System.out.println(Color.BRIGHT_YELLOW + "Press Enter to continue..." + Color.RESET);
        new Scanner(System.in).nextLine();

    }
}

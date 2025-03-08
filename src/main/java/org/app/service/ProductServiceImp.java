package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.Color;
import org.app.utilies.DBConfig;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProductServiceImp implements ProductService {
    ProductRepoImp productRepoImp = new ProductRepoImp();
    ArrayList<Product> productInsertTransaction = new ArrayList<>();
    ArrayList<Product> productUpdateTransaction = new ArrayList<>();

    @Override
    public ArrayList<Product> getAllProducts() {
        return productRepoImp.getAllProducts();
    }

    @Override
    public void addProduct() {


        int productId = getNextProductId();
        System.out.println("Product ID: " + productId );

        String productName = UserInput.Input(Color.BRIGHT_YELLOW + "-> Enter product name :" + Color.RESET, "^[a-zA-Z ]+$", Color.BRIGHT_RED + "[!] Invalid product name" + Color.RESET);

        String price = UserInput.Input(Color.BRIGHT_YELLOW + "-> Enter price :" + Color.RESET, "^\\d+(\\.\\d{1,2})?$", Color.BRIGHT_RED + "[!] Invalid price" + Color.RESET);

        String quantity = UserInput.Input(Color.BRIGHT_YELLOW + "-> Enter quantity :" + Color.RESET, "^\\d+$", Color.BRIGHT_RED + "[!] Invalid quantity " + Color.RESET);

        System.out.println("\n");
        System.out.println(Color.BRIGHT_YELLOW +  "Enter to continue..." + Color.RESET);

        new Scanner(System.in).nextLine();

        System.out.println(Color.BRIGHT_GREEN + "Product created successfully" + Color.RESET);

        double finalPrice = Double.parseDouble(price);

        int quantityInt = Integer.parseInt(quantity);

        Product product = new Product( productId ,  productName, finalPrice, quantityInt ,new Date(System.currentTimeMillis()));
    }
    private int getNextProductId() {
        String selectId = "SELECT MAX(id) FROM products";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectId);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1; // If no products exist yet
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1; // Default to 1 on error
        }
    }

    @Override
    public boolean updateProduct(int id) {
        try {
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
                productUpdateTransaction.add(tempProduct);
            } while (getMenuValue != 5);
        } catch (NullPointerException e) {
            throw new NullPointerException("Product Not Found!");
        }
        return true;
    }

    @Override
    public void deleteProduct(int id) {
        try {
            Scanner scanner = new Scanner(System.in);
            Product foundProduct = getProductById(id);
            String yN;

            TableConfig.getTable(List.of(foundProduct));

            loop:
            do {
                System.out.print("Are you sure to delete product id: " + id + "? (Y/N) : ");
                yN = scanner.nextLine();
                switch (yN.toUpperCase()) {
                    case "Y":
                        System.out.println();
                        productRepoImp.deleteProduct(id);
                        System.out.println("Product deleted successfully");
                        System.out.println("Enter to continue...");
                        scanner.nextLine();
                        break loop;
                    case "N":
                        System.out.println("Exit");
                        ;
                        break loop;
                    default:
                        System.out.println("Please input Y or N");
                }
            } while (true);

        } catch (NullPointerException e) {
            throw new NullPointerException("Product not found!");
        }
    }

    @Override
    public Product getProductById(int id) {
        Product foundProduct = productRepoImp.getProductById(id);
        if (foundProduct == null) {
            System.out.println("No product found with this ID");
        } else {
            TableConfig.getTable(List.of(foundProduct));
        }
        return foundProduct;
    }

    @Override
    public void getProductByName(String name) {
        ArrayList<Product> productList = productRepoImp.getProductByName(name);
        if (productList.isEmpty()) {
            System.out.println("No product found with this name");
        } else {
            TableConfig.getTable(productList);
        }
    }

    @Override
    public boolean saveInsertTransaction() {
        System.out.println(productInsertTransaction);
        for (Product product : productInsertTransaction) {
            return productRepoImp.addProduct(product);

        }
        return false;
    }

    @Override
    public boolean saveUpdateTransaction() {
        for (Product product : productUpdateTransaction) {
            return productRepoImp.updateProduct(product.getId(), product);
        }
        return false;
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
            case "b" -> {
                return;
            }
        }
        System.out.println("Press Enter to continue.....");
        new Scanner(System.in).nextLine();

    }
}

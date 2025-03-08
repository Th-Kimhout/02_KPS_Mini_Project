package org.app.service;

import org.app.model.Product;
import org.app.repo.ProductRepoImp;
import org.app.utilies.Color;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

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
        int lastProductId = productRepoImp.getAllProducts().stream().mapToInt(Product::getId).max().orElse(-1);

        System.out.println("Product ID: " + (++lastProductId));

        String productName = UserInput.Input("Enter product name :", "^[a-zA-Z ]+$", "Invalid product name");

        String price = UserInput.Input("Enter price :", "^\\d+(\\.\\d{1,2})?$", "Invalid price");

        String quantity = UserInput.Input("Enter quantity :", "^\\d+$", "Invalid quantity");

        System.out.println("Press Enter to continue...");

        new Scanner(System.in).nextLine();

        double finalPrice = Double.parseDouble(price);

        int quantityInt = Integer.parseInt(quantity);

        Product product = new Product(lastProductId, productName, finalPrice, quantityInt, java.sql.Date.valueOf(LocalDate.now()));
        productInsertTransaction.add(product);

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
        Scanner scanner = new Scanner(System.in);
        Product foundProduct = getProductById(id);
        String yN;

        if (foundProduct == null){
            throw new NullPointerException("Please input Product ID again.");
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

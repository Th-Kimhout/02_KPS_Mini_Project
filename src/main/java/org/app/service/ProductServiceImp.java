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
    public void updateProduct(int id) {
            Product tempProduct = getProductById(id);
            int getMenuValue;
            do {
                TableConfig.getTable(List.of(tempProduct));
                getMenuValue = Integer.parseInt(TableConfig.displayUpdateMenu());
                switch (getMenuValue) {
                    case 1 ->
                            tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z \\d]+$", "Invalid Input"));
                    case 2 ->
                            tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d+(\\.\\d{1,2})?$", "Invalid Input. Allow only Number Or Only 2 De ")));
                    case 3 ->
                            tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d+$", "Invalid Input. Allow only Number!")));
                    case 4 -> {
                        tempProduct.setProduct_name(UserInput.Input("Enter Name : ", "^[a-zA-Z \\d]+$", "Invalid Input"));
                        tempProduct.setProduct_unit_price(Double.parseDouble(UserInput.Input("Enter Price : ", "^\\d+(\\.\\d{1,2})?$", "Invalid Input. Allow only Number And X.XX")));
                        tempProduct.setProduct_quantity(Integer.parseInt(UserInput.Input("Enter Qty : ", "^\\d+$", "Invalid Input. Allow only Number!")));
                    }
                }
                productUpdateTransaction.add(tempProduct);
            } while (getMenuValue != 5);
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
           throw new NullPointerException(Color.BRIGHT_RED + "[!] No product found with this ID" + Color.RESET);
        } else {
            TableConfig.getTable(List.of(foundProduct));
        }
        return foundProduct;
    }

    @Override
    public void getProductByName(String name) {
        ArrayList<Product> productList = productRepoImp.getProductByName(name);
        if (productList.isEmpty()) {
            throw new NullPointerException(Color.BRIGHT_RED + "[!] No product found with this name" + Color.RESET);

        } else {
            TableConfig.getTable(productList);
        }
    }

    @Override
    public void commitTransaction() {

        String choice = UserInput.Input("Choice : ", "^(si|su|b)$", "Invalid Input. ui, uu or b only!");
        switch (choice) {
            case "si" -> {
                System.out.println(productInsertTransaction);
                for (Product product : productInsertTransaction) {
                    productRepoImp.addProduct(product);
                }

            }
            case "su" -> {
                for (Product product : productUpdateTransaction) {
                    productRepoImp.updateProduct(product.getId(), product);
                }
            }
            case "b" -> {
                return;
            }
        }
    }

    public void displayUnsavedProducts() {
        System.out.print("""
                Display Unsaved Products
                ui) Unsaved Insert
                uu) Unsaved Update
                b)  Exit
                """);
        String choice = UserInput.Input("Choice : ", "^(ui|uu)$", "Invalid Input. ui, uu or b only!");
        switch (choice) {
            case "ui" -> TableConfig.getTable(productInsertTransaction);

            case "uu" -> TableConfig.getTable(productUpdateTransaction);

        }
        System.out.println(Color.BRIGHT_YELLOW + "Press Enter to continue..." + Color.RESET);
        new Scanner(System.in).nextLine();

    }
}

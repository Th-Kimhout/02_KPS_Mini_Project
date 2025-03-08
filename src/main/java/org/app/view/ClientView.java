package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.*;


public class ClientView {

    static ProductController productController = new ProductController();

    public static void mainView() {
        int firstRow = 0;
        int showRows = Limit_rows.getLimitRows();
        while (true) {
            // display all product
            displayAllProducts(firstRow, showRows);

            displayMenuOptions();

            String pages = UserInput.Input("Enter your choice: ", "^[a-zA-Z]+$", "Invalid choice");
            switch (pages.toUpperCase()) {
                case "N" -> {
                    if (firstRow + showRows < productController.getAllProducts().size()) {
                        firstRow += showRows;
                    }
                }
                case "P" -> {
                    if (firstRow - showRows >= 0) {
                        firstRow -= showRows;
                    }
                }
                case "F" -> firstRow = 0;
                case "L" ->
                        firstRow = productController.getAllProducts().size() - (productController.getAllProducts().size() % showRows == 0 ? showRows : productController.getAllProducts().size() % showRows);
                case "G" -> {
                    String pageNumber = UserInput.Input("Page Number : ", "^[1-" + (int) (Math.ceil((float) productController.getAllProducts().size() / showRows)) + "]$", "Invalid choice");
                    firstRow = (Integer.parseInt(pageNumber) - 1) * showRows;
                }
                case "W" -> productController.addProduct();
                case "R" -> {
                    String input = UserInput.Input("Enter ID : ", "^\\d+$", "Invalid ID");
                    int id = Integer.parseInt(input);
                    productController.getProductById(id);
                }
                case "U" -> updateProducts();
                case "D" -> deleteProduct();
                case "S" -> {
                    String name = UserInput.Input("Enter name : ", "^[a-zA-Z]+$", "Invalid name");
                    productController.getProductByName(name);
                }

                case "SE" -> {
                    String limitRows = UserInput.Input("Limit Rows : ", "^[1-9][0-9]*$", "Invalid choice");
                    showRows = Limit_rows.updateLimitRows(Integer.parseInt(limitRows));
                }
                case "SA" -> productController.saveUpdateTransaction();

                case "UN" -> {productController.displayUnsavedProducts();
                }
                case "BA" -> Backup.doBackup();

                case "RE" -> Backup.doRestore();

                case "E" -> {
                    System.out.println("Thank you for using this app");
                    System.exit(0);
                }
            }
        }
    }

    public static void displayMenuOptions() {
        System.out.println("                            ___________ Menu ___________");
        System.out.println("    N. Next Page    P. Previous Page        F. First Page       L. Last Page    G. Goto");
        System.out.println();
        System.out.println("W) Write    R) Read (id)    U) Update       D) Delete       S) Search (name)   Se) Set rows");
        System.out.println("Sa) Save    Un) Unsaved     Ba) Backup      Re) Restore     E) Exit");
        System.out.println("                            __________________________");
    }

    public static void displayAllProducts(int firstRow, int showRows) {
        TableConfig.printTable(productController.getAllProducts(), firstRow, showRows);
    }

    public static void deleteProduct() {
        String productID;
        productID = UserInput.Input("Please Input Product ID to delete record: ", "^[0-9]+$", "Product ID must be number.");
        try {
            productController.deleteProduct(Integer.parseInt(productID));
        } catch (Exception e) {
            System.out.println(Color.BRIGHT_RED + e.getMessage() + Color.RESET);
            deleteProduct();
        }
    }

    public static void updateProducts() {
        String productById = UserInput.Input("Input Id to Update : ", "^[\\d]+$", "Allow Input only Number");
        try {
            productController.updateProduct(Integer.parseInt(productById));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            updateProducts();
        }
    }

}


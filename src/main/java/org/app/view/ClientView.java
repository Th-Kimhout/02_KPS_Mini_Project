package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.*;


public class ClientView {

    static ProductController productController = new ProductController();

    public static void mainView() {

        System.out.println(Color.BRIGHT_CYAN+ """
                
                  ____  _             _      __   __                                                   _     ____            _                \s
                 / ___|| |_ ___   ___| | __ |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  / ___| _   _ ___| |_ ___ _ __ ___ \s
                 \\___ \\| __/ _ \\ / __| |/ / | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __| \\___ \\| | | / __| __/ _ \\ '_ ` _ \\\s
                  ___) | || (_) | (__|   <  | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ___) | |_| \\__ \\ ||  __/ | | | | |
                 |____/ \\__\\___/ \\___|_|\\_\\ |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |____/ \\__, |___/\\__\\___|_| |_| |_|
                                                                      |___/                                       |___/                      \s
                
                """+Color.RESET);

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
                case "SA" -> productController.commitTransaction();

                case "UN" -> {
                    productController.displayUnsavedProducts();
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
        String[] list = {"N.", "P.", "F.", "G."};
        String[] options = {"Next Page", "Previous Page", "First Page", "Goto"};
        System.out.println("                            ___________ Menu ___________");
        for (int i = 0; i < 4; i++) {
            System.out.print("         " + Color.BRIGHT_GREEN + list[i] + Color.RESET + " " + options[i]);
        }
        System.out.println("\n");
        String[] menuList = {"W)", "R)", "U)", "D)", "S)", "Se)", "Sa)", "Un)", "Ba)", "Re)", "E)"};
        String[] menuOption = {"Write", "Read (id)", "Update", "Delete", "Search (name)", "Set Rows", "Save", "Unsaved", "Backup", "Restore", "Exit"};
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                System.out.println();
            }
            System.out.print(Color.BRIGHT_GREEN + menuList[i] + Color.RESET + " " + menuOption[i] + "     ");
        }
        System.out.println();
        System.out.println("                            __________________________");
    }

    public static void displayAllProducts(int firstRow, int showRows) {
        TableConfig.printTable(productController.getAllProducts(), firstRow, showRows);
    }

    public static void deleteProduct() {
        String productID;
        productID = UserInput.Input("Enter Product ID to delete: ", "^[0-9]+$", "Product ID must be integer. Product ID cannot be empty, text and special character!");
        try {
            productController.deleteProduct(Integer.parseInt(productID));
        } catch (Exception e) {
            System.out.println(e.getMessage());
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


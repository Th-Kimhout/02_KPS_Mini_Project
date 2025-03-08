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

            String pages = UserInput.Input("Enter your choice: ", "^[a-zA-Z]+$", "Invalid choice").toUpperCase();
            Menu exactMenu = null;

            // loop for get value of Menu enum
            for(Menu menu : Menu.values()) {

                String menuName = menu.name(); // store enum value,
                if(menuName.startsWith(pages)) { // compare if menuName contain the letter that we have been input
                    exactMenu = menu;
                    break;
                }
            }
            if(exactMenu == null) {
                throw new IllegalArgumentException("Menu not found");
            }

            switch (exactMenu) {
                case NEXT_PAGE -> {
                    if (firstRow + showRows < productController.getAllProducts().size()) {
                        firstRow += showRows;
                    }
                }
                case PREVIOUS_PAGE -> {
                    if (firstRow - showRows >= 0) {
                        firstRow -= showRows;
                    }
                }
                case FIRST_PAGE -> firstRow = 0;
                case LAST_PAGE ->
                        firstRow = productController.getAllProducts().size() - (productController.getAllProducts().size() % showRows == 0 ? showRows : productController.getAllProducts().size() % showRows);
                case GOTO -> {
                    String pageNumber = UserInput.Input("Page Number : ", "^[1-" + (int) (Math.ceil((float) productController.getAllProducts().size() / showRows)) + "]$", "Invalid choice");
                    firstRow = (Integer.parseInt(pageNumber) - 1) * showRows;
                }
                case WRITE -> productController.addProduct();
                case READ -> {
                    String input = UserInput.Input("Enter ID : ", "^\\d+$", "Invalid ID");
                    int id = Integer.parseInt(input);
                    productController.getProductById(id);
                }
                case UPDATE -> updateProducts();
                case DELETE -> deleteProduct();
                case SEARCH -> {
                    String name = UserInput.Input("Enter name : ", "^[a-zA-Z]+$", "Invalid name");
                    productController.getProductByName(name);
                }

                case SET_ROW -> {
                    String limitRows = UserInput.Input("Limit Rows : ", "^[1-9][0-9]*$", "Invalid choice");
                    showRows = Limit_rows.updateLimitRows(Integer.parseInt(limitRows));
                }
                case SAVE -> productController.saveInsertTransaction();

                case UN_SAVE -> {productController.displayUnsavedProducts();
                }
                case BACKUP -> Backup.doBackup();

                case RESTORE -> Backup.doRestore();

                case EXIT -> {
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


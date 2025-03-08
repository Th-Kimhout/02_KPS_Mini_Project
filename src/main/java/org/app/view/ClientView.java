package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.Limit_rows;
import org.app.utilies.TableConfig;
import org.app.utilies.UserInput;

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
                case "F" -> {
                    firstRow = 0;
                }
                case "L" -> {
                    firstRow = productController.getAllProducts().size() - (productController.getAllProducts().size() % showRows == 0 ? showRows : productController.getAllProducts().size() % showRows);
                }
                case "G" -> {
                    String pageNumber = UserInput.Input("Page Number : ", "^[1-" + (int) (Math.ceil((float) productController.getAllProducts().size() / showRows)) + "]$", "Invalid choice");
                    firstRow = (Integer.parseInt(pageNumber) - 1) * showRows;
                }
                case "SE" -> {
                    String limitRows = UserInput.Input("Limit Rows : ", "^[1-9][0-9]*$", "Invalid choice");
                    showRows = Limit_rows.updateLimitRows(Integer.valueOf(limitRows));
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

//        int firstRow = 0;
//        int showRows = Limit_rows.getLimitRows();
//
//        showRows = Limit_rows.updateLimitRows(7);

//        productController.getAllProducts().forEach(
//                (product) -> {
//                    System.out.println(product.toString());
//                }
//        );
        TableConfig.printTable(productController.getAllProducts(), firstRow, showRows);
    }
}


package org.app.view;

import org.app.controller.ProductController;
import org.app.utilies.Utility;

public class ClientView {

    static ProductController productController = new ProductController();

    public static void displayAllProducts() {
        productController.getAllProducts().forEach(
                (product) -> {
                    System.out.println(product.getProduct_name());
                }
        );
    }

    public static void deleteProduct() {
        String productID;

        do {
            productID = Utility.patternCheck("^[0-9]+$", "Product ID must be integer. Product ID cannot be empty, text and special character!", "Enter Product ID to delete: ");
            boolean successDeleted = productController.deleteProduct(Integer.parseInt(productID));

            if (successDeleted){
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Product does not exist");
            }
        } while (true);
    }
}

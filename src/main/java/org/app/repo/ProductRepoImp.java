package org.app.repo;

import org.app.model.Product;
import org.app.utilies.DBConfig;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;

public class ProductRepoImp implements ProductRepo {
    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String selectSql = "SELECT * FROM products";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                double productUnitPrice = rs.getDouble("product_unit_price");
                int productQuantity = rs.getInt("product_quantity");
                Date importedDate = rs.getDate("imported_date");
                productList.add(new Product(id, productName, productUnitPrice, productQuantity, importedDate));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return productList;
    }

    @Override
        public boolean addProduct(Product product) {
        String insertSql = "INSERT INTO products VALUES(?,?,?,?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(insertSql)) {

            pStmt.setString(1, product.getProduct_name());
            pStmt.setDouble(2, product.getProduct_unit_price());
            pStmt.setInt(3, product.getProduct_quantity());
            pStmt.setDate(4 , product.getProduct_created_date());
            return pStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProduct(int id, Product product) {
        return false;
    }

    @Override
    public boolean updateFieldProduct(int id, String field, String value) {
        return false;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        String selectSql = "select * from products where id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("product_name");
                double productUnitPrice = rs.getDouble("product_unit_price");
                int productQuantity = rs.getInt("product_quantity");
                Date importedDate = rs.getDate("imported_date");
                product = new Product(productId, productName, productUnitPrice, productQuantity, importedDate);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public ArrayList<Product> getProductByName(String name) {
        ArrayList<Product> productList = new ArrayList<>();
        String selectSql = "select * from products where product_name like ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                double productUnitPrice = rs.getDouble("product_unit_price");
                int productQuantity = rs.getInt("product_quantity");
                Date importedDate = rs.getDate("imported_date");
                productList.add(new Product(id, productName, productUnitPrice, productQuantity, importedDate));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
}

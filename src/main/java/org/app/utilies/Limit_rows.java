package org.app.utilies;

import org.app.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class Limit_rows {
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String selectSql = "select * from products";

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

    public static int getLimitRows() {
        int limitRows = 0;
        String selectSql = "select * from limit_rows";

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {
            while (rs.next()) {
                limitRows = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return limitRows;
    }

    public static int updateLimitRows(int updatedRows) {
        String updateSql = "update limit_rows set limits = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, updatedRows);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return updatedRows;
    }
}

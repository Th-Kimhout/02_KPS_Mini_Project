package org.app.utilies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Limit_rows {

    public static int getLimitRows() {
        String selectSql = "SELECT * FROM limit_rows";
        int limitRows = 0;
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

    public static int updateLimitRow(int Rows) {
        String selectSql = "UPDATE  limit_rows SET rows = ?";
        int limitRows = 0;
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(selectSql)){

             preparedStatement.setInt(1 , Rows);

             limitRows = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return limitRows;
    }
}

package org.app.utilies;

import org.app.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;

import org.app.controller.ProductController;
import org.app.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;


public class TableConfig {

    public static void getTable(ArrayList<Product> product) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.addCell( "ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Unit Price", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Quality", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Import Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));
         for (int i = 0; i<product.size(); i++) {
             table.addCell(String.valueOf(product.get(i).getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
             table.addCell(product.get(i).getProduct_name(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
             table.addCell(String.valueOf(product.get(i).getProduct_unit_price()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
             table.addCell(String.valueOf(product.get(i).getProduct_quantity()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
             table.addCell(String.valueOf(product.get(i).getProduct_created_date()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
         }

        for (int j = 0;j<5; j++){
            table.setColumnWidth(j, 20, 40);
        }
        for (String t : table.renderAsStringArray()) {
            System.out.println(t);
        }
    }
    static ProductController productController = new ProductController();

    public static void printTable(List<Product> lists, int firstRow, int showRows) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        table.setColumnWidth(0, 15, 20);
        table.setColumnWidth(1, 20, 40);
        table.setColumnWidth(2, 15, 20);
        table.setColumnWidth(3, 15, 20);
        table.setColumnWidth(4, 20, 20);

        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Unit Price", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Quality", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Import Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (int i = firstRow; i < Math.min(firstRow + showRows, lists.size()); i++) {
            Product product = lists.get(i);
            table.addCell(String.valueOf(product.getId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(product.getProduct_name()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(product.getProduct_unit_price()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(product.getProduct_quantity()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(product.getProduct_created_date()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }

        table.addCell("Page : " + ((int) (Math.ceil((float) firstRow / showRows)) + 1) + " of " + (int) (Math.ceil((float) productController.getAllProducts().size() / showRows)), new CellStyle(CellStyle.HorizontalAlign.CENTER),2);
        table.addCell("Total Record : " + productController.getAllProducts().size(), new CellStyle(CellStyle.HorizontalAlign.CENTER),3);

        System.out.println(table.render());
    }
}

package org.app.utilies;

import org.app.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;

public class TableConfig {

    public static void getTable(List<Product> product) {
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

    public static String displayUpdateMenu(){
        System.out.print("1. Name \t 2. Unit Price \t 3. Qty \t 4.All Field \t 5.Exit \n");
        String getMenuValue = UserInput.Input("Choose an Option to Update : ","^[\\d]+$","Allow Input only Text");
        return getMenuValue;
    }
}

package org.app.utilies;

import org.app.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;



public class TableConfig {

    public static void getTable(List<Product> product) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        String[] columnsHeader = {"ID", "Product Name", "Unit Price", "Quality", "Import Date"};
        for (String col : columnsHeader) {
            table.addCell(Color.BRIGHT_PURPLE + col + Color.BRIGHT_PURPLE, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }
        for (Product value : product) {
            table.addCell(Color.BRIGHT_BLUE + value.getId() + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(value.getProduct_name(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(value.getProduct_unit_price()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(value.getProduct_quantity()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(Color.BRIGHT_BLUE + value.getProduct_created_date() + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }

        for (int j = 0; j < 5; j++) {
            table.setColumnWidth(j, 20, 40);
        }
        for (String t : table.renderAsStringArray()) {
            System.out.println(t);
        }
    }


    public static void printTable(List<Product> productList, int startIndex, int showRows) {
        int totalRecords = productList.size();
        int endIndex = Math.min(startIndex + showRows, totalRecords);
        int currentPage = ((int) (Math.ceil((float) startIndex / showRows)) + 1);
        int totalPage = (int) (Math.ceil((float) productList.size() / showRows));
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        table.setColumnWidth(0, 15, 20);
        table.setColumnWidth(1, 20, 40);
        table.setColumnWidth(2, 15, 20);
        table.setColumnWidth(3, 15, 20);
        table.setColumnWidth(4, 20, 20);
        String[] columnsHeader = {"ID", "Product Name", "Unit Price", "Quality", "Import Date"};
        for (String col : columnsHeader) {
            table.addCell(Color.BRIGHT_PURPLE + col + Color.BRIGHT_PURPLE, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }

        for (int i = startIndex; i < endIndex; i++) {
            Product product = productList.get(i);
            table.addCell(Color.BRIGHT_BLUE + product.getId() + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(product.getProduct_name(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(product.getProduct_unit_price() + "", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(product.getProduct_quantity() + "", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(Color.BRIGHT_BLUE + product.getProduct_created_date() + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }

        table.addCell("Page : " + Color.BRIGHT_YELLOW + currentPage + Color.RESET + " of " + Color.BRIGHT_RED + totalPage + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER), 2);
        table.addCell("Total Record : " + Color.BRIGHT_GREEN + totalRecords + Color.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER), 3);

        System.out.println(table.render());
    }

    public static String displayUpdateMenu() {
        System.out.print(Color.BRIGHT_CYAN + "1.Name \t 2.Unit Price \t 3.Qty \t 4.All Field \t 5.Exit \n" + Color.RESET);
        String getMenuValue = UserInput.Input("Choose an Option to Update : ", "^[\\d]+$", "Allow Input only Number");
        return getMenuValue;
    }
}

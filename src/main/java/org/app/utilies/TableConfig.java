package org.app.utilies;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class TableConfig {

    public static void getTable(int id, String name, double price, int quality, String importDate) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.addCell( "ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Unit Price", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Quality", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Import Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));

        table.addCell(String.valueOf(id), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(name, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(String.valueOf(price), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(String.valueOf(quality), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(importDate, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (int i = 0;i<5; i++){
            table.setColumnWidth(i, 20, 40);
        }
        for (String t : table.renderAsStringArray()) {
            System.out.println(t);
        }
    }
}

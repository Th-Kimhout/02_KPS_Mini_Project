package org.app.model;

import java.sql.Date;

public class Product {
    private int id;
    private String product_name;
    private double product_unit_price;
    private int product_quantity;
    private Date product_created_date;

    public Product(int id, String product_name, double product_unit_price, int product_quantity, Date product_created_date) {
        this.id = id;
        this.product_name = product_name;
        this.product_unit_price = product_unit_price;
        this.product_quantity = product_quantity;
        this.product_created_date = product_created_date;
    }


    public  Product(String product_name, double product_unit_price, int product_quantity ) {
        this.product_name = product_name;
        this.product_unit_price = product_unit_price;
        this.product_quantity = product_quantity;
        this.product_created_date = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_unit_price() {
        return product_unit_price;
    }

    public void setProduct_unit_price(double product_unit_price) {
        this.product_unit_price = product_unit_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public Date getProduct_created_date() {
        return product_created_date;
    }

    public void setProduct_created_date(Date product_created_date) {
        this.product_created_date = product_created_date;
    }
}

package com.hackrupt.yolo;

/**
 * Created by admin on 2/4/2018.
 */

public class SupplierReg {
    public String name;
    public String email;
    public String company;
    public String product;
    public String add;
    public String price;
    public String type;

    public SupplierReg() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public SupplierReg(String name, String email, String company, String prod, String price, String add) {
        this.name = name;
        this.email = email;
        this.company=company;
        this.product=prod;
        type="sup";
        this.price=price;
        this.add=add;
    }
}

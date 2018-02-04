package com.hackrupt.yolo;

/**
 * Created by admin on 2/4/2018.
 */

public class TenderReg {
    public String name;
    public String email;
    public String company;
    public String areaBuilt;
    public String duration;
    public String price;
    public String type;

    public TenderReg() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public TenderReg(String name, String email, String company, String experience, String projects, String price) {
        this.name = name;
        this.email = email;
        this.company=company;
        this.areaBuilt=experience;
        this.duration=projects;
        type="ten";
        this.price=price;
    }
}

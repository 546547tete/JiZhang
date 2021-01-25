package com.example.jizhang.bean;

public class EntriesBean {

    /**
     * hardware_id : A76E533625E3FBD
     * year : 2021
     * month : 1
     * day : 21
     * amount : 997.56
     * description : A rocket. (Test entry)
     * category : Food
     */

    private String hardware_id;
    private int year;
    private int month;
    private int day;
    private double amount;
    private String description;
    private String category;

    public String getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(String hardware_id) {
        this.hardware_id = hardware_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

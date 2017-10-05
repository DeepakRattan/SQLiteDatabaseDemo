package com.example.deepakrattan.sqlitedatabasedemo;

/**
 * Created by deepak.rattan on 10/5/2017.
 */

public class SingleRow {
    private String name, phone;

    public SingleRow(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

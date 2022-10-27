package com.example.foodcount.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodcount.controllers.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class CustomerModel extends DatabaseController {
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    public CustomerModel(@Nullable Context context, int id, String name, int age, boolean isActive) {
        super(context);
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public CustomerModel(@Nullable Context context) {
        super(context);
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

//    public boolean save() {
//        SQLiteDatabase db = super.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(CUSTOMER_NAME, this.name);
//        cv.put(CUSTOMER_AGE, this.age);
//        cv.put(ACTIVE_CUSTOMER, this.isActive);
//
//        long status = db.insert("CUSTOMER_TABLE", null, cv);
//        if(status == 0) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public List<CustomerModel> getCustomers() {
//        List<CustomerModel> returnList = new ArrayList<>();
//
//        String query = "Select * from " + CUSTOMER_TABLE;
//
//        SQLiteDatabase db = super.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if(cursor.moveToFirst()) {
//            do {
//                int customerId = cursor.getInt(0);
//                String customerName = cursor.getString(1);
//                int customerAge = cursor.getInt(2);
//                boolean isActive = cursor.getInt(3) == 1;
//
//                CustomerModel newCustomer = new CustomerModel(null, customerId, customerName, customerAge, isActive);
//                returnList.add(newCustomer);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return returnList;
//    }
}

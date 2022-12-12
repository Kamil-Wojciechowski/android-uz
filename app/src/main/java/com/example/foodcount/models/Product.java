package com.example.foodcount.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodcount.controllers.DatabaseController;

import java.util.ArrayList;
import java.util.Arrays;

public class Product extends DatabaseController {
    private Integer Id;
    private String name;
    private Double calories;
    private Double proteins;
    private Double carbs;
    private Double fat;
    private byte[] image;
    private Context context;
    SQLiteDatabase db = super.getWritableDatabase();
    SQLiteDatabase dbRead = super.getReadableDatabase();


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return name;
    }

    public Product(@Nullable Context context, String name){
        super(context);
        this.context = context;
        this.name = name;
    }

    public Product(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Product(@Nullable Context context, Integer id) {
        super(context);
        this.context = context;
        this.Id = id;
    }

    public Product(@Nullable Context context, String name, Double calories, Double proteins, Double carbs, Double fat, byte[] image) {
        super(context);
        this.context = context;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.image = image;
    }

    public Product(@Nullable Context context, Integer id, String name, Double calories, Double proteins, Double carbs, Double fat, byte[] image) {
        super(context);
        this.context = context;
        this.Id = id;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.image = image;
    }

    public ArrayList<Product> read() {
        Cursor cursorProducts = db.rawQuery("Select * from " + PRODUCTS_TABLE, null);

        ArrayList<Product> productArrayList = new ArrayList<>();

        Log.println(Log.WARN, "SYSTEM PRODUCT", "1");

        if (cursorProducts.moveToFirst()) {
            do {
                productArrayList.add(new Product(context,
                        cursorProducts.getInt(0),
                        cursorProducts.getString(1),
                        cursorProducts.getDouble(2),
                        cursorProducts.getDouble(3),
                        cursorProducts.getDouble(4),
                        cursorProducts.getDouble(5),
                        cursorProducts.getBlob(6))
                );
            } while (cursorProducts.moveToNext());
        }

        Log.println(Log.WARN, "SYSTEM PRODUCT", "1");

        cursorProducts.close();
        return productArrayList;
    }

    public boolean save() {
        ContentValues cv = new ContentValues();

        cv.put(super.NAME, this.name);
        cv.put(super.CALORIES, this.calories);
        cv.put(super.PROTEINS, this.proteins);
        cv.put(super.CARBS, this.carbs);
        cv.put(super.FAT, this.fat);
        cv.put(super.IMAGE, this.image);
        long status = db.insert(PRODUCTS_TABLE, null, cv);

        if(status == 0) {
            return false;
        }

        return true;
    }

    public boolean edit() {
        ContentValues cv = new ContentValues();

        cv.put(super.NAME, this.name);
        cv.put(super.CALORIES, this.calories);
        cv.put(super.PROTEINS, this.proteins);
        cv.put(super.CARBS, this.carbs);
        cv.put(super.FAT, this.fat);
        cv.put(super.IMAGE, this.image);
        long status = db.update(PRODUCTS_TABLE, cv, "Id=?", new String[]{Id.toString()});

        if(status == 0) {
            return false;
        }

        return true;
    }

    public boolean delete() {
        long status = db.delete(PRODUCTS_TABLE, "Id=?", new String[]{Id.toString()});

        if(status == 0) {
            return false;
        }

        return true;
    }

    public Product get() {
        Product product = new Product(context, -1);
        Cursor cursorProducts = dbRead.rawQuery("Select * from " + PRODUCTS_TABLE + " where Id="+ Id.toString(), null);

        if(cursorProducts.moveToFirst()) {
            product.setId(cursorProducts.getInt(0));
            product.setName(cursorProducts.getString(1));
            product.setCalories( cursorProducts.getDouble(2));
            product.setProteins(cursorProducts.getDouble(3));
            product.setCarbs(cursorProducts.getDouble(4));
            product.setFat( cursorProducts.getDouble(5));
            product.setImage(cursorProducts.getBlob(6));
        }
        cursorProducts.close();

        return product;
    }


}

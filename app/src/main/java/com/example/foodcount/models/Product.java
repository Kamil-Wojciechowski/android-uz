package com.example.foodcount.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodcount.controllers.DatabaseController;

import java.util.Arrays;

public class Product extends DatabaseController {
    private String name;
    private Double calories;
    private Double proteins;
    private Double carbs;
    private Double fat;
    private byte[] image;

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
        return "Product{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public Product(@Nullable Context context, String name, Double calories, Double proteins, Double carbs, Double fat) {
        super(context);
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
    }

    public Product(@Nullable Context context, String name, Double calories, Double proteins, Double carbs, Double fat, byte[] image) {
        super(context);
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.image = image;
    }

    public boolean save() {
        SQLiteDatabase db = super.getWritableDatabase();
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


}

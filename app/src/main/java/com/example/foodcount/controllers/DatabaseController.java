package com.example.foodcount.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodcount.models.CustomerModel;

public class DatabaseController extends SQLiteOpenHelper {

    public static final String PRODUCTS_TABLE = "products_table";
    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String CALORIES = "Calories";
    public static final String PROTEINS = "Proteins";
    public static final String CARBS = "Carbs";
    public static final String FAT = "Fat";
    public static final String IMAGE = "Image";

    public DatabaseController(@Nullable Context context) {
        super(context, "foodcount.db", null, 1);
        Log.println(Log.INFO, "System - DB", "Creating Database");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.println(Log.INFO, "System - DB", "Creating Tables");
        String createTableStatement = "CREATE TABLE " + PRODUCTS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + CALORIES + " REAL, " + PROTEINS + " REAL, " + CARBS + " REAL, " + FAT + " REAL, " + IMAGE + " BLOB )";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

package com.example.foodcount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String ID = "ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "foodcount.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, " + ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME, customerModel.getName());
        cv.put(CUSTOMER_AGE, customerModel.getAge());
        cv.put(ACTIVE_CUSTOMER, customerModel.isActive());

        long status = db.insert(CUSTOMER_TABLE, null, cv);
        if(status == 0) {
            return false;
        }

        return true;
    }

//    public List<CustomerModel> getEveryone() {
//        List<CustomerModel> returnList = new ArrayList<>();
//
//        String query = "Select * from " + CUSTOMER_TABLE;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if(cursor.moveToFirst()) {
//            do {
//                int customerId = cursor.getInt(0);
//                String customerName = cursor.getColumnName(1);
//                int customerAge = cursor.getInt(2);
//                boolean isActive = cursor.getInt(3) == 1;
//
//                CustomerModel newCustomer = new CustomerModel(customerId, customerName, customerAge, isActive);
//                returnList.add(newCustomer);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//
//
//        return returnList;
//    }
}

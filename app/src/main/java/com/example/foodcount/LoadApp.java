package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class LoadApp extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        if(checkData()) {
            startActivity(new Intent(LoadApp.this, SecondActivity.class));
        } else {
            startActivity(new Intent(LoadApp.this, MainActivity.class));
        }

    }

    private Boolean checkData() {
        String firstname = sharedpreferences.getString("firstname","");
        String lastname = sharedpreferences.getString("lastname","");
        //Date age = prefs.getInt("age",0);
        String email = sharedpreferences.getString("email","");
        String phoneNumber = sharedpreferences.getString("phoneNumber","");

        Log.println(Log.INFO, "System", firstname);
        Log.println(Log.INFO, "System", lastname);
        Log.println(Log.INFO, "System", email);
        Log.println(Log.INFO, "System", phoneNumber);

        return (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty());
    }

}
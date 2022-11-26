package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class LoadApp extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        if(checkData()) {
            startActivity(new Intent(LoadApp.this, MainActivity.class));
        } else {
            startActivity(new Intent(LoadApp.this, LoginActivity.class));
        }

    }

    private Boolean checkData() {
        String firstname = sharedpreferences.getString("firstname","");
        String lastname = sharedpreferences.getString("lastname","");
        String age = sharedpreferences.getString("age","");
        String email = sharedpreferences.getString("email","");
        String phoneNumber = sharedpreferences.getString("phoneNumber","");

        Log.println(Log.INFO, "System", firstname);
        Log.println(Log.INFO, "System", lastname);
        Log.println(Log.INFO, "System", email);
        Log.println(Log.INFO, "System", phoneNumber);

        return (!isBlank(firstname) && !isBlank(lastname) && !isBlank(age) && !isBlank(email) && !isBlank(phoneNumber));
    }

    private Boolean isBlank(String text) {
        return (text.trim().length() == 0);
    }

}
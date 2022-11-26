package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt_userSettings, bt_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_userSettings=findViewById(R.id.bt_user_settings);
        bt_products = findViewById(R.id.bt_products);

        bt_userSettings.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserSettings.class)));

        bt_products.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Products.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "Hello! We are starting application.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Welcome back to the main activity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Toast.makeText(MainActivity.this, "Resumed!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Toast.makeText(MainActivity.this, "You paused me!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Toast.makeText(MainActivity.this, "Bye bye!", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "I'm dying", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
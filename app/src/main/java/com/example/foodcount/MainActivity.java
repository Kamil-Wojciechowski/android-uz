package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt_userSettings, bt_products, bt_canvas_draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_userSettings=findViewById(R.id.bt_user_settings);
        bt_products = findViewById(R.id.bt_products);
        bt_canvas_draw = findViewById(R.id.bt_canvas_draw);

        bt_userSettings.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserSettings.class)));

        bt_products.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Products.class)));

        bt_canvas_draw.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProductsDrawImage.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "Hello! We are starting application.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Welcome back to the main activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Toast.makeText(MainActivity.this, "Resumed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Toast.makeText(MainActivity.this, "You paused me!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Toast.makeText(MainActivity.this, "Bye bye!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "I'm dying", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
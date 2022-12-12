package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodcount.fragments.BlankFragment1;
import com.example.foodcount.fragments.BlankFragment2;
import com.example.foodcount.fragments.Communicator;

import org.jetbrains.annotations.TestOnly;

public class MainActivity extends AppCompatActivity implements Communicator {

    Button bt_userSettings, bt_products;
    FragmentManager fragmentManager;


    @Override
    public void passData(String textInput) {
        Bundle bundle = new Bundle();
        bundle.putString("message", textInput);

        FragmentTransaction fragment = fragmentManager.beginTransaction();
        BlankFragment2 bFragment2 = new BlankFragment2();

        bFragment2.setArguments(bundle);

        fragment.replace(R.id.fragment_container2, bFragment2).commit();
    }

    @Override
    public void clearData() {
        Bundle bundle = new Bundle();
        bundle.putString("message", "");

        BlankFragment1 bfragment1 = new BlankFragment1();

        bfragment1.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.fragment_container, bfragment1).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("FoodCount.MainActivity", "Starting the application");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_userSettings=findViewById(R.id.bt_user_settings);
        bt_products = findViewById(R.id.bt_products);
        BlankFragment1 bFragment1 = new BlankFragment1();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, bFragment1).commit();

        bt_userSettings.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserSettings.class)));
        bt_products.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Products.class)));
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
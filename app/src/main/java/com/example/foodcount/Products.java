package com.example.foodcount;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Products extends AppCompatActivity {

    Button bt_addProducts, bt_backProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initializer();

        bt_addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this, ProductsAdd.class));
            }
        });

        bt_backProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initializer() {
        bt_addProducts=findViewById(R.id.bt_addProducts);
        bt_backProducts=findViewById(R.id.bt_backProducts);
    }
}
package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodcount.adapters.ProductsAdapter;
import com.example.foodcount.models.Product;
import java.util.List;

public class Products extends AppCompatActivity implements ProductsAdapter.OnProductListener {

    Button bt_addProducts, bt_backProducts;
    RecyclerView rv_products;
    ProductsAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initializer();

        loadData();

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
        rv_products=findViewById(R.id.rv_products);

    }

    private void loadData() {
        Product prodct = new Product(this);
        List<Product> productsList = prodct.read();

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv_products.setLayoutManager(lm);

        productAdapter = new ProductsAdapter(this, productsList, this);
        rv_products.setAdapter(productAdapter);


    }

    @Override
    public void onProductClick(int position) {
        startActivity(new Intent(this, ProductsEdit.class).putExtra("position", position));
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
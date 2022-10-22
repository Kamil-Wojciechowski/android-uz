package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.bt_add);
        btn_viewAll = findViewById(R.id.bt_viewAll);
        et_age = findViewById(R.id.tx_age);
        et_name = findViewById(R.id.tx_customerName);
        sw_activeCustomer = findViewById(R.id.sw_customerActive);
        lv_customerList = findViewById(R.id.lv_listView);


    }
}
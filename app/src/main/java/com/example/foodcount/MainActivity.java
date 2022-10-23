package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

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

        //btn listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CustomerModel customer = new CustomerModel(MainActivity.this, -1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Log.println(Log.INFO, "System", et_name.getText().toString());
                    Boolean bool = customer.save();
//                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
//                    boolean bool = databaseHelper.addOne(customer);

                    if(bool) {
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CustomerModel> everyone = new CustomerModel(MainActivity.this).getCustomers();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv_customerList.setAdapter(customerArrayAdapter);
            }
        });
    }
}
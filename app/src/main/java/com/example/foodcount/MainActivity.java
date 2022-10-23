package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    EditText tv_firstname, tv_lastname, tx_email, tx_date,tx_phone;
    Button bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_firstname = findViewById(R.id.tx_firstname);
        tv_lastname = findViewById(R.id.tx_lastname);
        tx_date = findViewById(R.id.tx_date);
        tx_email = findViewById(R.id.tx_email);
        tx_phone = findViewById(R.id.tx_phone);
        bt_save = findViewById(R.id.bt_save);



        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateFirstname()) {
                    return;
                }

                Toast.makeText(MainActivity.this, "Sucess!", Toast.LENGTH_LONG).show();
            }
        });

    }

//        btn_add = findViewById(R.id.bt_add);
//        btn_viewAll = findViewById(R.id.bt_viewAll);
//        et_age = findViewById(R.id.tx_age);
//        et_name = findViewById(R.id.tx_customerName);
//        sw_activeCustomer = findViewById(R.id.sw_customerActive);
//        lv_customerList = findViewById(R.id.lv_listView);

        //btn listeners
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    CustomerModel customer = new CustomerModel(MainActivity.this, -1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
//                    Log.println(Log.INFO, "System", et_name.getText().toString());
//                    Boolean bool = customer.save();
//
//                    if(bool) {
//                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//
//        btn_viewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<CustomerModel> everyone = new CustomerModel(MainActivity.this).getCustomers();
//
//                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
//                lv_customerList.setAdapter(customerArrayAdapter);
//            }
//        });
//    }

    private boolean validateFirstname() {
        String val = tv_firstname.getText().toString().trim();

        if(val.isEmpty()) {
            tv_firstname.setError("Field can not be empty!");
            return false;
        }

        tv_firstname.setError(null);
        return true;
        }

    }

package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    EditText tv_firstname, tv_lastname, tx_email, tx_date,tx_phone;
    Button bt_save;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "myprefs";
    private static final String regex = "^(.+)@(.+)[.](.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(checkData()) {
            moveToNext();
        }

        setContentView(R.layout.activity_login);

        tv_firstname = findViewById(R.id.tx_firstname);
        tv_lastname = findViewById(R.id.tx_lastname);
        tx_date = findViewById(R.id.tx_date);
        tx_email = findViewById(R.id.tx_email);
        tx_phone = findViewById(R.id.tx_phone);
        bt_save = findViewById(R.id.bt_save);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateFirstname() | !validateLastname() | !checkDateFormat() | !validateEmail()) {
                    return;
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("firstname", tv_firstname.getText().toString());
                editor.putString("lastname", tv_lastname.getText().toString());
                editor.putString("age", tx_date.getText().toString());
                editor.putString("email", tx_email.getText().toString());
                editor.putString("phoneNumber", tx_phone.getText().toString());
                editor.apply();
                Toast.makeText(LoginActivity.this, "Sucess!", Toast.LENGTH_LONG).show();
                moveToNext();

            }
        });
    }

    private Boolean checkData() {
        String firstname = sharedpreferences.getString("firstname","");
        String lastname = sharedpreferences.getString("lastname","");
        String age = sharedpreferences.getString("age","");
        String email = sharedpreferences.getString("email","");

        return (!isBlank(firstname) & !isBlank(lastname) & !isBlank(age) & !isBlank(email));
    }

    private Boolean isBlank(String text) {
        return (text.trim().length() == 0);


    }

    private void moveToNext() {
        startActivity(new Intent(LoginActivity.this, SecondActivity.class));
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
        String firstname = tv_firstname.getText().toString().trim();

        if(isBlank(firstname)) {
            tv_firstname.setError("Field can not be empty!");
            return false;
        }

        tv_firstname.setError(null);
        return true;
        }

        private boolean validateLastname() {
            String lastname = tv_lastname.getText().toString().trim();

            if(isBlank(lastname)) {
                tv_lastname.setError("Field can not be empty!");
                return false;
            }

            tv_lastname.setError(null);
            return true;
        }

        private Boolean checkDateFormat(){
            String dateStr = tx_date.getText().toString().trim();

            DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                tx_date.setError("Field wrong format!");
                return false;
            }
            return true;
        }

        private boolean validateEmail() {
            String email = tx_email.getText().toString().trim();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if(isBlank(email)) {
                tx_email.setError("Field can not be empty!");
                return false;
            } else if(!matcher.matches()) {
                tx_email.setError("Email in wrong format!!");
                return false;
            }

            tx_email.setError(null);
            return true;
        }

        @Override
        public void onBackPressed() {
            moveTaskToBack(true);
        }
    }

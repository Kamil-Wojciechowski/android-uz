package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSettings extends AppCompatActivity {

    EditText tx_firstname, tx_lastname, tx_email, tx_date, tx_phone;
    Button bt_save, bt_back;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String regex = "^(.+)@(.+)[.](.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        setUpValues();
        insertOldValues();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()) {
                    return;
                }

                save();
                finish();
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpValues() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        tx_firstname = findViewById(R.id.tx_firstname_settings);
        tx_lastname = findViewById(R.id.tx_lastname_settings);
        tx_email = findViewById(R.id.tx_email_settings);
        tx_date = findViewById(R.id.tx_date_settings);
        tx_phone = findViewById(R.id.tx_phone_settings);
        bt_save = findViewById(R.id.bt_save_settings);
        bt_back = findViewById(R.id.bt_back_settings);
    }

    private void insertOldValues() {
        String firstname = sharedpreferences.getString("firstname","");
        String lastname = sharedpreferences.getString("lastname","");
        String age = sharedpreferences.getString("age","");
        String email = sharedpreferences.getString("email","");
        String phone = sharedpreferences.getString("phoneNumber","");

        tx_firstname.setText(firstname);
        tx_lastname.setText(lastname);
        tx_date.setText(age);
        tx_email.setText(email);
        tx_phone.setText(phone);
    }

    private Boolean isBlank(String text) {
        return (text.trim().length() == 0);
    }

    private boolean validateFields() {
        Boolean firstnameChk = false, lastnameChk = false, dateChk = false, emailChk = false;
        String firstname = tx_firstname.getText().toString().trim();
        String lastname = tx_lastname.getText().toString().trim();
        String dateStr = tx_date.getText().toString().trim();
        String email = tx_email.getText().toString().trim();

        if(isBlank(firstname)) {
            tx_firstname.setError("Field can not be empty!");
            firstnameChk = true;
        }

        if(isBlank(lastname)) {
            tx_lastname.setError("Field can not be empty!");
            lastnameChk = true;
        }

        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            tx_date.setError("Field wrong format!");
            dateChk = true;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(isBlank(email)) {
            tx_email.setError("Field can not be empty!");
            emailChk = true;
        } else if(!matcher.matches()) {
            tx_email.setError("Email in wrong format!!");
            emailChk = true;
        }

        if(firstnameChk || lastnameChk || dateChk || emailChk) {
            return true;
        }

        tx_date.setError(null);
        tx_lastname.setError(null);
        tx_firstname.setError(null);
        tx_email.setError(null);

        return false;
    }

    private void save() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("firstname", tx_firstname.getText().toString());
        editor.putString("lastname", tx_lastname.getText().toString());
        editor.putString("age", tx_date.getText().toString());
        editor.putString("email", tx_email.getText().toString());
        editor.putString("phoneNumber", tx_phone.getText().toString());
        editor.apply();
        Toast.makeText(UserSettings.this, "Success!", Toast.LENGTH_LONG).show();
    }
}
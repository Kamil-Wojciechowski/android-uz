package com.example.foodcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.Manifest;


public class LoginActivity extends AppCompatActivity {

    EditText tv_firstname, tv_lastname, tx_email, tx_date,tx_phone;
    Button bt_save;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "myprefs";
    public static final String regex = "^(.+)@(.+)[.](.+)$";

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

                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.POST_NOTIFICATIONS}, 1);

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
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();

                notification();

                finish();

                moveToNext();

            }
        });
    }

    private void notification() {
        NotificationManager mNotificationManager;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(LoginActivity.this, "notify_001");

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle("You logged correctly.");
        bigText.setSummaryText("Hello there! You finally awake.");

        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("You logged in.");
        mBuilder.setContentText("Hello there! You finally awake.");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
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
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

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

            tx_date.setError(null);
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

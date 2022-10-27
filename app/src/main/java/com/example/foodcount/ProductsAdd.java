package com.example.foodcount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodcount.models.Product;

import java.io.ByteArrayOutputStream;

public class ProductsAdd extends AppCompatActivity {


    Button bt_back, bt_addImage, bt_saveProduct;
    ImageView iv_imageProduct;
    EditText tx_name, tx_calories, tx_proteins, tx_carbs, tx_fat;
    private final int GALLERY_REQ_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_add);

        initializer();

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bt_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        bt_saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validator()) {
                    return;
                }

                String name = tx_name.getText().toString().trim();
                Double calories = Double.parseDouble(tx_calories.getText().toString());
                Double proteins = Double.parseDouble(tx_proteins.getText().toString());
                Double carbs = Double.parseDouble(tx_carbs.getText().toString());
                Double fat = Double.parseDouble(tx_fat.getText().toString());
                byte[] imageData = getImage();
                Product item = new Product(ProductsAdd.this, name, calories, proteins, carbs, fat, imageData);

                Boolean save = item.save();

                if(save) {
                    Toast.makeText(ProductsAdd.this, "Success!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ProductsAdd.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @NonNull
    private byte[] getImage() {
        iv_imageProduct.buildDrawingCache();
        Bitmap bitmap = iv_imageProduct.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageData=stream.toByteArray();
        return imageData;
    }

    private void initializer() {
        bt_back=findViewById(R.id.bt_backProductAdd);
        bt_addImage=findViewById(R.id.bt_addImageProduct);
        bt_saveProduct=findViewById(R.id.bt_saveProduct);
        tx_name=findViewById(R.id.tx_productName);
        tx_calories=findViewById(R.id.txn_caloriesProduct);
        tx_proteins=findViewById(R.id.txn_proteinsProduct);
        tx_carbs=findViewById(R.id.txn_carbsProduct);
        tx_fat=findViewById(R.id.txn_fatProduct);
        iv_imageProduct=findViewById(R.id.iv_productImage);
    }

    private boolean validator() {
        Boolean nameChk = false, caloriesChk = false, proteinsChk = false, carbsChk = false, fatChk = false;
        String name = tx_name.getText().toString().trim();
        String calories = tx_calories.getText().toString();
        String proteins = tx_proteins.getText().toString();
        String carbs = tx_carbs.getText().toString();
        String fat = tx_fat.getText().toString();

        Log.println(Log.INFO, "System", name);
        Log.println(Log.INFO, "System", calories.toString());
        Log.println(Log.INFO, "System", proteins.toString());
        Log.println(Log.INFO, "System", carbs.toString());
        Log.println(Log.INFO, "System", fat.toString());


        if(name.isEmpty()) {
            tx_name.setError("Field can not be empty!");
            nameChk = true;
        }

        if(calories.isEmpty()) {
            tx_calories.setError("Field can not be empty.");
            caloriesChk = true;
        } else if(Double.parseDouble(calories) < 0.0) {
            tx_calories.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(proteins.isEmpty()) {
            tx_proteins.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(proteins) < 0.0) {
            tx_proteins.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(carbs.isEmpty()) {
            tx_carbs.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(carbs) < 0.0) {
            tx_carbs.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(fat.isEmpty()) {
            tx_fat.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(fat) < 0) {
            tx_fat.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(nameChk || caloriesChk || proteinsChk || carbsChk || fatChk) {
            return true;
        } else {
            tx_name.setError(null);
            tx_calories.setError(null);
            tx_proteins.setError(null);
            tx_carbs.setError(null);
            tx_fat.setError(null);
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            if(requestCode==GALLERY_REQ_CODE){
                iv_imageProduct.setImageURI(data.getData());
            }
        }
    }
}
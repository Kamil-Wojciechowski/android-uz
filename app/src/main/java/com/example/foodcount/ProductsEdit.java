package com.example.foodcount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodcount.models.Product;

import java.io.ByteArrayOutputStream;

public class ProductsEdit extends AppCompatActivity {

    Button bt_productEditImage, bt_productsEditBack, bt_productsEditSave, bt_productsEditDelete;
    TextView tx_productEditName, txn_productEditCalories, txn_productEditProteins, txn_productEditCarbs, txn_productEditFat;
    ImageView im_productEditImage;
    Integer position;
    Product product;
    private int GALLERY_REQ_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_edit);

        initalizer();
        this.product = new Product(this, position).get();

        setFieldsValue(product);

        bt_productsEditBack.setOnClickListener((view) -> {
            finish();
        });

        bt_productsEditDelete.setOnClickListener((view) -> {
            boolean status = product.delete();

            if(status) {
                Toast.makeText(this, "Item deleted!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });

        bt_productEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        bt_productsEditSave.setOnClickListener((view) -> {
            if(validator()) {
                return;
            }

            String name = tx_productEditName.getText().toString().trim();
            Double calories = Double.parseDouble(txn_productEditCalories.getText().toString());
            Double proteins = Double.parseDouble(txn_productEditProteins.getText().toString());
            Double carbs = Double.parseDouble(txn_productEditCarbs.getText().toString());
            Double fat = Double.parseDouble(txn_productEditFat.getText().toString());
            byte[] imageData = getImage();
            product.setName(name);
            product.setCalories(calories);
            product.setProteins(proteins);
            product.setCarbs(carbs);
            product.setFat(fat);
            product.setImage(imageData);

            Boolean save = product.edit();

            if(save) {
                Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }


        });


    }

    private void initalizer() {
        bt_productEditImage = findViewById(R.id.bt_productEditImage);
        bt_productsEditBack = findViewById(R.id.bt_productsEditBack);
        bt_productsEditSave = findViewById(R.id.bt_productsEditSave);
        bt_productsEditDelete = findViewById(R.id.bt_productsEditDelete);
        tx_productEditName = findViewById(R.id.tx_productEditName);
        txn_productEditCalories = findViewById(R.id.txn_productEditCalories);
        txn_productEditProteins = findViewById(R.id.txn_productEditProteins);
        txn_productEditCarbs = findViewById(R.id.txn_productEditCarbs);
        txn_productEditFat = findViewById(R.id.txn_productEditFat);
        im_productEditImage = findViewById(R.id.im_productEditImage);
        getPosition();
    }

    private void getPosition() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        if(position == 0) {
            finish();
        }
    }

    private void setFieldsValue(Product product) {
        tx_productEditName.setText(product.getName());
        txn_productEditCalories.setText(product.getCalories().toString());
        txn_productEditProteins.setText(product.getProteins().toString());
        txn_productEditCarbs.setText(product.getCarbs().toString());
        txn_productEditFat.setText(product.getFat().toString());
        setImage(product.getImage());
    }

    private void setImage(byte[] image) {
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        im_productEditImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(), false));
    }

    @NonNull
    private byte[] getImage() {
        im_productEditImage.buildDrawingCache();
        Bitmap bitmap = im_productEditImage.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageData=stream.toByteArray();
        return imageData;
    }

    private boolean validator() {
        Boolean nameChk = false, caloriesChk = false, proteinsChk = false, carbsChk = false, fatChk = false;
        String name = tx_productEditName.getText().toString().trim();
        String calories = txn_productEditCalories.getText().toString();
        String proteins = txn_productEditProteins.getText().toString();
        String carbs = txn_productEditCarbs.getText().toString();
        String fat = txn_productEditFat.getText().toString();

        if(name.isEmpty()) {
            tx_productEditName.setError("Field can not be empty!");
            nameChk = true;
        }

        if(calories.isEmpty()) {
            txn_productEditCalories.setError("Field can not be empty.");
            caloriesChk = true;
        } else if(Double.parseDouble(calories) < 0.0) {
            txn_productEditCalories.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(proteins.isEmpty()) {
            txn_productEditProteins.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(proteins) < 0.0) {
            txn_productEditProteins.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(carbs.isEmpty()) {
            txn_productEditCarbs.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(carbs) < 0.0) {
            txn_productEditCarbs.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(fat.isEmpty()) {
            txn_productEditFat.setError("Field can not be empty.");
            caloriesChk = true;
        }
        else if(Double.parseDouble(fat) < 0) {
            txn_productEditFat.setError("Field can not be minus.");
            caloriesChk = true;
        }

        if(nameChk || caloriesChk || proteinsChk || carbsChk || fatChk) {
            return true;
        } else {
            tx_productEditName.setError(null);
            txn_productEditCalories.setError(null);
            txn_productEditProteins.setError(null);
            txn_productEditCarbs.setError(null);
            txn_productEditFat.setError(null);
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            if(requestCode==GALLERY_REQ_CODE){
                im_productEditImage.setImageURI(data.getData());
            }
        }
    }
}
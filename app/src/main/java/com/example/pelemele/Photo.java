package com.example.pelemele;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_image);
        ImageView iv = findViewById(R.id.iv);

        try {
            FileInputStream fis = openFileInput("image.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            iv.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
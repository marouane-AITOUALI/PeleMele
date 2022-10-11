package com.example.pelemele;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PHOTO = 24;
    private ImageView imageView;
    private Button contact;
    private ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.i("MainActivity", "une info");
        Button bonjour = findViewById(R.id.b1);
        bonjour.setOnClickListener(this);
        Button photo = findViewById(R.id.b2);
        Button chrono = findViewById(R.id.b3);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        Bundle extras =  data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");

                        try {
                            FileOutputStream fos = openFileOutput("image.data", MODE_PRIVATE);
                            imageBitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
                            fos.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }
        );
        chrono.setOnClickListener(l->{
            Intent intent = new Intent(this, ChronometreActivity.class);
            startActivity(intent);
        });


        photo.setOnClickListener(l->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcher.launch(intent);
        });

        Button lastImage = findViewById(R.id.lastImage);
        lastImage.setOnClickListener(l->{
            Intent intent = new Intent(this, Photo.class);
            startActivity(intent);
        });

        Button longueActivite = findViewById(R.id.longueActivite);
        longueActivite.setOnClickListener(l->{
            Intent intent = new Intent(getApplicationContext(), LongActivity.class);
            startActivity(intent);
        });
        Button meteo = findViewById(R.id.meteo);
        meteo.setOnClickListener(l->{
            Intent intent = new Intent(getApplicationContext(), MeteoActivity.class);
            startActivity(intent);
        });
        contact = findViewById(R.id.contacts);
        contact.setOnClickListener(l->{
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
            startActivity(intent);
        });

        Button capteur = findViewById(R.id.capteur);
        capteur.setOnClickListener(cl->{
            Intent intent = new Intent(getApplicationContext(), CapteursActivity.class);
            startActivity(intent);
        });

        Button selector = findViewById(R.id.selecteur);
        selector.setOnClickListener(l->{
            Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
            startActivity(intent);
        });




    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void quitter(MenuItem menu){
        System.exit(0);
    }

    public void startChrono(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), ChronometreActivity.class);
        startActivity(intent);
    }

    public void finish(MenuItem item){

    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Bonjour !", Toast.LENGTH_LONG).show();
    }

}
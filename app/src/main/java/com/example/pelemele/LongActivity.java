package com.example.pelemele;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongActivity extends AppCompatActivity {

    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long);
        Button longueActivite = findViewById(R.id.StartLongActivity);
        ProgressBar pb = findViewById(R.id.pb);
        ProgressBar pblong = findViewById(R.id.progressBar);
        longueActivite.setOnClickListener(l->{
            pb.setVisibility(View.VISIBLE);
            pblong.setVisibility(View.VISIBLE);

            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(()->{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            });

}}
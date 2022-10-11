package com.example.pelemele;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;

public class ChronometreActivity extends AppCompatActivity {

    private int hoursStart, hoursEnd,minuteStart, minutesEnd, secondStart, secondEnd;
    private Timestamp date1, date2;
    private boolean isStarted = false;
    private boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometre);
        //Toast.makeText(this,"Bienvenue à chronometre",Toast.LENGTH_LONG).show();
        ImageButton play = findViewById(R.id.play);
        ImageButton stop = findViewById(R.id.pause);
        play.setEnabled(true);
        stop.setEnabled(false);
        play.setOnClickListener(l->{
            play.setEnabled(false);
            stop.setEnabled(true);
            isStarted = true;
            date1= new Timestamp(System.currentTimeMillis());
            hoursStart = date1.getHours();
            minuteStart = date1.getMinutes();
            secondStart = date1.getSeconds();
            Toast.makeText(this, hoursStart+":"+minuteStart+":"+secondStart, Toast.LENGTH_SHORT).show();
        });
        stop.setOnClickListener(listener->{
            if(isStarted){
                stop.setEnabled(false);
                play.setEnabled(true);
                date2 = new Timestamp(System.currentTimeMillis());
                long millis= date2.getTime() - date1.getTime();
                int sec = (int) millis/1000;

                hoursEnd = sec/3600;
                minutesEnd = (sec%3600)/60;
                secondEnd = (sec%3600)%60;
                isStarted = !isStarted;
                Toast.makeText(this, hoursEnd+":"+minutesEnd+":"+secondEnd,Toast.LENGTH_LONG).show();
            }
        });
    }
}
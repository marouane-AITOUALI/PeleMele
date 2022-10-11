package com.example.pelemele;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CapteursActivity extends AppCompatActivity implements SensorEventListener {

    private boolean accisEnabled = false;
    private boolean magnisEnabled = false;
    private SensorManager manager;
    private Sensor sensor;
    private Sensor sensor2;
    private TextView t1,t2,t3,t4,t5,t6;

    private Switch swMagn;
    private Switch swAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capteurs);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);


        swAcc = findViewById(R.id.sw);
        swMagn = findViewById(R.id.sw2);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor2 = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        swAcc.setOnClickListener(l->{
            accisEnabled = !accisEnabled;

        });
        swMagn.setOnClickListener(l->{
            magnisEnabled = !magnisEnabled;
        });

        //accisEnabled = swAcc.isEnabled();
        //magnisEnabled = swMagn.isEnabled();
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(accisEnabled && sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY){
            t1.setText(String.valueOf(sensorEvent.values[0]));
            t2.setText(String.valueOf(sensorEvent.values[0]));
            t3.setText(String.valueOf(sensorEvent.values[0]));
            /*t1.setText(t1.getText() + String.valueOf(sensorEvent.values[0]));
            t2.setText(t2.getText() + String.valueOf(sensorEvent.values[1]));
            t3.setText(t3.getText() + String.valueOf(sensorEvent.values[2]));
            tv1.setText("Affichage des mouvements de l'appareil\n Axis x: "+sensorEvent.values[0]
                    +"m/s²\n"+ "Axis y: "+sensorEvent.values[1]+"m/s²\n"+"Axis z: "
                    +sensorEvent.values[2]+"m/s²");*/
        }

        else if(magnisEnabled && sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            t4.setText(String.valueOf(sensorEvent.values[0]));
            t5.setText(String.valueOf(sensorEvent.values[1]));
            t6.setText(String.valueOf(sensorEvent.values[2]));
            /*t4.setText(t1.getText() + String.valueOf(sensorEvent.values[0]));
            t5.setText(t2.getText() + String.valueOf(sensorEvent.values[1]));
            t6.setText(t3.getText() + String.valueOf(sensorEvent.values[2]));
            tv2.setText("Affichage des mouvements de l'appareil\n Axis x: "+sensorEvent.values[0]
                    + "\nAxis y: "+sensorEvent.values[1]+"\nAxis z: "
                    +sensorEvent.values[2]);*/
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
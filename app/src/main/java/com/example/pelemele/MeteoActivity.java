package com.example.pelemele;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MeteoActivity extends AppCompatActivity {

    private Thread thread;
    private TextView textView;

    private double lat, longi;
    private String url;
    private boolean isGranted = false;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Meteo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        textView = findViewById(R.id.textView);
        permissionGranted();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in = null;
                try {
                    in = new java.net.URL(url).openStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    String descriptionMeteo = "";
                    JSONObject res = readStream(in) ;

                    JSONArray wt = res.getJSONArray("weather");
                    JSONObject desc = wt.getJSONObject(0);
                    String description = desc.getString("description");

                    JSONObject main = res.getJSONObject("main");
                    double temperature = main.getDouble("temp") - 273.15;
                    double humdite = main.getDouble("humidity");

                    JSONObject wind = res.getJSONObject("wind");
                    double windSpeed = wind.getDouble("speed");

                    JSONObject system = res.getJSONObject("sys");
                    String country = system.getString("country");
                    String city = res.getString("name");

                    descriptionMeteo = "        AFFICHAGE METEO   \n    Description: "+description+
                            "\n    PAYS: "+country+"\n    City: " +city+"\n    TEMPERATURE: "
                            +(((int)temperature*100)/100.)+"°C\n    Vent: "+ windSpeed+"m/s"
                            +"\n    Humidite: "+humdite+"%";

                    textView.setText(descriptionMeteo);


                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button meteo = findViewById(R.id.weather);
        meteo.setOnClickListener(l->{
            thread.start();

        });
    }
    public void permissionGranted(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(!isGranted){
            if (ContextCompat.checkSelfPermission(MeteoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MeteoActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 100);
            }
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            getLocation(location);
                        }
                    }
                });
    }

    public void getLocation(Location location) {
        this.longi = location.getLongitude();
        this.lat = location.getLatitude();
        this.url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+longi
        +"&appid=47b23c2d907f76b8d09c3db25c80a6ed";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            isGranted = true;
            permissionGranted();
        }
        else{
            Toast.makeText(this, "Permission Denied for Position",
                    Toast.LENGTH_SHORT).show();
        }
    }



    private JSONObject readStream(InputStream is) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for(String line = r.readLine(); line != null;line=r.readLine()){
            sb.append(line);
        }
        return new JSONObject(sb.toString());
    }




}
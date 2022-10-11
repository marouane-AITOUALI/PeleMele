package com.example.pelemele;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    private ListView viewContacts;
    private ArrayList<String> listCont = new ArrayList<String>();

    private boolean isGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        viewContacts = findViewById(R.id.listView);
        ArrayAdapter<String> data = new ArrayAdapter<>(this, R.layout.activity_list, R.id.cont,listCont);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }



        Cursor phones = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                     new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                             ContactsContract.CommonDataKinds.Phone.NUMBER},null, null);
        }


        while(phones.moveToNext()){
            @SuppressLint("Range") String name = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
            @SuppressLint("Range") String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.
                    Phone.NUMBER));

            listCont.add(name+": "+phoneNumber+"\n");
            viewContacts.setAdapter(data);
            //Log.i("phone", name+" "+phoneNumber);
        }



        //viewContacts.setAdapter(data);
        phones.close();


    }


}




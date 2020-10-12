package com.example.accesssettings;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.*;


public class MainActivity extends AppCompatActivity {

    Button cancel_btn;
    Button continue_btn;
    Switch storage_switch_btn;
    Switch location_switch_btn;
    Switch camara_switch_btn;
    Switch phone_witch_btn;
    Switch contacts_witch_btn;
    List<String> request_permissions_array = new ArrayList<>();
    public static final String NOT_GRANTED_PERMISSION = "notGranted";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cancel_btn = findViewById(R.id.button2);

        //Search switch ids componentents
        storage_switch_btn = findViewById(R.id.switch6);
        location_switch_btn = findViewById(R.id.switch7);
        camara_switch_btn = findViewById(R.id.switch8);
        phone_witch_btn = findViewById(R.id.switch9);
        contacts_witch_btn = findViewById(R.id.switch10);
        continue_btn = findViewById(R.id.button_continue);

        //close the application
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

        //Activities workflow - permissions
        check_already_granted_permission();

    }

    //check for already granted permissions

    private Boolean check_already_granted_permission(){

         if(ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
             storage_switch_btn.setChecked(true);
             storage_switch_btn.setClickable(false);

         }
         if(ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
             location_switch_btn.setChecked(true);
             location_switch_btn.setClickable(false);

         }
         if(ContextCompat.checkSelfPermission(MainActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED){
             camara_switch_btn.setChecked(true);
             camara_switch_btn.setClickable(false);

         }
         if(ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
             phone_witch_btn.setChecked(true);
             phone_witch_btn.setClickable(false);

         }
         if(ContextCompat.checkSelfPermission(MainActivity.this, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
             contacts_witch_btn.setChecked(true);
             contacts_witch_btn.setClickable(false);

         }
        return false;
    }

    //Call when clicked continue button
    public void when_clicked_continue_btn(View btn){
           Intent continue_btn_intent = new Intent(this, given_permission.class);

           if(!storage_switch_btn.isChecked()){
               request_permissions_array.add(READ_EXTERNAL_STORAGE);
           }
           if(!location_switch_btn.isChecked()){
               request_permissions_array.add(ACCESS_FINE_LOCATION);
           }
           if(!camara_switch_btn.isChecked()){
               request_permissions_array.add(CAMERA);
           }
           if(!phone_witch_btn.isChecked()){
               request_permissions_array.add(CALL_PHONE);
           }
           if(!contacts_witch_btn.isChecked()){
               request_permissions_array.add(READ_CONTACTS);
           }
           if(!request_permissions_array.isEmpty()){
               ActivityCompat.requestPermissions(this, new String[]{request_permissions_array.toString()}, 200);

           }


           startActivity(continue_btn_intent);

    }




}
package com.example.accesssettings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    List<String> request_permissions_array = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //close the application
        cancel_btn = findViewById(R.id.button2);

        //Search switch ids componentents
        storage_switch_btn = findViewById(R.id.switch6);
        location_switch_btn = findViewById(R.id.switch7);
        camara_switch_btn = findViewById(R.id.switch8);
        continue_btn = findViewById(R.id.button_continue);


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });


        //Activities workflow - permissions

        check_storage_already_granted_permission();
        check_location_already_granted_permission();
        check_camara_already_granted_permission();


    }

    //check for already granted permissions

    private Boolean check_storage_already_granted_permission(){

     if(ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
         storage_switch_btn.setChecked(true);
     }
        return false;
    }
    private Boolean check_location_already_granted_permission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
          location_switch_btn.setChecked(true);
        }
        return false;
    }
    private Boolean check_camara_already_granted_permission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED){
            camara_switch_btn.setChecked(true);
        }
        return false;
    }


    //Call when clicked continue button
    public void when_clicked_continue_btn(View btn){
       Intent continue_btn_intent = new Intent(this, given_permission.class);
       startActivity(continue_btn_intent);

    }



    private String request_missing_permissins(){

                if(!check_storage_already_granted_permission() ){
                    request_permissions_array.add(READ_EXTERNAL_STORAGE);
                }
                if(!check_location_already_granted_permission()){
                    request_permissions_array.add(ACCESS_FINE_LOCATION);
                }
                if(!check_camara_already_granted_permission()){
                    request_permissions_array.add(CAMERA);
                }

        return "";

    }




}
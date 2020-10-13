package com.example.accesssettings;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.*;


public class MainActivity extends AppCompatActivity {

    Switch storage_switch_btn;
    Switch location_switch_btn;
    Switch camara_switch_btn;
    Switch phone_switch_btn;
    Switch contacts_switch_btn;
    List<String> request_permissions_array = new ArrayList<>();
    public static final int CODE_REQUEST_PERMISSION = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cancel_btn = findViewById(R.id.button2);

        //Search switch ids componentents
        storage_switch_btn = findViewById(R.id.switch6);
        location_switch_btn = findViewById(R.id.switch7);
        camara_switch_btn = findViewById(R.id.switch8);
        phone_switch_btn = findViewById(R.id.switch9);
        contacts_switch_btn = findViewById(R.id.switch10);
        Button continue_btn = findViewById(R.id.button_continue);

        //close the application
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

        check_already_granted_permission();
    }

    private void check_already_granted_permission(){

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
             phone_switch_btn.setChecked(true);
             phone_switch_btn.setClickable(false);
         }
         if(ContextCompat.checkSelfPermission(MainActivity.this, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
             contacts_switch_btn.setChecked(true);
             contacts_switch_btn.setClickable(false);
         }
    }

    public void when_clicked_continue_btn(View continue_btn){

        Intent continue_btn_intent = new Intent(continue_btn.getContext(), given_permission.class);
        if(storage_switch_btn.isChecked() ){
            if(ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                request_permissions_array.add(READ_EXTERNAL_STORAGE);
            }
        }
        if(location_switch_btn.isChecked() ){
            if(ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                request_permissions_array.add(ACCESS_FINE_LOCATION);
            }
        }
        if(camara_switch_btn.isChecked() ){
            if(ContextCompat.checkSelfPermission(MainActivity.this, CAMERA) != PackageManager.PERMISSION_GRANTED){
                request_permissions_array.add(CAMERA);
            }
        }
        if(phone_switch_btn.isChecked() ){
            if(ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                request_permissions_array.add(CALL_PHONE);
            }
        }
        if(contacts_switch_btn.isChecked() ){
            if(ContextCompat.checkSelfPermission(MainActivity.this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                request_permissions_array.add(READ_CONTACTS);
            }
        }
        if(!request_permissions_array.isEmpty()){
            ActivityCompat.requestPermissions(MainActivity.this, request_permissions_array.toArray(new String[0]), CODE_REQUEST_PERMISSION);

        }else {
            startActivity(continue_btn_intent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if(requestCode == CODE_REQUEST_PERMISSION ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Snackbar.make(contacts_switch_btn, "Permiso otorgado", Snackbar.LENGTH_SHORT).show();
                Intent continue_btn_intent = new Intent(getApplicationContext(), given_permission.class);
                startActivity(continue_btn_intent);

            } else {
                Snackbar.make(contacts_switch_btn, "El permiso no fue otorgado", Snackbar.LENGTH_SHORT).show();
            }

        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


}
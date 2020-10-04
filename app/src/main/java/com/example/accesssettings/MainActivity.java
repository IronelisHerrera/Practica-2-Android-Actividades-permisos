package com.example.accesssettings;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    Button cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //close the application
        cancel_btn = findViewById(R.id.button2);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

    }

    //Call when clicked continue botton



}
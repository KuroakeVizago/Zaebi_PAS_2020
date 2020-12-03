package com.example.zaebi_pas_2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        /****** Create Thread that will sleep for 3 seconds****/
        Thread background = new Thread() {
            public void run() {

                    // Thread will sleep for 3 seconds
                try {
                    sleep(3*1000);
                    Intent i=new Intent(getBaseContext(), ControlClass.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        // start thread
        background.start();
    }
    }
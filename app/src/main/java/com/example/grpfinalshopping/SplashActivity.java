package com.example.grpfinalshopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },3000);
    }
}
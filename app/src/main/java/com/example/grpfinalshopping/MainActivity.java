package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BTN_AddApple, BTN_AddBanana, BTN_AddCherry, BTN_AddGrape, BTN_AddOrange,
            BTN_AddCarrot, BTN_AddCelery, BTN_AddOnion, BTN_AddPotato, BTN_AddTomato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BTN_AddApple=(Button) findViewById(R.id.BTN_AddApple);
        BTN_AddBanana=(Button) findViewById(R.id.BTN_AddBanana);
        BTN_AddCherry=(Button) findViewById(R.id.BTN_AddCherry);
        BTN_AddGrape=(Button) findViewById(R.id.BTN_AddGrape);
        BTN_AddOrange=(Button) findViewById(R.id.BTN_AddOrange);
        BTN_AddCarrot=(Button) findViewById(R.id.BTN_AddCarrot);
        BTN_AddCelery=(Button) findViewById(R.id.BTN_AddCelery);
        BTN_AddOnion=(Button) findViewById(R.id.BTN_AddOnion);
        BTN_AddPotato=(Button) findViewById(R.id.BTN_AddPotato);
        BTN_AddTomato=(Button) findViewById(R.id.BTN_AddTomato);


        //BTN_AddApple.setOnClickListener(this);
        //BTN_AddBanana.setOnClickListener(this);
        //BTN_AddCherry.setOnClickListener(this);
        //BTN_AddGrape.setOnClickListener(this);
        //BTN_AddOrange.setOnClickListener(this);
        //BTN_AddCarrot.setOnClickListener(this);
        //BTN_AddCelery.setOnClickListener(this);
        //BTN_AddOnion.setOnClickListener(this);
        //BTN_AddPotato.setOnClickListener(this);
        //BTN_AddPotato.setOnClickListener(this);
        //BTN_AddTomato.setOnClickListener(this);


    }
}
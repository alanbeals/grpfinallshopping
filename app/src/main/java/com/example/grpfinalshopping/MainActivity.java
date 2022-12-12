package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        lstView = (ListView) findViewById(R.id.LV_Items);

    }

    public void OpenCart(View view){
        Intent i=new Intent(getApplicationContext(),CheckoutActivity.class);
        startActivity(i);
        finish();
    }

    public void OpenSettings(View view){
        Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void addItemToCart(View view){
        Button btn = findViewById(view.getId());

        dbHelper.addToCart(1, btn.getId());
    }
}
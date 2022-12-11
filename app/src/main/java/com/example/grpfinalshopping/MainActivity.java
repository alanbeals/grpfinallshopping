package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grpfinalshopping.R;

public class MainActivity extends AppCompatActivity {

    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTest = findViewById(R.id.btnTest);
        DbHelper dbHelper = new DbHelper(this);
//        dbHelper.addUser("Luke Skywalker 2", "111-111-1111", "111 Some Galaxy", "Death Star 2", "BC", "A2A 2A2");
//
//        dbHelper.addProduct("Apple", 1);
//        dbHelper.addProduct("Banana", 2);
//        dbHelper.addProduct("Strawberry", 5.2);
//        dbHelper.addProduct("Kiwi", 8.5);

//        dbHelper.updateUserProfile(1,"Luke Skywalker", "111-111-1111", "111 Some Galaxy", "Death Star 2", "BC", "A2A 2A2");
//
//        dbHelper.addToCart(1, 1);
//        dbHelper.addToCart(1, 1);
//        dbHelper.addToCart(1, 2);


    }

}
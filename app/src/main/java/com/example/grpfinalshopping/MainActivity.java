package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;

import entities.Order;
import entities.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);

//        dbHelper.addUser("Anakin Skywalker", "111-111-1111", "111 Some Galaxy, Kitchener, ON, A2A 2A2");
//        dbHelper.updateUserProfile(1,"Luke Skywalker 2", "222-222-2222", "222 Some Galaxy, Waterloo, ON, B1B 1B1");

//        dbHelper.addProduct("Apple", 1);
//        dbHelper.addProduct("Banana", 2);
//        dbHelper.addProduct("Strawberry", 5.2);
//        dbHelper.addProduct("Kiwi", 8.5);
//
//        dbHelper.addToCart(1, 3);
//        dbHelper.addToCart(1, 3);
//        dbHelper.addToCart(1, 3);

//        ArrayList<Product> products = dbHelper.getAllProducts();

//        User user = dbHelper.getUserById(1);

//        dbHelper.placeOrder(1);

//        ArrayList<Order> orders = dbHelper.getCompletedOrdersByUserId(1);
//        Order order = dbHelper.getCartByUserId(1);

    }

    public void OpenCart(View view){

    }
}
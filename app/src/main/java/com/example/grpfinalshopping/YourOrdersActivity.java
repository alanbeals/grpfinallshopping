package com.example.grpfinalshopping;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.DefaultLifecycleObserver;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import entities.Order;
import entities.OrderItem;

public class YourOrdersActivity extends AppCompatActivity {

    DbHelper dbHelper;

    GridLayout productsListContainer;
    TextView pastOrdersIsEmpty;

    ArrayList<Order> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        productsListContainer = findViewById(R.id.GL_ProductsListContainer);
        pastOrdersIsEmpty = findViewById(R.id.pastOrdersIsEmptyTV);

        pastOrdersIsEmpty.setVisibility(View.INVISIBLE);

        dbHelper = new DbHelper(this);

        order = dbHelper.getCompletedOrdersByUserId(1);

        if(order.size() == 0) {
            pastOrdersIsEmpty.setVisibility(View.VISIBLE);
            return;
        }

        for(int i=0; i < order.size(); i++) {

            ImageView deliveredImage = new ImageView(this);
            TextView shippingAddress = new TextView(this);
            TextView datePlaced = new TextView(this);
            TextView orderTotal = new TextView(this);

            ViewGroup.LayoutParams gridParams = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(1));
            ViewGroup.LayoutParams gridParamsForImage = new GridLayout.LayoutParams(GridLayout.spec(i + i * 2, 3), GridLayout.spec(0));

            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linearParams.setMargins(20, 0, 0, 0);
            linearParams.width = 900;

            LinearLayout.LayoutParams linearParamsForPrice = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linearParamsForPrice.setMargins(20, 0, 0, 90);

            shippingAddress.setTextSize(20);
            shippingAddress.setPadding(0, 10, 0,0);
            shippingAddress.setText(order.get(i).getShippingAddress());
            shippingAddress.setLayoutParams(gridParams);
            shippingAddress.setLayoutParams(linearParams);
            shippingAddress.setTextAppearance(R.style.fontForYourOrdersList);
            shippingAddress.setGravity(0);

            String formattedDate = order.get(i).getOrderDate().substring(0, order.get(i).getOrderDate().indexOf("T"));

            datePlaced.setTextSize(20);
            datePlaced.setPadding(0, 10, 0,0);
            datePlaced.setText("Order placed on: " + formattedDate);
            datePlaced.setLayoutParams(gridParams);
            datePlaced.setLayoutParams(linearParams);
            datePlaced.setTextAppearance(R.style.fontForYourOrdersList);
            datePlaced.setGravity(0);

            orderTotal.setTextSize(20);
            orderTotal.setPadding(0, 10, 0,0);
            orderTotal.setText("Total: $" + order.get(i).getOrderTotalPrice(order.get(i).getId()));
            orderTotal.setLayoutParams(gridParams);
            orderTotal.setLayoutParams(linearParamsForPrice);
            orderTotal.setTextAppearance(R.style.fontForYourOrdersList);
            orderTotal.setTypeface(null, Typeface.BOLD);

            int productDrawableId = this.getResources().getIdentifier("order_delivered_icon", "drawable", this.getPackageName());
            deliveredImage.setImageResource(productDrawableId);
            deliveredImage.setLayoutParams(gridParamsForImage);

            if (productsListContainer != null) {
                productsListContainer.addView(deliveredImage);
                productsListContainer.addView(shippingAddress);
                productsListContainer.addView(datePlaced);
                productsListContainer.addView(orderTotal);
            }
//
            deliveredImage.getLayoutParams().height = 120;
            deliveredImage.getLayoutParams().width = 120;
        }
    }

    public void PlaceOrder(View view){
        dbHelper.placeOrder(1);
        Toast.makeText(this, "Order Placed", Toast.LENGTH_LONG).show();

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
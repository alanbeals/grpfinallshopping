package com.example.grpfinalshopping;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Locale;

import entities.Order;
import entities.OrderItem;
import entities.Product;

public class CheckoutActivity extends AppCompatActivity {

    DbHelper dbHelper;

    GridLayout productsListContainer;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        productsListContainer = findViewById(R.id.GL_ProductsListContainer);

        dbHelper = new DbHelper(this);

        order = dbHelper.getCartByUserId(1);

        for(int i=0; i < order.getOrderItems().size(); i++) {

            OrderItem orderItem = order.getOrderItems().get(i);
            TextView productName = new TextView(this);
            TextView productPrice = new TextView(this);
            Button removeFromCartBtn = new Button(this);
            ImageView productImage = new ImageView(this);

            ViewGroup.LayoutParams gridParams = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(1));

            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linearParams.setMargins(0, 0, 110, 100);

            productName.setTextSize(20);
            productName.setTypeface(null, Typeface.BOLD);
            productName.setPadding(0, 10, 0,0);
            productName.setText(orderItem.getProduct().getProductName());
            productName.setLayoutParams(gridParams);
            productName.setLayoutParams(linearParams);

            productPrice.setTextSize(20);
            productPrice.setTypeface(null, Typeface.BOLD);
            productPrice.setPadding(0, 10, 0,0);
            productPrice.setText(String.format("$%.2f", orderItem.getProduct().getProductPrice()));
            productPrice.setLayoutParams(gridParams);
            productPrice.setLayoutParams(linearParams);

            removeFromCartBtn.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );

            removeFromCartBtn.setText("remove");

            removeFromCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CheckoutActivity.this, orderItem.getProduct().getProductName() + ": removed from cart", Toast.LENGTH_LONG).show();
//                    dbHelper.addToCart(1, product.getProductId());
                }
            });

            productImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.apple, null));

            int productDrawableId = this.getResources().getIdentifier(orderItem.getProduct().getProductName().toLowerCase(Locale.ROOT), "drawable", this.getPackageName());

            productImage.setImageResource(productDrawableId);
            productImage.setLayoutParams(linearParams);

            if (productsListContainer != null) {
                productsListContainer.addView(productImage);
                productsListContainer.addView(productName);
                productsListContainer.addView(productPrice);
                productsListContainer.addView(removeFromCartBtn);
            }

            productImage.getLayoutParams().height = 120;
            productImage.getLayoutParams().width = 120;
        }
    }

    public void PlaceOrder(View view){
        dbHelper.placeOrder(1);
        Toast.makeText(this, "Order Placed", Toast.LENGTH_LONG).show();

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

}
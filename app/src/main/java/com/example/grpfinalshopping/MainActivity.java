package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import entities.Product;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;

    GridLayout productsListContainer;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsListContainer = findViewById(R.id.GL_ProductsListContainer);

        dbHelper = new DbHelper(this);

        products = dbHelper.getAllProducts();

        for(int i=0; i < products.size(); i++) {

            Product product = products.get(i);
            TextView productName = new TextView(this);
            TextView productPrice = new TextView(this);
            ImageView productImage = new ImageView(this);
            ImageView addToCartBtn = new ImageView(this);

            ViewGroup.LayoutParams gridParams = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(1));

            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linearParams.setMargins(0, 0, 150, 100);

            productName.setTextSize(20);
            productName.setTypeface(null, Typeface.BOLD);
            productName.setPadding(0, 10, 0,0);
            productName.setText(product.getProductName());
            productName.setLayoutParams(gridParams);
            productName.setLayoutParams(linearParams);

            productPrice.setTextSize(20);
            productPrice.setTypeface(null, Typeface.BOLD);
            productPrice.setPadding(0, 10, 0,0);
            productPrice.setText(String.format("$%.2f", product.getProductPrice()));
            productPrice.setLayoutParams(gridParams);
            productPrice.setLayoutParams(linearParams);

//            addToCartBtn.setLayoutParams(
//                new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//            );

            addToCartBtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.add_icon, null));
            addToCartBtn.setClickable(true);

//            addToCartBtn.setText("Add");

//            addToCartBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(MainActivity.this, product.getProductName() + ": added to cart", Toast.LENGTH_LONG).show();
//                    dbHelper.addToCart(1, product.getId());
//                }
//            });

            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, product.getProductName() + ": added to cart", Toast.LENGTH_SHORT).show();
                    dbHelper.addToCart(1, product.getId());
                }
            });

            productImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.apple, null));

            int productDrawableId = this.getResources().getIdentifier(product.getProductName().toLowerCase(Locale.ROOT), "drawable", this.getPackageName());

            productImage.setImageResource(productDrawableId);
            productImage.setLayoutParams(linearParams);

            if (productsListContainer != null) {
                productsListContainer.addView(productImage);
                productsListContainer.addView(productName);
                productsListContainer.addView(productPrice);
                productsListContainer.addView(addToCartBtn);
            }

            productImage.getLayoutParams().height = 120;
            productImage.getLayoutParams().width = 120;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.profileMenu:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(i);
                    }
                },0);
                return true;
            case R.id.yourCartMenu:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), CheckoutActivity.class);
                        startActivity(i);
                    }
                }, 0);
                return true;
            default:
                return true;
        }
    }
}
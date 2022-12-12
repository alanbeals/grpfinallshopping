package com.example.grpfinalshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import entities.Product;

public class MainActivity extends AppCompatActivity {

    GridLayout productsListContainer;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsListContainer = findViewById(R.id.GL_ProductsListContainer);

        DbHelper dbHelper = new DbHelper(this);

        products = dbHelper.getAllProducts();

        for(int i=0; i < products.size(); i++) {

            Product product = products.get(i);
            TextView productName = new TextView(this);
            TextView productPrice = new TextView(this);
            Button addToCartBtn = new Button(this);
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
            productName.setText(product.getProductName());
            productName.setLayoutParams(gridParams);
            productName.setLayoutParams(linearParams);

            productPrice.setTextSize(20);
            productPrice.setTypeface(null, Typeface.BOLD);
            productPrice.setPadding(0, 10, 0,0);
            productPrice.setText(String.format("$%.2f", product.getProductPrice()));
            productPrice.setLayoutParams(gridParams);
            productPrice.setLayoutParams(linearParams);

            addToCartBtn.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
            );

            addToCartBtn.setText("Add");

            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.addToCart(1, product.getProductId());
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

//        dbHelper.addUser("Anakin Skywalker", "111-111-1111", "111 Some Galaxy, Kitchener, ON, A2A 2A2");
//
//        dbHelper.addProduct("Apple", 0.99);
//        dbHelper.addProduct("Banana", 0.3);
//        dbHelper.addProduct("Cherry", 3.97);
//        dbHelper.addProduct("Grape", 8.75);
//        dbHelper.addProduct("Orange", 0.92);
//        dbHelper.addProduct("Carrot", 2.47);
//        dbHelper.addProduct("Celery", 2.97);
//        dbHelper.addProduct("Onion", 2.47);
//        dbHelper.addProduct("Potato", 3.97);
//        dbHelper.addProduct("Tomato", 0.78);


    }

    public void OpenCart(View view){

    }
}
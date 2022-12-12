package com.example.grpfinalshopping;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import entities.User;

public class ProfileActivity  extends AppCompatActivity {

    EditText name, address, number;
    DbHelper dbHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new DbHelper(this);

        name = (EditText) findViewById(R.id.ET_Name);
        address = (EditText) findViewById(R.id.RY_Email);
        number = (EditText) findViewById(R.id.ET_Phone);

        user = dbHelper.getUserById(1);

        if (user.getUserId() == 0){
            dbHelper.addUser("", "", "");
        }
        name.setText(user.getFullName());
        address.setText(user.getAddress());
        number.setText(user.getPhone());

        dbHelper = new DbHelper(this);
    }

    public void ReturnHome(View view){
        if (!Pattern.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", number.getText().toString())){
            Toast.makeText(this, "Phone number not in a recognized format", Toast.LENGTH_LONG).show();
            return;
        }

        dbHelper.updateUserProfile(1,
                name.getText().toString(),
                number.getText().toString(),
                address.getText().toString());

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
package com.example.grpfinalshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity  extends AppCompatActivity {

    TextView name, address, number;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.ET_Name);
        address = (TextView) findViewById(R.id.RY_Email);
        number = (TextView) findViewById(R.id.ET_Phone);

        dbHelper = new DbHelper(this);

//        get user info into text views
    }

    public void ReturnHome(View view){

//        dbHelper.updateUserProfile(1,
//                                    name.getText().toString(),
//                                    address.getText().toString(),
//                                    number.getText().toString());

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
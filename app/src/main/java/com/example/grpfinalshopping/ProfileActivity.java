package com.example.grpfinalshopping;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import entities.User;

public class ProfileActivity  extends AppCompatActivity {

    EditText fullName, address, phone;
    DbHelper dbHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbHelper = new DbHelper(this);

        fullName = (EditText) findViewById(R.id.fullNameET);
        address = (EditText) findViewById(R.id.addressET);
        phone = (EditText) findViewById(R.id.phoneET);

        user = dbHelper.getUserById(1);

        if (user.getUserId() == 0){
            dbHelper.addUser("", "", "");
        }
        fullName.setText(user.getFullName());
        address.setText(user.getAddress());
        phone.setText(user.getPhone());
    }

    public void UpdateUserProfile(View view){

        if(VerifyFieldsAreInformed())
        {
            dbHelper.updateUserProfile(1,
                    fullName.getText().toString(),
                    phone.getText().toString(),
                    address.getText().toString());

            Toast.makeText(this, "Your profile has been updated successfully!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean VerifyFieldsAreInformed(){

        if (fullName.length() == 0){
            fullName.setError("Full name is required");
            return false;
        }
        if (phone.length() == 0){
            phone.setError("Phone number is required");
            return false;
        }
        else if(!Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$").matcher(phone.getText().toString()).matches()){
            phone.setError("Phone number is invalid");
            return false;
        }

        if (address.length() == 0){
            address.setError("Address is required");
            return false;
        }

        return true;
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
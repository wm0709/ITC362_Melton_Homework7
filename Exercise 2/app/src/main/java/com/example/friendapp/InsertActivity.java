package com.example.friendapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view){
        // Retrieve first name, last name, and email
        Log.w("InsertActivity", "Insert Button Pushed");
        EditText firstNameET = findViewById(R.id.input_first_name);
        EditText lastNameET = findViewById(R.id.input_last_name);
        EditText emailET = findViewById(R.id.input_email);
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String email = emailET.getText().toString();

        //insert into database
        Friend friend = new Friend(0, firstName, lastName, email);
        dbManager.insert(friend);
        Toast.makeText(this, "Friend Added", Toast.LENGTH_SHORT).show();

        //clear data
        firstNameET.setText("");
        lastNameET.setText("");
        emailET.setText("");
    }

    public void goBack(View view){this.finish();}
}

package com.example.candystore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view){
        // Retrieve name and price
        Log.w("InsertActivity", "Insert Button Pushed");
        EditText nameET = findViewById(R.id.input_name);
        EditText priceET = findViewById(R.id.input_price);
        String name = nameET.getText().toString();
        String priceString = priceET.getText().toString();

        //insert into database

        //clear data
        nameET.setText("");
        priceET.setText("");
    }

    public void goBack(View view){
        this.finish();
    }

}

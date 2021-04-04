package com.example.candystore;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
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
        // Retrieve name and price
        Log.w("InsertActivity", "Insert Button Pushed");
        EditText nameET = findViewById(R.id.input_name);
        EditText priceET = findViewById(R.id.input_price);
        String name = nameET.getText().toString();
        String priceString = priceET.getText().toString();

        //insert into database

        try {
            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0, name, price);
            dbManager.insert(candy);
            Toast.makeText(this, "Candy Added", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException nfe){
            Toast.makeText(this, "Price Error", Toast.LENGTH_SHORT).show();
        }

        //clear data
        nameET.setText("");
        priceET.setText("");
    }

    public void goBack(View view){
        this.finish();
    }

}

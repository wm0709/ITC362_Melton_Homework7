package com.example.todo_list_1;

import android.os.Bundle;
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
        // Retrieve first name, last name, and email
        Log.w("InsertActivity", "Insert Button Pushed");
        EditText todoET = findViewById(R.id.input_todo_item);
        String todoItem = todoET.getText().toString();

        //insert into database
        Todo todo = new Todo(0, todoItem);
        dbManager.insert(todo);
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();

        //clear data
        todoET.setText("");
    }

    public void goBack(View view){this.finish();}
}

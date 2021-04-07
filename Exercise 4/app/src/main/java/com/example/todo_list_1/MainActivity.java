package com.example.todo_list_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateView();
    }

    private void updateView() {
        ArrayList<Todo> todos = dbManager.selectAll();

        //create a scroll view and gridLayout
        if(todos.size() > 0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(todos.size());
            grid.setColumnCount(3);

            //create arrays of components
            TextView[] ids = new TextView[todos.size()];
            TextView[] todoItems = new TextView[todos.size()];
            TextView[] todoDeadlines = new TextView[todos.size()];

            //retrieve the width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(size);
            int width = size.x;

            //create the textView for the friends's ID
            int i = 0;
            for(Todo todo : todos){
                boolean pastDue = false;
                // ids
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + todo.getId());
                // todos
                todoItems[i] = new TextView(this);
                todoItems[i].setText(todo.getTodoItem());
                // deadlines
                try {
                    Date dateTemp = formatter.parse(todo.getTodoDeadline());
                    if(Calendar.getInstance().getTime().compareTo(dateTemp) > 0){
                        pastDue = true;
                    }
                } catch (ParseException var4) {
                    var4.printStackTrace();
                }

                todoDeadlines[i] = new TextView(this);
                todoDeadlines[i].setText(todo.getTodoDeadline());

                if(pastDue){
                    ids[i].setBackgroundColor(Color.RED);
                    todoItems[i].setBackgroundColor(Color.RED);
                    todoDeadlines[i].setBackgroundColor(Color.RED);
                }

                //add the elements to the grid
                grid.addView(ids[i], (width/10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(todoItems[i], (int) (width * .40),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(todoDeadlines[i], (int) (width * .6),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle action bar items here
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                Log.w("MainActivity", "Add Selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
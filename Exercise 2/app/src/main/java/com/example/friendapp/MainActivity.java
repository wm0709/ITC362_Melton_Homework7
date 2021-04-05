package com.example.friendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateView();
    }

    private void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();

        //create a scroll view and gridLayout
        if(friends.size() > 0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(4);

            //create arrays of components
            TextView[] ids = new TextView[friends.size()];
            TextView[] firstNames = new TextView[friends.size()];
            TextView[] lastNames = new TextView[friends.size()];
            TextView[] emails = new TextView[friends.size()];

            //retrieve the width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(size);
            int width = size.x;

            //create the textView for the friends's ID
            int i = 0;
            for(Friend friend : friends){
                // ids
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());
                // firstNames
                firstNames[i] = new TextView(this);
                firstNames[i].setText(friend.getFirstName());
                // lastNames
                lastNames[i] = new TextView(this);
                lastNames[i].setText(friend.getLastName());
                // emails
                emails[i] = new TextView(this);
                emails[i].setText(friend.getEmail());

                //add the elements to the grid
                grid.addView(ids[i], (width/10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(firstNames[i], (int) (width * .20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(lastNames[i], (int) (width * .20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * .6),
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
            case R.id.action_update:
                Log.w("MainActivity", "Update Selected");
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
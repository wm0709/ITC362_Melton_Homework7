package com.example.friendapp;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    // Build a View Dynamically with all the candies
    private void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();

        //create a scroll view and gridLayout
        if(friends.size() > 0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(5);

            //create arrays of components
            TextView[] ids = new TextView[friends.size()];
            EditText[][] names = new EditText[friends.size()][2];
            EditText[] emails = new EditText[friends.size()];
            Button[] buttons = new Button[friends.size()];
            ButtonHandler bh = new ButtonHandler();

            //retrieve the width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(size);
            int width = size.x;

            //create the textView for the candy's ID
            int i = 0;
            for(Friend friend : friends){
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());

                //create the two EditTexts for the friends name
                names[i][0] = new EditText(this);
                names[i][1] = new EditText(this);
                names[i][0].setText(friend.getFirstName());
                names[i][1].setText("" + friend.getLastName());
                names[i][0].setId(10 * friend.getId());
                names[i][1].setId(10 * friend.getId() + 1);

                //create the EditText for the email
                emails[i] = new EditText(this);
                emails[i].setText(friend.getEmail());
                emails[i].setId(10 * friend.getId() + 2);

                //create the buttons
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(friend.getId());

                //setup the event handling
                buttons[i].setOnClickListener(bh);

                //add the elements to the grid
                grid.addView(ids[i], (width/10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(names[i][0], (int) (width * .2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(names[i][1], (int) (width * .2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * .25),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .3),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //retrieve name and price of the candy
            int friendId = v.getId();
            EditText firstNameET = (EditText) findViewById(10 * friendId);
            EditText lastNameET = (EditText) findViewById(10 * friendId + 1);
            EditText emailET = (EditText) findViewById(10 * friendId + 2);
            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String email = emailET.getText().toString();

            //update candy in the database
                dbManager.updateById(friendId, firstName, lastName, email);
                Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();

                //update screen
                updateView();

        }
    }
}


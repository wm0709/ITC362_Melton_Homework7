package com.example.candystore;

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
        ArrayList<Candy> candies = dbManager.selectAll();

        //create a scroll view and gridLayout
        if(candies.size() > 0){
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(candies.size());
            grid.setColumnCount(4);

            //create arrays of components
            TextView[] ids = new TextView[candies.size()];
            EditText[][] nameAndPrices = new EditText[candies.size()][2];
            Button[] buttons = new Button[candies.size()];
            ButtonHandler bh = new ButtonHandler();

            //retrieve the width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(size);
            int width = size.x;

            //create the textView for the candy's ID
            int i = 0;
            for(Candy candy : candies){
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + candy.getId());

                //create the two EditTexts for the candy's name and price
                nameAndPrices[i][0] = new EditText(this);
                nameAndPrices[i][1] = new EditText(this);
                nameAndPrices[i][0].setText(candy.getName());
                nameAndPrices[i][1].setText("" + candy.getPrice());
                nameAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                nameAndPrices[i][0].setId(10 * candy.getId());
                nameAndPrices[i][1].setId(10 * candy.getId() + 1);

                //create the buttons
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(candy.getId());

                //setup the event handling
                buttons[i].setOnClickListener(bh);

                //add the elements to the grid
                grid.addView(ids[i], (width/10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(nameAndPrices[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(nameAndPrices[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35),
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
            int candyId = v.getId();
            EditText nameET = (EditText) findViewById(10 * candyId);
            EditText priceET = (EditText) findViewById(10 * candyId + 1);
            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            //update candy in the database
            try {
                double price = Double.parseDouble(priceString);
                dbManager.updateById(candyId, name, price);
                Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();
                
                //update screen
                updateView();
            } catch (NumberFormatException nfe){
                Toast.makeText(UpdateActivity.this, "Price Error", Toast.LENGTH_LONG).show();
            }

        }
    }
}

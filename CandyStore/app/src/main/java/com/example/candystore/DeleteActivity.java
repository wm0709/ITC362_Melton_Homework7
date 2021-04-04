package com.example.candystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TooManyListenersException;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    // Build a View Dynamically with all of the candies
    private void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for(Candy candy : candies){
            RadioButton rb = new RadioButton(this);
            rb.setId(candy.getId());
            rb.setText(candy.toString());
            group.addView(rb);
        }

        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        // add a back button at the bottom of the view

        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // delete candy from database
            dbManager.deleteById(checkedId);
            Toast.makeText(DeleteActivity.this, "Candy deleted",
                    Toast.LENGTH_SHORT).show();

            // update screen
            updateView();

        }
    }
}
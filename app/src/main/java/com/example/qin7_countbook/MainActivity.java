package com.example.qin7_countbook;

/*
 * LonleyTwitter Activity
 *
 * Version 1.0
 *
 * September 26, 2017
 *
 * Copyright 2017 hli668, CMPUT301, University of Alberta - All Rights Reserved.  You may use, distribute, or modify this code under terms adn conditions of the Code of Student Behavior at University of Alberta.
 * you may find a copy of the license in this project.  Otherwise please contack qin7@ualberta.ca
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


/**
 * The class for the main activity
 * @author Qin Zhang
 */
public class MainActivity extends Activity {


    private ListView counterListView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton addButton =  findViewById(R.id.add);
        counterListView = findViewById(R.id.counter_listview);

    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        /**
         * create a new counterArrayAdapter
         */
        CounterArrayAdapter adapter = new CounterArrayAdapter(this,Data.getInstance(this).getCounters());
        counterListView.setAdapter(adapter);
        /**
         * update the number of total
         */
        String size = ""+Data.getInstance(this).getCounters().size();
        ((TextView)findViewById(R.id.total_number)).setText(size);
    }

    /**
     * if this activity is onPause then save the current data
     */
    @Override
    protected void onPause() {
        super.onPause();
        Data.getInstance(this).saveInFile(this);
    }

    /**
     * if EditActivity is called then start the intent of EditActivity
     * @param v
     */
    public void editCounter(View v){
        setResult(RESULT_OK);
        Intent intent = new Intent(this,EditActivity.class);
        startActivity(intent);
    }



}

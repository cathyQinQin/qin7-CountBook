package com.example.qin7_countbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


/**
 * Created by qin7 on 2017/9/30.
 */

/**
 * create a singleton class to save data of counters
 */
class Data {
    private static final String FILENAME = "file.sav";
    private static Data instance;

    private ArrayList<Counter> counters;

    public static Data getInstance(Context context) {
        /**
         * if no instance, then try to find a instance in the saved file
         */
        if (instance == null){
            loadFromFile(context);
        }
        return instance;
    }

    private Data() {
        counters = new ArrayList<Counter>();
    }


    public ArrayList<Counter> getCounters() {
        return counters;
    }

    /**
     * Load the file that storing the data
     */
    private static void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<Data>() {}.getType();
            instance = gson.fromJson(in, listType);
            /**
             * use gson to load data
             */
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            instance = new Data();
            /**
             * if no saved data then create a new instance
             */
        }

    }

    /**
     * Save the user input and the time they click on the save button to the file.
     */

    public void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(this, writer);
            writer.flush();
            /**
             * use gson to save data
             */
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}

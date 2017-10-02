package com.example.qin7_countbook;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qin7 on 2017/9/29.
 */

/**
 * Initialize CounterArrayAdapter class and connect counter and view
 */
public class CounterArrayAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private ArrayList<Counter> counters;
    private CounterArrayAdapter that = this;

    /**
     * Initialization of CounterArrayAdapter
     * @param context
     * @param counters
     */
    public CounterArrayAdapter(Context context, ArrayList<Counter> counters) {
        this.context = context;
        this.counters = counters;
    }

    @Override
    public Object getItem(int i) {
        return counters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return counters.size();
    }

    /**
     * edit content in view
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup){
        // get idea from https://stackoverflow.com/questions/17525886/listview-with-add-and-delete-buttons-in-each-row-in-android
        if (view == null){
            /**
             * if there is no view then inflater list_item.xml as a view
             */
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        /**
         * find separate view of button and textview
         */
        TextView name = view.findViewById(R.id.list_counter_name);
        TextView value = view.findViewById(R.id.list_counter_value);
        ImageButton add_btn = view.findViewById(R.id.list_add_btn);
        ImageButton minus_btn = view.findViewById(R.id.list_minus_btn);
        ImageButton reset_btn = view.findViewById(R.id.list_reset_btn);

        final Counter counter = counters.get(i);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * find the ith counter in couters array list
                 */
                Intent intent = new Intent(context,EditActivity.class);
                intent.putExtra("pos",i);
                context.startActivity(intent);

            }
        });
        /**
         * set name and value
         */
        name.setText(counter.getName());
        value.setText(counter.getCurrent_value());

        /**
         * bind button to call accroding function
         */
        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                counter.increment();
                that.notifyDataSetChanged();
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                counter.decrement();
                that.notifyDataSetChanged();
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                counter.reset();
                that.notifyDataSetChanged();
            }
        });
        return view;
    }
}

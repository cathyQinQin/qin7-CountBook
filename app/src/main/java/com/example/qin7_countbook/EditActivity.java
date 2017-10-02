package com.example.qin7_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * edit the specific counter which is chosen
 */
public class EditActivity extends AppCompatActivity {
    private Counter counter;
    private EditText name,value,init_value,comment;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        /**
         * get the name and value and date,init_value and comment of the chosen counter
         */
        name = (EditText) findViewById(R.id.edit_name);
        TextView last_changed = (TextView) findViewById(R.id.last_change);
        value = (EditText) findViewById(R.id.edit_value);
        init_value = (EditText) findViewById(R.id.edit_init_value);
        comment = (EditText) findViewById(R.id.edit_comment);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",-1);
        if (pos < 0){
            /**
             * if counter is being created, hide the view of init_value and date
             */
            init_value.setVisibility(View.GONE);
            last_changed.setVisibility(View.GONE);
            findViewById(R.id.text_date).setVisibility(View.GONE);
            findViewById(R.id.text_initial_value).setVisibility(View.GONE);
        } else {
            /**
             * if counter already exists, then show the details of the counter
             */
            counter = Data.getInstance(this).getCounters().get(pos);
            name.setText(counter.getName());
            last_changed.setText(counter.getDate());
            value.setText(counter.getCurrent_value());
            init_value.setText(counter.getInitial_value());
            comment.setText(counter.getComment());
        }
    }

    /**
     *confirm the change of current counter
     * @param v
     */
    public void confirm(View v){
        if (pos < 0){
            Data.getInstance(this).getCounters().add(new Counter(name.getText().toString(),
                    Integer.parseInt(value.getText().toString()),comment.getText().toString()));
        } else {
            counter.edit(name.getText().toString(),Integer.parseInt(value.getText().toString()),
                    Integer.parseInt(init_value.getText().toString()),comment.getText().toString());
        }

        this.finish();

    }

    /**
     * delete the current counter
     * @param v
     */
    public void delete(View v){
        if (pos >= 0){
            Data.getInstance(this).getCounters().remove(pos);
        }
        this.finish();
    }
}

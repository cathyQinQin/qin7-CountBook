package com.example.qin7_countbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by qin7 on 2017/9/29.
 */

/**
 * Initialize counter and implement the function of increment,decrement,reset and edit.
 */
class Counter{
    private int initial_value, current_value;
    private Date date;
    private String name,comment;
    Counter(String name,int init_value,String comment) {
        this.initial_value = init_value;
        this.current_value = init_value;
        this.date = new Date();
        this.name = name;
        this.comment = comment;


    }

    /**
     * add 1 and update date
     */
    void increment(){
        ++this.current_value;
        this.date = new Date();
    }

    /**
     * minius 1 and update date
     */
    void decrement(){
        --this.current_value;
        if (this.current_value<0){
            this.current_value = 0;
        }
        this.date = new Date();
    }

    /**
     * reset the value to initial value and update date
     */
    void reset(){
        this.current_value = this.initial_value;
        this.date = new Date();
    }

    /**
     * change the details of counter
     * @param name
     * @param current_value
     * @param init_value
     * @param comment
     */
    void edit(String name,int current_value,int init_value,String comment){
        this.name = name;
        this.current_value = current_value;
        this.initial_value = init_value;
        this.comment = comment;
        this.date = new Date();
    }

    /**
     * getter to get initial_value,comment,current_value,date and name
     * @return
     */
    String getInitial_value() {
        return ""+initial_value;
    }

    String getComment() {
        return comment;
    }



    String getCurrent_value() {
        return ""+current_value;
    }



    String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(this.date);

    }


    public String getName() {
        return name;
    }


    /**
     * override toString
     * @return
     */
    @Override
    public String toString() {
        return "Counter{" +
                "current_value=" + current_value +
                ", comment='" + comment + '\'' +
                '}';
    }
}

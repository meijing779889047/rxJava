package com.project.rxjavaproject.observerpattern;

import android.util.Log;

/**
 * Created by meijing on 2017/4/16.
 */

public class ChildObserver   implements   Observer {

    private  String  name;

    public ChildObserver(String name) {
        this.name = name;
    }



    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
         return name;
    }

    @Override
    public void update(String status) {
        Log.i("ChildObserver",this.name+"接收到被观察者的通知信息:"+status);
    }
}

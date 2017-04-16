package com.project.rxjavaproject.observerpattern;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meijing on 2017/4/16.
 */

public  abstract class Subject {

    private String  TAG="Subject";

    private List<Observer>  observers=new ArrayList<>();

    /**
     * 添加观察者
     * @param observer
     */
    public   void attach(Observer  observer){

        observers.add(observer);
        Log.i(TAG,"添加一个观察者"+observer.getName());

    }

    /**
     * 删除观察者
     * @param observer
     */
    public  void dettch(Observer  observer){
        observers.remove(observer);
        Log.i(TAG,"删除一个观察者"+observer.getName());
    }

    public  void  notifyObserver(String status){
        for (Observer observer:observers){
              observer.update(status);

        }
        Log.i(TAG,"通知所有的观察者");
    }

}

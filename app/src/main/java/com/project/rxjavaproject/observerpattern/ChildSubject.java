package com.project.rxjavaproject.observerpattern;

/**
 * Created by meijing on 2017/4/16.
 */

public class ChildSubject  extends  Subject {



    public   void chancge(String state){
           notifyObserver(state);
    }
}

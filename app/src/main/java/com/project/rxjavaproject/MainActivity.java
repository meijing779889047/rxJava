package com.project.rxjavaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.project.rxjavaproject.observerpattern.ChildObserver;
import com.project.rxjavaproject.observerpattern.ChildSubject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void onClick() {
        ChildObserver   childObserver1=new ChildObserver("观察者1");
        ChildObserver   childObserver2=new ChildObserver("观察者2");
        ChildObserver   childObserver3=new ChildObserver("观察者3");

        ChildSubject  subject=new ChildSubject();


        subject.attach(childObserver1);
        subject.attach(childObserver2);
        subject.attach(childObserver3);
        subject.chancge("被观察对象向观察者对象发送一个数据");


    }
}

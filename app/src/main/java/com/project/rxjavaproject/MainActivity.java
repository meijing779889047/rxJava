package com.project.rxjavaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.rxjavaproject.observerpattern.ChildObserver;
import com.project.rxjavaproject.observerpattern.ChildSubject;
import com.project.rxjavaproject.rxSample.SimpleUseActivity;
import com.project.rxjavaproject.schedule.ScheduleActivity;
import com.project.rxjavaproject.verificationcode.VerificationCodeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button1, R.id.button2,R.id.button3,R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1://观察者模式
                ChildObserver childObserver1=new ChildObserver("观察者1");
                ChildObserver   childObserver2=new ChildObserver("观察者2");
                ChildObserver   childObserver3=new ChildObserver("观察者3");
                ChildSubject subject=new ChildSubject();
                subject.attach(childObserver1);
                subject.attach(childObserver2);
                subject.attach(childObserver3);
                subject.chancge("被观察对象向观察者对象发送一个数据");
                break;
            case R.id.button2://rxjava的简单使用
                startActivity(new Intent(this, SimpleUseActivity.class));
                break;
            case R.id.button3://rxjava的线程控制
                startActivity(new Intent(this, ScheduleActivity.class));
                break;
            case R.id.button4://通过rxjava实现验证码发送
                startActivity(new Intent(this, VerificationCodeActivity.class));
                break;
        }
    }
}

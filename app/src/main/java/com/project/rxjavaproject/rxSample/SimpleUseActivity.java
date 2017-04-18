package com.project.rxjavaproject.rxSample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.rxjavaproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * rxJava的简单实用
 */
public class SimpleUseActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.activity_simple_use)
    LinearLayout activitySimpleUse;
    private String TAG="SimpleUseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1://create的使用
                //创建被观察者
                Observable<String> observable = getObservable();
                //创建观察者
                Observer<String> observer = getObserver();
                //订阅
                observable.subscribe(observer);
                break;
            case R.id.button2://just（）的使用
                //创建被观察者
                Observable<String> justObservable = getjustObservable();
                //创建观察者
                Observer<String> justObserver = getObserver();
                //订阅
                justObservable.subscribe(justObserver);
                break;
            case R.id.button3://from（）的使用
                break;
        }
    }


    /**
     * 获取observable   被观察者
     * @return
     */
    public Observable<String>  getObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                       e.onNext("发送数据");//发送数据
                       Log.i(TAG,"被观察发送数据");
                       e.onComplete();//数据发送完成
                        Log.i(TAG,"被观察发送数据完成");
//                       e.onError(new Throwable("发送数据出现异常"));
            }
        });
    }
    /**
     * 获取observable   被观察者
     * @return
     */
    public  Observable<String> getjustObservable(){

//        int[] arrays;
//        arrays = new int[3];
//        arrays[0]=1;
//        arrays[1]=1;
//        arrays[2]=2;
//        Observable<int[]> observable = Observable.just(arrays);
        return  Observable.just("数据1", "数据2", "数据2");
    }

    /**
     * 获取observable   被观察者
     * @return
     */
    public  Observable<List<String>> getFromObservable(){
         List<String> list=new ArrayList<>();
         list.add("数据1");
         list.add("数据1");
         list.add("数据1");
         return Observable.fromArray(list);
    }

    /**
     * 获取observer   观察者
     * @return
     */
    public Observer<String>  getObserver(){
        return   new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"在被观察者发送数前调用 onSubscribe（）");
            }

            @Override
            public void onNext(String value) {
                Log.i(TAG,"当被观察者发送数据后接收数据:"+value+" onNext()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"当被观察者发送数出现异常时发送的一场数据:"+e.getMessage()+"   onError()");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"当被观察者发送数据完成后触发onComplete()");
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

package com.project.rxjavaproject.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.rxjavaproject.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ScheduleActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.activity_schedule)
    LinearLayout activitySchedule;
    private String TAG = "ScheduleActivity";
    private long result = 0;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.button1)
    public void onClick() {
        getObserver();
    }


    public void getObserver() {
         Observable.interval(2, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())//observabler在io线程操作
                .observeOn(AndroidSchedulers.mainThread())//observer在主线程操作
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            mDisposable=d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "隔2秒接收的数据为：" + aLong);
                        tvData.setText("更新界面数据:"+result);
                        result++;

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "获取数据失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "获取数据完成");
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mDisposable!=null){
            mDisposable.dispose();
        }
    }
}

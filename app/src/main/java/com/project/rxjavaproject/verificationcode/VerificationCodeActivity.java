package com.project.rxjavaproject.verificationcode;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.rxjavaproject.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class VerificationCodeActivity extends AppCompatActivity {

    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;

    private int count=10;
    private String TAG="VerificationCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifcation_code_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_code)
    public void onClick() {

        Observable.interval(0,1, TimeUnit.SECONDS)//定时器
                  .take(count)//只发送60次数据
                   .map(new Function<Long, Long>() {
                       @Override
                       public Long apply(@NonNull Long aLong) throws Exception {
                           Log.i(TAG,"along:"+aLong);
                           return count-aLong-1;
                       }
                   })
                   .observeOn(AndroidSchedulers.mainThread())//接受数据后再主线程调用
                   .subscribeOn(Schedulers.io())
                   .doOnSubscribe(new Consumer<Disposable>() {
                       @Override
                       public void accept(@NonNull Disposable disposable) throws Exception {
                           btnCode.setEnabled(false);
                           btnCode.setTextColor(Color.WHITE);

                       }
                   })
                   .subscribe(new Observer<Long>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(Long aLong) {
                          btnCode.setText("剩余"+aLong+"秒");
                       }

                       @Override
                       public void onError(Throwable e) {

                       }

                       @Override
                       public void onComplete() {
                           btnCode.setEnabled(true);
                           btnCode.setTextColor(Color.BLACK);
                           btnCode.setText("发送验证码");
                       }
                   });

    }
}

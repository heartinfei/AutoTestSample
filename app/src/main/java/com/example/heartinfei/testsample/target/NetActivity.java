package com.example.heartinfei.testsample.target;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.heartinfei.testsample.R;
import com.example.heartinfei.testsample.api.BaiduApi;
import com.example.heartinfei.testsample.widget.ProgressLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络请求测试
 *
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
public class NetActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.progressLayout)
    ProgressLayout p;
    static final Handler HANDLER = new Handler();

    private static final Retrofit.Builder RETROFIT_BUILDER = new Retrofit.Builder()
            .baseUrl("https://www.cnblogs.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        p.showProgress(true);
        switch (v.getId()) {
            case R.id.btn:
                request();
                break;
            default:
                HANDLER.postDelayed(() -> {
                    clearText();
                    p.showProgress(false);
                }, TimeUnit.SECONDS.toMillis(2));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HANDLER.removeCallbacksAndMessages(null);
    }

    private void clearText() {
        tv.setText("123");
    }

    private void request() {
        RETROFIT_BUILDER.build()
                .create(BaiduApi.class)
                .request()
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        p.showProgress(false);
                    }
                });
    }
}

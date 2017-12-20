package com.example.heartinfei.testsample;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingmaster.slib.S;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rlv)
    RecyclerView rlv;

    MainAdapter adapter;
    private final Pattern p = Pattern.compile(".*MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        S.init(true, 1, "S_LOG");
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter = new MainAdapter());
        setUpData();
    }

    private void setUpData() {
        Observable.just(getPackageManager())
                .map((s) -> Arrays.asList(s.getPackageInfo(getPackageName(),
                        PackageManager.GET_ACTIVITIES).activities))
                .flatMap((List<ActivityInfo> activityInfos) ->
                        Observable.create((ObservableEmitter<ActivityInfo> e) -> {
                            for (ActivityInfo activityInfo : activityInfos) {
                                e.onNext(activityInfo);
                            }
                            e.onComplete();
                        })
                )
                .filter((ActivityInfo activityInfo) -> {
                    Matcher matcher = p.matcher(activityInfo.name);
                    return !matcher.find();
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((data) -> adapter.setData(data));
    }
}

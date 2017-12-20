package com.example.heartinfei.testsample.target;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.heartinfei.testsample.R;

/**
 * 测试Intent
 * @author 王强 on 2017/12/15 249346528@qq.com
 */
public class IntentTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,HelloWorldActivity.class));
    }
}

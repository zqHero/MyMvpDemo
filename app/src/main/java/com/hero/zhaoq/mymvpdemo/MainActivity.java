package com.hero.zhaoq.mymvpdemo;

import android.os.Bundle;

import com.hero.zhaoq.mymvpdemo.view.activity.BaseActivity;

//mvpDemo
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

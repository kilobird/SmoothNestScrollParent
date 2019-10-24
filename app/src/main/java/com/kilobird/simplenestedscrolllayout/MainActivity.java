package com.kilobird.simplenestedscrolllayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this::toList);
        findViewById(R.id.btn2).setOnClickListener(this::toViewPager);
        findViewById(R.id.btn4).setOnClickListener(this::toTopBottomViewPager);
    }

    private void toTopBottomViewPager(View view) {
        NestScrollViewPagerActionBarActivity.intentTo(this);
    }

    private void toList(View view) {
        NestScrollListActivity.intentTo(this);
    }

    private void toViewPager(View view) {
        NestScrollViewPagerActivity.intentTo(this);
    }

    public void toTopBarRecycler(View view) {
        NestScrollListBarActivity.intentTo(this);
    }
}

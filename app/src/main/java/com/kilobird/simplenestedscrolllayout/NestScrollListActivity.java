package com.kilobird.simplenestedscrolllayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kilobird.simplenestedscrolllayout.view.SmoothNestedScrollLayout;

import java.util.ArrayList;
import java.util.List;

public class NestScrollListActivity extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected SmoothNestedScrollLayout mLayout;

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, NestScrollListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        initView();
    }

    protected int getResId() {
        return R.layout.activity_nest_scroll_list;
    }

    protected void initView() {
        mLayout = findViewById(R.id.recycler_list_container_layout);
        mLayout.setTopViewId(R.id.scroll_top_view);
        mLayout.setInnerOffsetView(findViewById(R.id.middle_title));
        mRecyclerView = findViewById(R.id.recycler_content);
        mLayout.setContentView(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("title -> " + i);
        }
        mRecyclerView.setAdapter(new SimpleStringAdapter(this, list));
    }
}

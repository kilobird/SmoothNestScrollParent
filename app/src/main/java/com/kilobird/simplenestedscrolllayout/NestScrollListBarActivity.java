package com.kilobird.simplenestedscrolllayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

public class NestScrollListBarActivity extends NestScrollListActivity {

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, NestScrollListBarActivity.class));
    }

    @Override
    protected int getResId() {
        return R.layout.activity_nest_scroll_list_bar;
    }

    protected void initView() {
        super.initView();
        View topBar = findViewById(R.id.top_transparent_bar);
        topBar.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                mLayout.setOuterCoverTopMargin(topBar.getHeight()));
    }

}

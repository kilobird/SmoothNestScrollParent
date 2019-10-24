package com.kilobird.simplenestedscrolllayout;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kilobird.simplenestedscrolllayout.view.SmoothNestedScrollLayout;

public class NestScrollViewPagerActivity extends AppCompatActivity {
    private String[] mTitles = new String[]{"简介", "评价", "相关"};
    private TabLayout mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private SmoothNestedScrollLayout mScrollLayout;

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, NestScrollViewPagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scroll_view_pager);
        initView();
        initData();
//        initEvent();
    }

//    private void initEvent() {
//        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                mScrollLayout.setScrollChild(mFragments[position].getRecyclerView());
//            }
//        });
//
//    }

    private void initData() {

    }

    private void initView() {
        mScrollLayout = findViewById(R.id.scroll_parent);
        mIndicator = findViewById(R.id.scroll_page_indicator);
        mViewPager = findViewById(R.id.scroll_list_viewpager);
        mIndicator.setupWithViewPager(mViewPager);
        mScrollLayout.setTopView(findViewById(R.id.scroll_top_view));
        mScrollLayout.setInnerOffsetView(mIndicator);
        mScrollLayout.setContentView(mViewPager);
        mScrollLayout.setControlDelegate(new SmoothNestedScrollLayout.OnScrollControlDelegate() {
            @Override
            public View getScrollChildView() {
                return mFragments[mViewPager.getCurrentItem()].getRecyclerView();
            }
        });

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }
}

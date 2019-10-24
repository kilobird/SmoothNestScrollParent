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
import android.view.ViewTreeObserver;

import com.kilobird.simplenestedscrolllayout.view.SmoothNestedScrollLayout;

public class NestScrollViewPagerActionBarActivity extends AppCompatActivity {
    private String[] mTitles = new String[]{"简介", "评价", "相关"};
    private TabLayout mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private SmoothNestedScrollLayout mScrollLayout;
    private View mTopActionBar;
    private View mBottomBar;

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, NestScrollViewPagerActionBarActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scroll_view_pager_action_bar);
        initView();
        initData();
    }


    private void initData() {

    }

    private void initView() {
        mScrollLayout = findViewById(R.id.scroll_parent);
        mIndicator = findViewById(R.id.scroll_page_indicator);
        mViewPager = findViewById(R.id.scroll_list_viewpager);
        // 注意：这次在layout里对 SmoothNestedScrollLayout 已经设置了nest_scroll_content等属性，
        // 所以这里不用再调API设置对应的view了，只需要把顶部、底部操作栏的高度告诉 SmoothNestedScrollLayout
        mIndicator.setupWithViewPager(mViewPager);
        mTopActionBar = findViewById(R.id.top_transparent_bar);
        mTopActionBar.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                mScrollLayout.setOuterCoverTopMargin(mTopActionBar.getHeight()));
        mBottomBar = findViewById(R.id.bottom_action_bar);
        mBottomBar.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                mScrollLayout.setOuterCoverBottomMargin(mBottomBar.getHeight()));
        // 需要告诉nest scroll parent 当前的scroll child 是谁 以决定 scroll 的传递对象
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

        // 添加 top action bar 渐变 alpha 效果
        mTopActionBar.getBackground().setAlpha(0);
        mScrollLayout.setScrollListener((dy, scrollY) -> {
            int alpha = scrollY * 255 / mScrollLayout.getTopScrollHeight();
            mTopActionBar.getBackground().setAlpha(alpha);
        });
    }

}

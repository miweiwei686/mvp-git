package com.example.miwei.mvptest.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miwei.mvptest.R;
import com.example.miwei.mvptest.common.view.ScrollableHelper;
import com.example.miwei.mvptest.common.view.ScrollableLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrollLayoutActivity extends FragmentActivity {

//    private ListView listView;
    private ScrollableLayout scrollableLayout;
    private ScrollViewPager pagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolllayout);
        initView();
        initData();
    }

    private void initData() {
        String[] strDatas = new String[50];
        for(int i = 0;i<2;i++) {

            strDatas[i] = String.valueOf(i);

        }

        fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());

        pagerAdapter = new ScrollViewPager(getSupportFragmentManager(), fragments,
                Arrays.asList(strDatas));
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        scrollableLayout.getHelper().setCurrentScrollableContainer((ScrollableHelper.ScrollableContainer) fragments.get(0));
        scrollableLayout.isCanScroll(true);

    }


    private void initView() {
//        listView = (ListView)findViewById(R.id.listview);
        scrollableLayout = (ScrollableLayout)findViewById(R.id.scrollView);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
    }

    public class ScrollViewPager extends FragmentPagerAdapter {
        private List<Fragment> list;
        private List<String> strings;

        public ScrollViewPager(FragmentManager fm, List<Fragment> list, List<String> strings) {
            super(fm);
            this.list = list;
            this.strings = strings;
        }

        @Override
        public Fragment getItem(int position) {
            return list != null ? list.get(position) : null;
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strings.get(position);
        }
    }

}

package com.tuandm.codeme.view.home;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tuandm.codeme.base.BaseActivity;
import com.tuandm.codeme.R;
import com.tuandm.codeme.view.home.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        setupTabLayout();
    }

    private void init() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupTabLayout() {
        tabLayout.getTabAt(0).setIcon(R.drawable.news_feeds);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_youtube);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_chat);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_menu);
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.colorGray500), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorGray500), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.colorGray500), PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorGray500), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}

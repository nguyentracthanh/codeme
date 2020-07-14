package com.tuandm.codeme.view.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tuandm.codeme.view.home.tab_chat.ChatFragment;
import com.tuandm.codeme.view.home.tab_home.HomeFragment;
import com.tuandm.codeme.view.home.tab_menu.MenuFragment;
import com.tuandm.codeme.view.home.tab_youtube.YoutubeFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new YoutubeFragment();
            case 2:
                return new ChatFragment();
            default:
                return new MenuFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

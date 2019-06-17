package com.google.dmac;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPageAdapter extends FragmentPagerAdapter {
 private final List<Fragment> fragmentList = new ArrayList<>();
 private final List<String > fragmenttitles= new ArrayList<>();

    public viewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmenttitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitles.get(position);
    }
    public void AddFragment(Fragment fragment,String s){
        fragmentList.add(fragment);
        fragmenttitles.add(s);
    }



}

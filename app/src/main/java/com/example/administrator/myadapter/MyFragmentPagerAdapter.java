package com.example.administrator.myadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.fragment.MapFragment;
import com.example.administrator.fragment.SearchFragment;
import com.example.administrator.fragment.SettingFragment;
import com.example.administrator.gasdetectionapp.MainActivity;

/**
 * Created by Administrator on 2017/7/7.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private Fragment mapFragment = null;
//    private Fragment listFragment = null;
    private Fragment searchFragment=null;
    private Fragment settingFragment = null;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mapFragment = new MapFragment();
//        listFragment = new ListFragment(fm);
        searchFragment=new SearchFragment(fm);
        settingFragment = new SettingFragment();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = mapFragment;
                break;
            case MainActivity.PAGE_TWO:
//                fragment = listFragment;

                fragment=searchFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = settingFragment;
                break;
        }
        return fragment;
    }
}

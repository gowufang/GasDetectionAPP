package com.example.administrator.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.gasdetectionapp.R;

/**
 * Created by Administrator on 2017/7/7.
 */
public class SettingFragment extends Fragment {

    public SettingFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment,container,false);
        return view;
    }
}

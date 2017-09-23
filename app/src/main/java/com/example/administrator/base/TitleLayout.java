package com.example.administrator.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.gasdetectionapp.R;

/**
 * Created by Administrator on 2017/8/22.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener{

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        ImageView backBtn= (ImageView) findViewById(R.id.title_back);

        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back:
                ((Activity)getContext()).finish();
        }
    }
}
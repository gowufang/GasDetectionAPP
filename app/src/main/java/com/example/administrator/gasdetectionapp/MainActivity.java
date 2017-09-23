package com.example.administrator.gasdetectionapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.fragment.MapFragment;
import com.example.administrator.fragment.SearchFragment;
import com.example.administrator.fragment.SettingFragment;
import com.example.administrator.pushservice.IntentService;
import com.example.administrator.pushservice.PushService;
import com.igexin.sdk.PushManager;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static Context mContext;

    //UI Object
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_map;
    private RadioButton rb_list;
    private RadioButton rb_setting;
    private ViewPager viewPager;

//    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private FragmentManager fragmentManager;
    MapFragment mapFragment;
    SearchFragment searchFragment;
    SettingFragment settingFragment;

    //页面常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        mContext = MainActivity.this;

        // com.getui.demo.DemoPushService 为第三⽅方⾃自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), PushService.class);
        // com.getui.demo.DemoIntentService 为第三⽅方⾃自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);



//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        fragmentManager=getSupportFragmentManager();
        //绑定 UI Object
        bindViews();

        //设定初始按钮为选中状态
        rb_map.setChecked(true);
    }

    //绑定函数
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_map = (RadioButton) findViewById(R.id.rb_map);
        rb_list = (RadioButton) findViewById(R.id.rb_list);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

//        viewPager = (ViewPager) findViewById(R.id.viewPage);
//        viewPager.setAdapter(myFragmentPagerAdapter);
//        viewPager.setCurrentItem(0);
//        viewPager.addOnPageChangeListener(this);
    }

    //重写RadioGroup接口方法
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        FragmentTransaction transaction=fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (checkedId) {
            case R.id.rb_map:
//                viewPager.setCurrentItem(PAGE_ONE);
                Log.d("rb_click","mapclickedddd");

                if(mapFragment==null){
                    mapFragment=new MapFragment();
                    transaction.add(R.id.content,mapFragment);

                }
                else{
                    transaction.show(mapFragment);
                }
                transaction.show(mapFragment);
                txt_topbar.setText("设备分布");
                break;
            case R.id.rb_list:
//                viewPager.setCurrentItem(PAGE_TWO);

                if(searchFragment==null) {
                    searchFragment = new SearchFragment(fragmentManager);
                    transaction.add(R.id.content, searchFragment);

                }else{
                    transaction.show(searchFragment);
                }
                transaction.show(searchFragment);
                txt_topbar.setText("查询");
                break;
            case R.id.rb_setting:
//                viewPager.setCurrentItem(PAGE_THREE);

                if(settingFragment==null) {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment);
                }else{
                    transaction.show(settingFragment);
                }
                txt_topbar.setText("设置");
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (mapFragment != null) {
            transaction.hide(mapFragment);
        }
        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }

    }
//    //重写ViewPager页面切换的处理方法
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
//        if (state == 2) {
//            switch (viewPager.getCurrentItem()) {
//                case PAGE_ONE:
//                    rb_map.setChecked(true);
//                    break;
//                case PAGE_TWO:
//                    rb_list.setChecked(true);
//                    break;
//                case PAGE_THREE:
//                    rb_setting.setChecked(true);
//                    break;
//            }
//        }
//    }
}

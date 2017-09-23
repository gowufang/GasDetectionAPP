package com.example.administrator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.gasdetectionapp.MainActivity;
import com.example.administrator.gasdetectionapp.MarkerDetilsActivity;
import com.example.administrator.gasdetectionapp.R;
import com.example.administrator.utils.VolleyUtils;

import java.util.HashMap;

import static com.example.administrator.gasdetectionapp.R.drawable.marker_normal;

/**
 * Created by Administrator on 2017/7/7.
 */
public class MapFragment extends Fragment implements AMap.OnMapClickListener, AMap.OnMarkerClickListener {
    private String DataUrlget = "http://sejila.cn:8080/GasEI2/client?type=device&isfixed=1";
    private RequestQueue requestQueue;
    private MapView mapView;
    private UiSettings uiSettings;
//    private InfoWinAdapter adapter;
private Marker oldMarker;
    private LatLng myLatLng;
    private AMap aMap;
    public MapFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment,container,false);


        mapView = (MapView) view.findViewById(R.id.mapView);
        requestQueue = Volley.newRequestQueue(MainActivity.mContext);
        mapView.onCreate(savedInstanceState);
        initOperation();

        return view;
    }
    private void initOperation() {
        initMap();
//        initLocation();
    }
    /**
     * 初始化地图
     */
    private void initMap() {

        aMap = mapView.getMap();
        LatLng marker1 = new LatLng(30.882424,121.917593);
        //设置中心点和缩放比例
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));

        uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true); //显示缩放控件
        uiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
//        uiSettings.setZoomGesturesEnabled(false);//关闭手势放缩
        VolleyUtils.volleyDeviceUtils(DataUrlget,requestQueue,aMap);


        aMap.setOnMapClickListener(this);


        //自定义InfoWindow
        aMap.setOnMarkerClickListener(this);
//        adapter = new InfoWinAdapter();
//        aMap.setInfoWindowAdapter(adapter);
//test
//        HashMap<String,String> test=new HashMap<String,String>();
//
//        test.put("device_code","dCode233");
//        test.put("device_id", "45456");
//        test.put("device_name","huashuo");
//        test.put("remarks","this is remark");
//        test.put("show_lat","39.906901");
//        test.put("show_lon","116.397972");
//
//
//        addMarkerToMap(new LatLng(30.666482, 104.036407),test);
//test
        //test
       /* LatLng latLng = new LatLng(39.906901,116.397972);
        aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));

        LatLng latLng2 = new LatLng(40.906901,116.397972);
        aMap.addMarker(new MarkerOptions().position(latLng2).title("ggg").snippet("DefaultgggMarker"));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(38.906901,116.397972));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
        aMap.addMarker(markerOptions);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions.position(new LatLng(37.906901,116.397972));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
         aMap.addMarker(markerOptions);*/
    }

    private void addMarkerToMap(LatLng latLng,HashMap<String,String> fixedDeviceInfo){
        aMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(marker_normal))
        ).setObject(fixedDeviceInfo);
    }

    //地图的点击事件
    @Override
    public void onMapClick(LatLng latLng) {
        //点击地图上没marker 的地方，隐藏inforwindow
        if (oldMarker != null) {
            oldMarker.hideInfoWindow();
            oldMarker.setIcon(BitmapDescriptorFactory.fromResource(marker_normal));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); //管理地图的生命周期
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause(); //管理地图的生命周期
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        ActivityCollector.finishAll();
        mapView.onDestroy(); //管理地图的生命周期


    }
    //maker的点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {

        if (!marker.getPosition().equals(myLatLng)){ //点击的marker不是自己位置的那个marker
            oldMarker = marker;
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
            if (oldMarker != null) {

                oldMarker.setIcon(BitmapDescriptorFactory.fromResource(marker_normal));
//                Toast.makeText(MainActivity.mContext,"click ",Toast.LENGTH_SHORT).show();

                Intent showIntent=new Intent(MainActivity.mContext,MarkerDetilsActivity.class);
                Log.d("ShowIntent",showIntent.toString());
                HashMap<String,String> data=new HashMap<String,String>();
                data=(HashMap<String, String>) marker.getObject();
                String decode=data.get("device_code");
                showIntent.putExtra("device_code",decode);

                showIntent.putExtra("device_id", data.get("device_id"));
                showIntent.putExtra("device_name",data.get("device_name"));
                showIntent.putExtra("remarks",data.get("remarks")+"this is a remark");
                showIntent.putExtra("show_lat",data.get("show_lat"));
                showIntent.putExtra("show_lon",data.get("show_lon"));
                startActivity(showIntent);

            }

        }

        return false; //返回 “false”，除定义的操作之外，默认操作也将会被执行
    }
}

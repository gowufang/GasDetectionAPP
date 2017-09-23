package com.example.administrator.gasdetectionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.entity.GasEntity;
import com.example.administrator.myadapter.GasDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MarkerDetilsActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    TextView map_lon_tv;
    TextView map_lat_tv;
    TextView device_name_tv;
    TextView device_code_tv;
    TextView device_id_tv;
    TextView remarks_tv;
    String device_code;
    private SwipeRefreshLayout swipeRefreshLayout;

//    GasEntity gasdata;
//    Bundle bundle=new Bundle();
    GasDataAdapter adapter;

    ListView listView;
    String GasDataUrl;
private static final int REFRESH_COMPLETE = 0X110;
    private RequestQueue requestQueue;
    private List<GasEntity> gasEntities=new ArrayList<GasEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showpage);


        requestQueue = Volley.newRequestQueue(MarkerDetilsActivity.this);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_list);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);

        adapter=new GasDataAdapter(MarkerDetilsActivity.this,R.layout.gas_item,gasEntities);
        listView = (ListView) findViewById(R.id.lv_datashow);
        listView.setAdapter(adapter);





        Intent intent=getIntent();
        device_code=intent.getStringExtra("device_code");
        String device_id=intent.getStringExtra("device_id");
        String device_name=intent.getStringExtra("device_name");
        String remarks=intent.getStringExtra("remarks");
        String show_lat=intent.getStringExtra("show_lat");
        String show_lon=intent.getStringExtra("show_lon");


        GasDataUrl = "http://sejila.cn:8080/GasEI2/client?type=databydevice&devicecode="+device_code+"&count=15";
        Log.d("GasDataUrl",GasDataUrl.toString());
//        VolleyUtils.volleyGasUtils(GasDataUrl,requestQueue,gasdata);






        getGasEntities();




        map_lon_tv= (TextView) findViewById(R.id.show_lon);
        map_lat_tv= (TextView) findViewById(R.id.show_lat);
        device_name_tv= (TextView) findViewById(R.id.device_name);
        device_code_tv= (TextView) findViewById(R.id.device_code);
        device_id_tv= (TextView) findViewById(R.id.device_id);
        remarks_tv= (TextView) findViewById(R.id.remarks);

        map_lon_tv.setText(show_lon);
        map_lat_tv.setText(show_lat);
        device_name_tv.setText(device_name);
        device_id_tv.setText(device_id);
        device_code_tv.setText(device_code);
        remarks_tv.setText(remarks);

        Log.d("secondaty",device_code+device_id+device_name+remarks+show_lat+show_lon);
    }

    private void initGasEntity(GasEntity gasentity) {
//        GasEntity data1=new GasEntity("2017-05-25","22.2","220.3");
//        gasEntities.add(data1);
//        GasEntity data2=new GasEntity("2018-05-25","22.2","220.4");
//        gasEntities.add(data2);
//        GasEntity data3=new GasEntity("2019-05-25","22.2","220.8");
//        gasEntities.add(data3);
//        GasEntity data4=new GasEntity("2018-05-15","22.2","220.5");
//        gasEntities.add(data4);
//        GasEntity data5=new GasEntity("2007-05-25","22.2","220.6");
//        gasEntities.add(data5);

        gasEntities.add(gasentity);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    adapter.clear();//清空旧数据
                    getGasEntities();
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;

            }
        }
    };

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }




    private void getGasEntities(){
        JsonArrayRequest getJsonArray = new JsonArrayRequest(
                GasDataUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("onResp2:", response.toString());
                        try {
//                                String gas []={"","","","",""};
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);



                                JSONObject gasObject=jsonObject.getJSONObject("gas");
                                String insert_time=gasObject.getString("insert_time");
                                String gas1=gasObject.getString("gas1");
                                String gas2=gasObject.getString("gas2");

                                Log.d("GasData",insert_time+" "+ gas1+" "+gas2);

                                GasEntity gasEntity=new GasEntity(insert_time,gas1,gas2);

//                                initGasEntity(gasEntity);
                                gasEntities.add(gasEntity);

                            }

                            PutDataIntoAdapter();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("myMsg.error:", "error");
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("error.errorListener", volleyError.getMessage(), volleyError);
                    }
                }
        );
        requestQueue.add(getJsonArray);
    }





    private void PutDataIntoAdapter() {
//        Log.d("myMsg.data:", datas.toString());
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new GasDataAdapter(this,R.layout.gas_item,gasEntities);
        listView.setAdapter(adapter);
    }
}

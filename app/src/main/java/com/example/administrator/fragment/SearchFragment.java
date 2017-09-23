package com.example.administrator.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.entity.Data;
import com.example.administrator.entity.GasEntity;
import com.example.administrator.gasdetectionapp.MainActivity;
import com.example.administrator.gasdetectionapp.R;
import com.example.administrator.myadapter.GasDataAdapter;
import com.example.administrator.utils.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener,SwipeRefreshLayout.OnRefreshListener{
    private FragmentManager fragmentManager;

    public SearchFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
    private SwipeRefreshLayout swipeRefreshLayout;
    private String DataUrlget = "http://sejila.cn:8080/GasEI2/client?type=device&isfixed=-1";
    private RequestQueue requestQueue;
    private GasDataAdapter adapter;
    ListView listView;
    private List<GasEntity> gasEntities=new ArrayList<GasEntity>();
    Spinner myspinner;
    private ArrayAdapter<String> arr_adapter;
    private List<String> data_list;
    private static final int REFRESH_COMPLETE = 0X110;
    String GasDataUrl2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.search_fragment, container, false);
        requestQueue = Volley.newRequestQueue(MainActivity.mContext);

        myspinner= (Spinner) view.findViewById(R.id.spinner);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.search_refresh);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);


        data_list = new ArrayList<String>();
        data_list.add("点击下拉选项");
        //数据
        VolleyUtils.GetDeviceCodeUtils(DataUrlget,requestQueue,data_list);

        /*
        * 这里可以添加移动点的volleyUtils的getcode方法
        * 然后通过get方法
        * 将device_code添加到data_list中*/















        arr_adapter= new ArrayAdapter<String>(MainActivity.mContext, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //加载适配器
        myspinner.setAdapter(arr_adapter);
        myspinner.setOnItemSelectedListener(this);

        GasEntity data1=new GasEntity("2017-05-25","22.2","220.3");
        gasEntities.add(data1);
        GasEntity data2=new GasEntity("2018-05-25","22.2","220.4");
        gasEntities.add(data2);
        adapter=new GasDataAdapter(MainActivity.mContext,R.layout.gas_item,gasEntities);
        listView  = (ListView) view.findViewById(R.id.datalist);
        listView.setAdapter(adapter);
        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("whichtoselect",arr_adapter.getItem(position));

        GasDataUrl2 = "http://sejila.cn:8080/GasEI2/client?type=databydevice&devicecode="+arr_adapter.getItem(position)+"&count=50";
        adapter.clear();
        getGasEntities( GasDataUrl2);
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    adapter.clear();//清空旧数据
                    getGasEntities( GasDataUrl2);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;

            }
        }
    };


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Log.d("whichtoselect","nothing");
    }





    private void getGasEntities(String url){
        JsonArrayRequest getJsonArray = new JsonArrayRequest(
                url,
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
        adapter = new GasDataAdapter(MainActivity.mContext,R.layout.gas_item,gasEntities);
        listView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
}

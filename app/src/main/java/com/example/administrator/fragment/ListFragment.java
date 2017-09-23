//package com.example.administrator.fragment;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.administrator.entity.Data;
//import com.example.administrator.gasdetectionapp.GasDetailDataActivity;
//import com.example.administrator.gasdetectionapp.MainActivity;
//import com.example.administrator.gasdetectionapp.R;
//import com.example.administrator.myadapter.MyListViewAdapter;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * Created by Administrator on 2017/7/7.
// */
//public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
//
//    private FragmentManager fragmentManager;
//
//    // get data
//    private String DataUrlget = "http://sejila.cn:8080/GasEI2/client?type=top&deviceId=all&topNum=100";
//
//    private RequestQueue requestQueue;
//
//    // UI
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private MyListViewAdapter myListViewAdapter;
//    private ListView gasdataListview;
//    private ArrayList<Data> datas;
//
//
//    //刷新常量
//    private static final int REFRESH_COMPLETE = 0X110;
//
//    public ListFragment(FragmentManager fragmentManager) {
//        this.fragmentManager = fragmentManager;
//    }
////
////    public ListFragment() {
////
////    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_fragment, container, false);
//
//        Log.d("myMsg.start:", "start!");
//        requestQueue = Volley.newRequestQueue(MainActivity.mContext);
//
//        //绑定 UI Object
//        bindViews(view);
//
//        getDatas();
//
//
//        gasdataListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("myMsg.click", "Click");
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data", datas.get(i));
//                Intent intent = new Intent(MainActivity.mContext, GasDetailDataActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                ((Activity) MainActivity.mContext).overridePendingTransition(R.anim.enter_slide_to_left, R.anim.exit_slide_to_left);
//            }
//        });
//        return view;
//    }
//
//
//
//
//
//
//
//
//
//    private void PutDataIntoAdapter() {
//        Log.d("myMsg.data:", datas.toString());
//        swipeRefreshLayout.setOnRefreshListener(this);
//        myListViewAdapter = new MyListViewAdapter(datas, getActivity());
//        gasdataListview.setAdapter(myListViewAdapter);
//    }
//
//    //绑定函数
//    private void bindViews(View view) {
//        gasdataListview = (ListView) view.findViewById(R.id.gas_data_listView);
//
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.List_Fragment_Swipe_Refresh);
//    }
//
//    private Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case REFRESH_COMPLETE:
//                    getDatas();
//                    myListViewAdapter.notifyDataSetChanged();
//                    swipeRefreshLayout.setRefreshing(false);
//                    break;
//
//            }
//        }
//    };
//
//    public void onRefresh() {
//        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
//
//    }
//
//
//
//
//
//
//
//
//    //取数据
//    private void getDatas() {
//        datas = new ArrayList<Data>();
//        /*for (int i = 0; i < 20; i++ ) {
//            Data data = new Data("10000","ABC111","2017-07-07","30.123","29.123");
//            datas.add(data);
//        }*/
//        JsonArrayRequest getJsonArray = new JsonArrayRequest(
//                DataUrlget,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d("myMsg.response:", response.toString());
//                        try {
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String id = jsonObject.getString("id");
//                                String device_id = jsonObject.getString("deviceId");
//                                String time = jsonObject.getString("time");
//                                String lon = jsonObject.getString("lon");
//                                String lat = jsonObject.getString("lat");
//                                String gas1 = jsonObject.getString("gas1");
//                                String gas2 = jsonObject.getString("gas2");
//                                String gas3 = jsonObject.getString("gas3");
//                                String gas4 = jsonObject.getString("gas4");
//                                String gas5 = jsonObject.getString("gas5");
//                                String insert_time = jsonObject.getString("insertTime");
//                                Data data = new Data(id, device_id, time, insert_time, lon, lat, new String[]{gas1, gas2, gas3, gas4, gas5});
//
//
//                                datas.add(data);
//
//                            }
//                            PutDataIntoAdapter();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.d("myMsg.error:", "error");
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        Log.e("error.errorListener", volleyError.getMessage(), volleyError);
//                    }
//                }
//        );
//        requestQueue.add(getJsonArray);
//    }
//}

//package com.example.administrator.gasdetectionapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//import com.example.administrator.entity.Data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class GasDetailDataActivity extends AppCompatActivity {
//
//    private SimpleAdapter simpleAdapter;
//    private ArrayList<HashMap<String,String>> dataArrayList = new ArrayList<HashMap<String, String>>();
//    //UI
//    private ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gas_detail_data);
//
//
//
//        //绑定UI
//        bindViews();
//
//        getData();
//
//        simpleAdapter = new SimpleAdapter(this,dataArrayList,R.layout.gas_data_detail_listview_item,new String[] {"key","value"},new int[] {R.id.detail_item_key,R.id.detail_item_value});
//        listView.setAdapter(simpleAdapter);
//    }
//    private void bindViews() {
//        listView = (ListView) findViewById(R.id.gas_detail_data_listView);
//    }
//
//
//
//
//    private void getData() {
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        final Data data = (Data) bundle.getSerializable("data");
//        Log.d("myMsg.Data",data.toString());
//
//        HashMap<String,String> map = new HashMap<String, String>();
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","ID");put("value",data.getId_value_content());}});
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","设备编号");put("value",data.getDeviceId_value_content());}});
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","采集时间");put("value",data.getAcquisitionTime_value_content());}});
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","保存时间");put("value",data.getInsertTime_value_content());}});
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","经度");put("value",data.getLongitude_value_content());}});
//
//        dataArrayList.add(new HashMap<String, String>(){{put("key","纬度");put("value",data.getLatitude_value_content());}});
//
//        for (int i = 0 ; i < data.getGasDatas().length ; i ++ ){
//            map.put("key","Gas" + (i+1));
//            map.put("value", String.valueOf(data.getGasDatas()[i]));
//            final int finalI = i;
//            dataArrayList.add(new HashMap<String, String>(){{put("key","Gas" + (finalI +1));put("value",String.valueOf(data.getGasDatas()[finalI]));}});
//        }
//        Log.d("myMsg.dataArrayList",dataArrayList.toString());
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.enter_slide_to_right, R.anim.exit_slide_to_right);
//    }
//}

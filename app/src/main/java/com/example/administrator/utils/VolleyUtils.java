package com.example.administrator.utils;

import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.administrator.entity.FixedDevice;
import com.example.administrator.entity.GasEntity;
import com.example.administrator.gasdetectionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import static com.example.administrator.gasdetectionapp.MarkerDetilsActivity.GasDataUrl;


/**
 * Created by Administrator on 2017/8/24.
 */

public class VolleyUtils {

/*
*
* 继续写判断插入时间是否变化  0925
* 如果变化，则闪烁*/
    private void ifDataChanged(){
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

//


                            }



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

    }
    public static void volleyDeviceUtils(String DataUrlget, RequestQueue requestQueue, final AMap aMap){
        JsonArrayRequest getJsonArray = new JsonArrayRequest(
                DataUrlget,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("onResp:", response.toString());
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Double map_lon=jsonObject.getDouble("map_lon");
                                Double map_lat=jsonObject.getDouble("map_lat");
                                String device_name=jsonObject.getString("device_name");

                                String newResponse = new String(device_name.getBytes("ISO-8859-1"),"UTF-8");
                                String device_code=jsonObject.getString("device_code");
                                int device_id=jsonObject.getInt("device_id");
                                String remarks=jsonObject.getString("remarks");
                                String show_lon=jsonObject.getString("show_lon");
                                String show_lat=jsonObject.getString("show_lat");

                                Log.d("fixeddevice",map_lon+ " "+map_lat+" "+device_name+" "+device_code+" "+device_id+" "+remarks+" "+show_lon+" "+show_lat);


                                FixedDevice fixedDeviceData=new FixedDevice(device_code,device_id,  newResponse,  map_lat,  map_lon,  remarks,  show_lat,  show_lon);
                                HashMap<String,String> fixedDeviceInfo=new HashMap<>();
                                fixedDeviceInfo.put("device_code",fixedDeviceData.getDevice_code());
                                fixedDeviceInfo.put("device_id", String.valueOf(fixedDeviceData.getDevice_id()));
                                fixedDeviceInfo.put("device_name",fixedDeviceData.getDevice_name());
                                fixedDeviceInfo.put("remarks",fixedDeviceData.getRemarks());
                                fixedDeviceInfo.put("show_lat",fixedDeviceData.getShow_lat());
                                fixedDeviceInfo.put("show_lon",fixedDeviceData.getShow_lon());
//                                    Log.d("gasData():",data.getGasDatas()[0]+"   "+data.getGasDatas()[1]+"   "+data.getGasDatas()[2]+"   "+data.getGasDatas()[3]+"   "+data.getGasDatas()[4]);

                                Log.d("fixeddevice",fixedDeviceInfo.toString());


//                                    addMarkerToMap(new LatLng
//                                            ((data.getLatitude_value_content()+i), (data.getLongitude_value_content()+i))//1
//                                            ,data.getAcquisitionTime_value_content()//2
//                                            ,data.getInsertTime_value_content()//3
//                                            ,data.getId_value_content()//4
//
//                                            ,gasdata//6
//
//                                    );
                                aMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(fixedDeviceData.getMap_lat(),fixedDeviceData.getMap_lon()))
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal))
                                ).setObject(fixedDeviceInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("myMsg.error:", "error");
                            throw new RuntimeException(e);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
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






    public static void GetDeviceCodeUtils(String DataUrlget,RequestQueue requestQueue,final List<String> deviceCodeList){

        JsonArrayRequest getJsonArray = new JsonArrayRequest(
                DataUrlget,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("onResp:", response.toString());
                        try {
//                                String gas []={"","","","",""};
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
//



                                String device_code=jsonObject.getString("device_code");


                                Log.d("fixeddevicecode","devicecode"+device_code);


//
                                deviceCodeList.add(device_code);

                                Log.d("fixeddevice",deviceCodeList.toString());



                            }


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






















    public static void volleyGasUtils(String url, RequestQueue requestQueue, final GasEntity mygasEntity){
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



//                                    int id = jsonObject.getInt("id");
//                                    String device_id = jsonObject.getString("deviceId");
//                                    String time = jsonObject.getString("time");
//                                    Double lon = jsonObject.getDouble("lon");
//                                    Double lat = jsonObject.getDouble("lat");
//                                    String gas1 = jsonObject.getString("gas1");
//                                    String gas2 = jsonObject.getString("gas2");
//                                    String gas3 = jsonObject.getString("gas3");
//                                    String gas4 = jsonObject.getString("gas4");
//                                    String gas5 = jsonObject.getString("gas5");
//                                    String insert_time = jsonObject.getString("insertTime");

                            }

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




}

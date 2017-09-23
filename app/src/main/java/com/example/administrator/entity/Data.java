package com.example.administrator.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Data implements Serializable {
    private String Id_value_content;
    private String DeviceId_value_content;
    private String AcquisitionTime_value_content;
    private String InsertTime_value_content;
    private String Longitude_value_content;
    private String Latitude_value_content;
    private String[] GasDatas = new String[5];

    public Data() {
    }

    public Data(String id_value_content, String equipmentNumber_value_content, String acquisitionTime_value_content, String longitude_value_content, String latitude_value_content) {
        Id_value_content = id_value_content;
        DeviceId_value_content = equipmentNumber_value_content;
        AcquisitionTime_value_content = acquisitionTime_value_content;
        Longitude_value_content = longitude_value_content;
        Latitude_value_content = latitude_value_content;
    }

    public Data(String id_value_content, String equipmentNumber_value_content, String acquisitionTime_value_content, String insertTime_value_content, String longitude_value_content, String latitude_value_content, String[] gasDatas) {
        Id_value_content = id_value_content;
        DeviceId_value_content = equipmentNumber_value_content;
        AcquisitionTime_value_content = acquisitionTime_value_content;
        InsertTime_value_content = insertTime_value_content;
        Longitude_value_content = longitude_value_content;
        Latitude_value_content = latitude_value_content;
        GasDatas = gasDatas;
    }

    public String getId_value_content() {
        return Id_value_content;
    }

    public String getDeviceId_value_content() {
        return DeviceId_value_content;
    }

    public String getAcquisitionTime_value_content() {
        return AcquisitionTime_value_content;
    }

    public String getInsertTime_value_content() {
        return InsertTime_value_content;
    }

    public String getLongitude_value_content() {
        return Longitude_value_content;
    }

    public String getLatitude_value_content() {
        return Latitude_value_content;
    }

    public String[] getGasDatas() {
        return GasDatas;
    }

    public void setId_value_content(String id_value_content) {
        Id_value_content = id_value_content;
    }

    public void setDeviceId_value_content(String deviceId_value_content) {
        DeviceId_value_content = deviceId_value_content;
    }

    public void setAcquisitionTime_value_content(String acquisitionTime_value_content) {
        AcquisitionTime_value_content = acquisitionTime_value_content;
    }

    public void setInsertTime_value_content(String insertTime_value_content) {
        InsertTime_value_content = insertTime_value_content;
    }

    public void setLongitude_value_content(String longitude_value_content) {
        Longitude_value_content = longitude_value_content;
    }

    public void setLatitude_value_content(String latitude_value_content) {
        Latitude_value_content = latitude_value_content;
    }

    public void setGasDatas(String[] gasDatas) {
        GasDatas = gasDatas;
    }

    @Override
    public String toString() {
        String str = "{" +
                "id:" + Id_value_content +
                ",device_id:" + DeviceId_value_content +
                ",acquistionTime:" + AcquisitionTime_value_content +
                ",insertTime:" + InsertTime_value_content +
                ",longitude:" + Longitude_value_content +
                ",latitude:" + Latitude_value_content +
                ",gas1:" + GasDatas[0] +
                ",gas2:" + GasDatas[1] +
                ",gas3:" + GasDatas[2] +
                ",gas4:" + GasDatas[3] +
                ",gas5:" + GasDatas[4] +
                "}";
        return str;
    }
}

package com.example.administrator.entity;

/**
 * Created by Administrator on 2017/8/22.
 */

public class FixedDevice {
    private Double map_lon;
    private Double map_lat;
    private String device_name;
    private String device_code;
    private int device_id;
    private String remarks;
    private String show_lon;
    private String show_lat;

    public FixedDevice() {
    }

    public FixedDevice(String device_code, int device_id, String device_name, Double map_lat, Double map_lon, String remarks, String show_lat, String show_lon) {
        this.device_code = device_code;
        this.device_id = device_id;
        this.device_name = device_name;
        this.map_lat = map_lat;
        this.map_lon = map_lon;
        this.remarks = remarks;
        this.show_lat = show_lat;
        this.show_lon = show_lon;
    }

    public int getDevice_id() {
        return device_id;
    }

    public String getDevice_code() {
        return device_code;
    }

    public String getDevice_name() {
        return device_name;
    }

    public Double getMap_lat() {
        return map_lat;
    }

    public Double getMap_lon() {
        return map_lon;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getShow_lat() {
        return show_lat;
    }

    public String getShow_lon() {
        return show_lon;
    }

    @Override
    public String toString() {
        return "FixedDevice{" +
                "device_code='" + device_code + '\'' +
                ", map_lon='" + map_lon + '\'' +
                ", map_lat='" + map_lat + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_id=" + device_id +
                ", remarks='" + remarks + '\'' +
                ", show_lon='" + show_lon + '\'' +
                ", show_lat='" + show_lat + '\'' +
                '}';
    }
}

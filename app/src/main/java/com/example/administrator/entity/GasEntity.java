package com.example.administrator.entity;

/**
 * Created by Administrator on 2017/8/23.
 */

public class GasEntity {
    String insertTime;
    String gas1;
    String gas2;

    public GasEntity(String insertTime, String gas1, String gas2 ) {
        this.gas1 = gas1;
        this.gas2 = gas2;
        this.insertTime = insertTime;
    }

    public String getGas1() {
        return gas1;
    }

    public void setGas1(String gas1) {
        this.gas1 = gas1;
    }

    public String getGas2() {
        return gas2;
    }

    public void setGas2(String gas2) {
        this.gas2 = gas2;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "GasEntity{" +
                "gas1='" + gas1 + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", gas2='" + gas2 + '\'' +
                '}';
    }
}

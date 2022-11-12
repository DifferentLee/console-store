package com.store;

public class CarItem {
    private int carId;
    private int gid;
    private int num;
    public CarItem() {

    }

    public CarItem(int carId, int gid, int num) {
        this.carId = carId;
        this.gid = gid;
        this.num = num;
    }

    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }
}


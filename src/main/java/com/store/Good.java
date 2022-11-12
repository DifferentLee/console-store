package com.store;

public class Good {
    private int gid;
    private String name;
    private float price;
    private int nums;
    private String desc;

    public Good(int gid, String name, float price, int nums, String desc) {
        this.gid = gid;
        this.name = name;
        this.price = price;
        this.nums = nums;
        this.desc = desc;
    }

    public int getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getNums() {
        return nums;
    }

    public String getDesc() {
        return desc;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return  "商品编号:" + gid + ", 商品名字:" + name+ ", 商品价格:" + price + ", 库存:" + nums + ", 商品描述:" + desc;
    }
}

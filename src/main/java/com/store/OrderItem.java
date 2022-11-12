package com.store;


public class OrderItem {
    private int itemId;
    private int count;
    private int gid;
    private int oid;

    public OrderItem(int itemId, int count, int gid, int oid) {
        this.itemId = itemId;
        this.count = count;
        this.gid = gid;
        this.oid = oid;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }
}


package com.store;

public class Order {
    private int oid;
    private double total;


    public Order(int oid, double total) {
        super();
        this.oid = oid;
        this.total = total;
    }

    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }



}


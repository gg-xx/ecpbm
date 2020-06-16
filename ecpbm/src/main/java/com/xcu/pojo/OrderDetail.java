package com.xcu.pojo;

import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private int oid;
    private OrderInfo oi;
    private int pid;
    private ProductInfo pi;
    private int num;
    private double price;
    private double totalprice;
}


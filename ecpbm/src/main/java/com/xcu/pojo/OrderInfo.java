package com.xcu.pojo;

import lombok.Data;

@Data
public class OrderInfo {
    private int id;
    private int uid;
    private UserInfo ui;
    private String status;
    private String ordertime;
    private double orderprice;
    private String orderTimeFrom;
    private String orderTimeTo;
}

package com.xcu.pojo;

import lombok.Data;

@Data
public class SearchProductInfo {
    private int id;
    private String code;
    private String name;
    private String brand;
    private double priceForm;
    private double priceTo;
    private int tid;
}

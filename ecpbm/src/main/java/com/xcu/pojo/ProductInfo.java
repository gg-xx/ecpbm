package com.xcu.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductInfo implements Serializable {
    private int id;
    private String code;
    private String name;
    private Type type;
    private String brand;
    private String pic;
    private int num;
    private double price;
    private String intro;
    private int status;
    private double priceFrom;
    private double priceTo;
}

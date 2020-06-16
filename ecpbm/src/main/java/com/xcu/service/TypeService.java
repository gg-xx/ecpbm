package com.xcu.service;

import com.xcu.pojo.Type;

import java.util.List;

public interface TypeService {
    //获取所有商品类型
    List<Type> getAll();

    //添加商品类型
    int addType(Type type);

    //更新商品类型
    void updateType(Type type);
}

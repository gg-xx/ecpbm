package com.xcu.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfo implements Serializable {
    private int id;
    private String userName;
    private String password;
    private String realName;
    private String sex;
    private String address;
    private String email;
    private String regDate;
    private int status;
}

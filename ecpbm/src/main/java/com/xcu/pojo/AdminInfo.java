package com.xcu.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class AdminInfo implements Serializable {
    private int id;
    private String name;
    private String pwd;
    private List<Functions> fs;
}

package com.xcu.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class Functions implements Comparable<Functions> {
    private int id;
    private String name;
    private int parentid;
    private boolean isleaf;
    private Set ais = new HashSet();
    public int compareTo(Functions o) {
        return ((Integer)this.getId()).compareTo((Integer)o.getId());
    }
}

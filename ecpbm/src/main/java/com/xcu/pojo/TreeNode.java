package com.xcu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {
    private int id;
    private String text;
    private int fid;
    private List<TreeNode> children;
}

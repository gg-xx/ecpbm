package com.xcu.util;

import com.xcu.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class JsonFactory {
    public static List<TreeNode> buildtree(List<TreeNode> nodes,int id){
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeNode : nodes) {
            TreeNode node = new TreeNode();
            node.setId(treeNode.getId());
            node.setText(treeNode.getText());
            if(id == treeNode.getFid()){
                node.setChildren(buildtree(nodes,node.getId()));
                treeNodes.add(node);
            }
        }
        return treeNodes;
    }
}

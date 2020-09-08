package com.bigdata.sys.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    public static List<TreeNode> build(List<TreeNode> treeNodes,int topId){

        List<TreeNode> nodes=new ArrayList<>();
        //循环
        for(TreeNode n1:treeNodes){
            if(n1.getPid()<topId){//1
                continue;//跳过根节点
            }
            if(n1.getPid()==topId){
                nodes.add(n1);//把根节点孩子放入list


            }
            for(TreeNode n2:treeNodes){
                if(n2.getPid()==n1.getId()){

                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}

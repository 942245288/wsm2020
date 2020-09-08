package com.bigdata.sys.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private  Integer id;

    @JsonProperty(value = "parentId")
    private Integer pid;//pid是数据库的，但是页面需要parentId

    private String title;

    private String href;
    private String icon;
    private Boolean spread;//节点是否展开

    //children 孩子
    private List<TreeNode> children=new ArrayList<>();

    public TreeNode(Integer id, Integer pid, String title, String href, String icon, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.spread = spread;
    }

    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;

    }
}

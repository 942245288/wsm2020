package com.bigdata.sys.controller;


import com.bigdata.sys.constant.Constant;
import com.bigdata.sys.domain.Dept;
import com.bigdata.sys.service.DeptService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.utils.ResultObj;
import com.bigdata.sys.utils.TreeNode;
import com.bigdata.sys.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2020-04-08
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    //loadAllDeptTreeJson
    @RequestMapping("loadAllDeptTreeJson")
    public DataGridView loadAllDeptTreeJson(DeptVo deptVo){
        deptVo.setAvailable(Constant.AVAILABLE_TRUE);
        //查询所有可用的部门
        List<Dept> allDept=deptService.queryAllDeptForList(deptVo);

        List<TreeNode> treeNodes=new ArrayList<>();
        for(Dept d:allDept){
            Integer id = d.getId();
            Integer pid = d.getPid();
            String title = d.getTitle();
            Boolean spread=d.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(id,pid,title,spread));
        }
        return new DataGridView(treeNodes);

    }


    @RequestMapping("loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo){
        //deptVo接受前台的数据
        return deptService.loadAllDept(deptVo);

    }

    //deleteDept
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(Integer id){
        try {
            deptService.deleteDept(id);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }

    //addDept
    @RequestMapping("addDept")
    public ResultObj addDept(Dept dept){
        try {

            deptService.addDept(dept);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }
    //updateDept

    @RequestMapping("updateDept")
    public ResultObj updateDept(Dept dept){
        try {

            deptService.updateDept(dept);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }

    //batchDeleteDept

    @RequestMapping("batchDeleteDept")
    public ResultObj batchDeleteDept(Integer[] ids){
        try {
            deptService.batchDeleteDept(ids);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }


    //loadDeptById
    @RequestMapping("loadDeptById")
    public DataGridView loadDeptById(Integer id){
        return  new DataGridView(deptService.getById(id));

    }

    //checkCurrentDeptHasChild
    @RequestMapping("checkCurrentDeptHasChild")
    public DataGridView checkCurrentDeptHasChild(Integer id){
        Integer count= deptService.queryDeptCountByPid(id);
        return new DataGridView(count);
    }

    //loadDeptMaxOrderNum
    @RequestMapping("loadDeptMaxOrderNum")
    public DataGridView loadDeptMaxOrderNum(){
        Integer max=deptService.queryDeptMaxOrderNum();
        return new DataGridView(max+1);

    }

}


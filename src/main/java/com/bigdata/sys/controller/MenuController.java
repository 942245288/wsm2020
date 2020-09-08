package com.bigdata.sys.controller;


import com.bigdata.sys.constant.Constant;
import com.bigdata.sys.domain.Permission;
import com.bigdata.sys.service.PermissionService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.utils.ResultObj;
import com.bigdata.sys.utils.TreeNode;
import com.bigdata.sys.vo.PermissionVo;
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
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;


    //loadAllPermissionTreeJson
    @RequestMapping("loadAllMenuTreeJson")
    public DataGridView loadAllMenuTreeJson(PermissionVo permissionVo){
        permissionVo.setAvailable(Constant.AVAILABLE_TRUE);
        permissionVo.setType(Constant.TYPE_MENU);
        //查询所有可用的部门
        List<Permission> allPermission=permissionService.queryAllPermissionForList(permissionVo);

        List<TreeNode> treeNodes=new ArrayList<>();
        for(Permission d:allPermission){
            Integer id = d.getId();
            Integer pid = d.getPid();
            String title = d.getTitle();
            Boolean spread=d.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(id,pid,title,spread));
        }
        return new DataGridView(treeNodes);

    }


    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo){
        permissionVo.setType(Constant.TYPE_MENU);
        //permissionVo接受前台的数据
        return permissionService.loadAllPermission(permissionVo);

    }

    //deletePermission
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id){
        try {
            permissionService.deletePermission(id);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }

    //addPermission
    @RequestMapping("addPermission")
    public ResultObj addPermission(Permission permission){
        try {
            //permission.setCreatetime(new Date());
            permissionService.addPermission(permission);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }
    //updatePermission

    @RequestMapping("updatePermission")
    public ResultObj updatePermission(Permission permission){
        try {

            permissionService.updatePermission(permission);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }

    //batchDeletePermission

    @RequestMapping("batchDeletePermission")
    public ResultObj batchDeletePermission(Integer[] ids){
        try {
            permissionService.batchDeletePermission(ids);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }


    //loadPermissionById
    @RequestMapping("loadPermissionById")
    public DataGridView loadPermissionById(Integer id){
        return  new DataGridView(permissionService.getById(id));

    }
    //checkCurrentPermissionHasChild
    @RequestMapping("checkCurrentMenuHasChild")
    public DataGridView checkCurrentMenuHasChild(Integer id){
        Integer count= permissionService.queryPermissionCountByPid(id);
        return new DataGridView(count);
    }

    //loadPermissionMaxOrderNum
    @RequestMapping("loadPermissionMaxOrderNum")
    public DataGridView loadPermissionMaxOrderNum(){
        Integer max=permissionService.queryPermissionMaxOrderNum();
        return new DataGridView(max+1);

    }
}


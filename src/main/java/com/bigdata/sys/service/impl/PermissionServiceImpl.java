package com.bigdata.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.sys.constant.Constant;
import com.bigdata.sys.domain.Permission;
import com.bigdata.sys.mapper.PermissionMapper;
import com.bigdata.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private  PermissionMapper permissionMapper;
    @Override
    public List<Permission> queryAllPermissionForList(PermissionVo permissionVo) {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        if(null!=permissionVo){
            permissionQueryWrapper.eq(StringUtils.isNotBlank(permissionVo.getType()),"type",permissionVo.getType());
            //where type="menu";
            permissionQueryWrapper.eq(permissionVo.getAvailable()!=null,"available",permissionVo.getAvailable());
            //available=1

        }
        permissionQueryWrapper.orderByAsc("ordernum");
        List<Permission> permissionList = permissionMapper.selectList(permissionQueryWrapper);
        //select * from sys_permission where type="menu" and available=1

        return permissionList;
    }


    @Override
    public DataGridView loadAllPermission(PermissionVo permissionVo) {
        QueryWrapper<Permission> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        qw.eq(StringUtils.isNotBlank(permissionVo.getType()),"type",permissionVo.getType());
        qw.eq("available", Constant.AVAILABLE_TRUE);
        //qw.like(StringUtils.isNotBlank(permissionVo.getAddress()),"address",permissionVo.getAddress());
        //qw.like(StringUtils.isNotBlank(permissionVo.getRemark()),"remark",permissionVo.getRemark());
        qw.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        //id=2 or pid =2


        qw.orderByDesc("ordernum");

        //分页
        Page page=new Page<>(permissionVo.getPage(),permissionVo.getLimit());

        //查询数据库
        permissionMapper.selectPage(page,qw);

        return new DataGridView(page.getTotal(),page.getRecords());
    }


    @Transactional
    @Override
    public void deletePermission(Integer id) {

        permissionMapper.deleteById(id);

    }

    @Transactional
    @Override
    public void batchDeletePermission(Integer[] ids) {
        Collection<Serializable> idList=new ArrayList<>();
        for(Integer i:ids){
            idList.add(i);
        }
        permissionMapper.deleteBatchIds(idList);



    }

    @Transactional
    @Override
    public void addPermission(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Transactional
    @Override
    public void updatePermission(Permission permission) {
        permissionMapper.updateById(permission);
    }



    @Override
    public Integer queryPermissionCountByPid(Integer id) {
        return permissionMapper.queryPermissionCountByPid(id);
    }

    @Override
    public Integer queryPermissionMaxOrderNum() {
        return permissionMapper.queryPermissionMaxOrderNum();
    }

}

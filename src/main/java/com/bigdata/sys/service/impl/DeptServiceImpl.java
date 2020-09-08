package com.bigdata.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.sys.domain.Dept;
import com.bigdata.sys.mapper.DeptMapper;
import com.bigdata.sys.service.DeptService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.DeptVo;
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
 * @since 2020-04-08
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> queryAllDeptForList(DeptVo deptVo) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptVo.getAvailable()!=null,"available",deptVo.getAvailable());

        return deptMapper.selectList(queryWrapper);
        //select * from sys_dept where available='1'
    }


    @Override
    public DataGridView loadAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        qw.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        qw.like(StringUtils.isNotBlank(deptVo.getRemark()),"remark",deptVo.getRemark());
        qw.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());
        //id=2 or pid =2


        qw.orderByDesc("createtime");

        //分页
        Page page=new Page<>(deptVo.getPage(),deptVo.getLimit());

        //查询数据库
        deptMapper.selectPage(page,qw);

        return new DataGridView(page.getTotal(),page.getRecords());
    }


    @Transactional
    @Override
    public void deleteDept(Integer id) {

        deptMapper.deleteById(id);

    }

    @Transactional
    @Override
    public void batchDeleteDept(Integer[] ids) {
        Collection<Serializable> idList=new ArrayList<>();
        for(Integer i:ids){
            idList.add(i);
        }
        deptMapper.deleteBatchIds(idList);



    }

    @Transactional
    @Override
    public void addDept(Dept dept) {
        deptMapper.insert(dept);
    }

    @Transactional
    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateById(dept);
    }



    @Override
    public Integer queryDeptCountByPid(Integer id) {
        return deptMapper.queryDeptCountByPid(id);
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return deptMapper.queryDeptMaxOrderNum();
    }

}

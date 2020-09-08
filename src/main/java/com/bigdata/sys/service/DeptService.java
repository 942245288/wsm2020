package com.bigdata.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.sys.domain.Dept;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.DeptVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-08
 */
public interface DeptService extends IService<Dept> {

    List<Dept> queryAllDeptForList(DeptVo deptVo);


    DataGridView loadAllDept(DeptVo deptVo);

    void deleteDept(Integer id);

    void batchDeleteDept(Integer[] ids);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    Integer queryDeptCountByPid(Integer id);

    Integer queryDeptMaxOrderNum();

}

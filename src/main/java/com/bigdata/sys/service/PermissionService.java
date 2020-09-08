package com.bigdata.sys.service;

import com.bigdata.sys.domain.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-05
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> queryAllPermissionForList(PermissionVo permissionVo);
    //List<Permission> queryAllPermissionForList(PermissionVo permissionVo);

    DataGridView loadAllPermission(PermissionVo permissionVo);

    void deletePermission(Integer id);

    void batchDeletePermission(Integer[] ids);

    void addPermission(Permission permission);

    void updatePermission(Permission permission);

    Integer queryPermissionCountByPid(Integer id);

    Integer queryPermissionMaxOrderNum();
}

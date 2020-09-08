package com.bigdata.sys.mapper;

import com.bigdata.sys.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2020-04-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    Integer queryPermissionCountByPid(@Param("id") Integer id);

    Integer queryPermissionMaxOrderNum();

}

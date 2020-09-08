package com.bigdata.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigdata.sys.domain.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bigdata
 * @since 2020-04-08
 */
public interface DeptMapper extends BaseMapper<Dept> {

    Integer queryDeptCountByPid(@Param("id") Integer id);

    Integer queryDeptMaxOrderNum();
}

package com.bigdata.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.sys.domain.Loginfo;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.LoginfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-06
 */
public interface LoginfoService extends IService<Loginfo> {

    DataGridView loadAllLoginfo(LoginfoVo loginfoVo);

    void deleteLoginfo(Integer id);

    void batchDeleteLoginfo(Integer[] ids);
}


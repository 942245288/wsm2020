package com.bigdata.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.sys.domain.Loginfo;
import com.bigdata.sys.mapper.LoginfoMapper;
import com.bigdata.sys.service.LoginfoService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-06
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {


    @Autowired
    private LoginfoMapper loginfoMapper;
    @Override
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
        QueryWrapper<Loginfo> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        qw.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        qw.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        qw.orderByDesc("logintime");
        //select * from sys_login where loginname like '' and logintime>loginfoVo.getStartTime() and logtime<loginfoVo.getEndTime()
        //分页
        Page page=new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());

        //查询数据库
        loginfoMapper.selectPage(page,qw);

        return new DataGridView(page.getTotal(),page.getRecords());
    }


    @Transactional
    @Override
    public void deleteLoginfo(Integer id) {

        loginfoMapper.deleteById(id);

    }

    @Transactional
    @Override
    public void batchDeleteLoginfo(Integer[] ids) {
        Collection<Serializable>  idList=new ArrayList<>();
        for(Integer i:ids){
            idList.add(i);
        }
        loginfoMapper.deleteBatchIds(idList);



    }
}

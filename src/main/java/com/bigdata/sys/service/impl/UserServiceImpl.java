package com.bigdata.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.sys.domain.User;
import com.bigdata.sys.mapper.UserMapper;
import com.bigdata.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.sys.utils.ActiverUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByUserName(String username) {

        //查询数据库
        //使用mybatis-plus
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("loginname",username);
        User user = userMapper.selectOne(userQueryWrapper);


        return user;
    }
}

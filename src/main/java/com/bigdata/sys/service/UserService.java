package com.bigdata.sys.service;

import com.bigdata.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.sys.utils.ActiverUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-03
 */
public interface UserService extends IService<User> {


    User queryUserByUserName(String username);
}

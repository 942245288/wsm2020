package com.bigdata.sys.realm;

import com.bigdata.sys.domain.User;
import com.bigdata.sys.service.UserService;
import com.bigdata.sys.utils.ActiverUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户填写的用户名
        String username = token.getPrincipal().toString();
        //根据用户名去查询数据库
        User user = userService.queryUserByUserName(username);
        if (null!=user){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            //密码比对
            //获取盐
            String salt = user.getSalt();
            //转化
            ByteSource byteSource = ByteSource.Util.bytes(salt);
            //密码比对
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activerUser, user.getPwd(), byteSource, getName());
            return simpleAuthenticationInfo;
        }

        return null;
    }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}

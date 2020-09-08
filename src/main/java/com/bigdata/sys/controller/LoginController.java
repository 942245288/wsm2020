package com.bigdata.sys.controller;

import com.bigdata.sys.constant.Constant;
import com.bigdata.sys.domain.Loginfo;
import com.bigdata.sys.domain.Permission;
import com.bigdata.sys.domain.User;
import com.bigdata.sys.service.LoginfoService;
import com.bigdata.sys.service.PermissionService;
import com.bigdata.sys.utils.ActiverUser;
import com.bigdata.sys.utils.ResultObj;
import com.bigdata.sys.utils.TreeNode;
import com.bigdata.sys.utils.TreeNodeBuilder;
import com.bigdata.sys.vo.PermissionVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController//controller+responsebody//json
@RequestMapping("login")
public class LoginController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping("login")
    public ResultObj login(String username, String password, HttpSession session, HttpServletRequest request){

        try {
            //登录，shiro
            //获取subject对象
            Subject subject = SecurityUtils.getSubject();
            //获取UsernamePasswordToken对象
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);

            //获取用户
            ActiverUser activerUser = (ActiverUser)subject.getPrincipal();
            //把用户放入session
            User user = activerUser.getUser();
            session.setAttribute("user",user);

            //写入登录日志
            Loginfo loginfo = new Loginfo();
            loginfo.setLoginname(user.getName());//获取名字
            loginfo.setLoginip(request.getRemoteAddr());//获取ip
            loginfo.setLogintime(new Date());
            loginfoService.save(loginfo);

            return ResultObj.LOGIN_SUCESS;
        }catch (Exception e){
            //登录失败
            return ResultObj.LOGIN_ERROR;
        }

    }



    //menu_json 拼json格式
    @RequestMapping("loadIndexLeftMenuTreeJson")
    public List<TreeNode> loadIndexLeftMenuTreeJson(PermissionVo permissionVo){
        permissionVo.setType(Constant.TYPE_MENU);//menu
        permissionVo.setAvailable(Constant.AVAILABLE_TRUE);//1
        //查询所有的可用的菜单
        List<Permission> permissionList=permissionService.queryAllPermissionForList(permissionVo);

        //创建list对象
        List<TreeNode> treeNodes=new ArrayList<>();

        for(Permission permission:permissionList){
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String href = permission.getHref();
            String icon = permission.getIcon();
            Boolean spread=permission.getOpen()==1?true:false;
            //通过构造方法创建treenode对象
            TreeNode treeNode = new TreeNode(id, pid, title, href, icon, spread);
            //放入list
            treeNodes.add(treeNode);
        }

        //treeNodes变成navs2.json格式
        List<TreeNode>  treeNodeList= TreeNodeBuilder.build(treeNodes,1);
        return treeNodeList;

    }
}

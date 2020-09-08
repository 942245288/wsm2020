package com.bigdata.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class SystemController {

    @RequestMapping("index")
    public String index(){
        return "system/index";

    }

    @RequestMapping("toDeskManager")
    public String toDeskManager(){

        return "system/deskManager";
    }

    //
    @RequestMapping("toLoginfoManager")
    public String toLoginfoManager(){

        return "system/loginfo/loginfoManager";
    }

    //toNoticeManager

    @RequestMapping("toNoticeManager")
    public String toNoticeManager(){

        return "system/notice/noticeManager";
    }

    //toDeptManager
    @RequestMapping("toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";

    }

    //toDeptLeftManager

    @RequestMapping("toDeptLeftManager")
    public String toDeptLeftManager(){
        return "system/dept/deptLeftManager";

    }
    //toDeptRightManager

    @RequestMapping("toDeptRightManager")
    public String toDeptRightManager(){
        return "system/dept/deptRightManager";

    }

    //toMenuManager
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";

    }

    //toMenuLeftManager

    @RequestMapping("toMenuLeftManager")
    public String toMenuLeftManager(){
        return "system/menu/menuLeftManager";

    }
    //toMenuRightManager

    @RequestMapping("toMenuRightManager")
    public String toMenuRightManager(){
        return "system/menu/menuRightManager";

    }

}

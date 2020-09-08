package com.bigdata.sys.controller;

import com.bigdata.sys.service.LoginfoService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.utils.ResultObj;
import com.bigdata.sys.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2020-04-06
 */
@RestController
@RequestMapping("loginfo")
public class LoginfoController {

    ///loginfo/loadAllLoginfo
    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo){
        //loginfoVo接受前台的数据
        return loginfoService.loadAllLoginfo(loginfoVo);



    }

    //deleteLoginfo
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
     try {
         loginfoService.deleteLoginfo(id);
         return ResultObj.DELETE_SUCESS;
     }catch (Exception e){

         return ResultObj.DELETE_ERROR;
     }

    }

    //batchDeleteLoginfo

    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            loginfoService.batchDeleteLoginfo(ids);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }

}


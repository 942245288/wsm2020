package com.bigdata.sys.controller;


import com.bigdata.sys.domain.Notice;
import com.bigdata.sys.domain.User;
import com.bigdata.sys.service.NoticeService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.utils.ResultObj;
import com.bigdata.sys.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigdata
 * @since 2020-04-07
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        //noticeVo接受前台的数据
        return noticeService.loadAllNotice(noticeVo);

    }

    //deleteNotice
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            noticeService.deleteNotice(id);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }

    //addNotice
    @RequestMapping("addNotice")
    public ResultObj addNotice(Notice notice, HttpSession session){
        try {
            //从session获取登录用户
            User user=(User)session.getAttribute("user");
            notice.setOpername(user.getName());//设置发布人
            notice.setCreatetime(new Date());
            noticeService.addNotice(notice);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }
    //updateNotice

    @RequestMapping("updateNotice")
    public ResultObj updateNotice(Notice notice){
        try {

            noticeService.updateNotice(notice);

            return ResultObj.ADD_SUCESS;
        }catch (Exception e){

            return ResultObj.ADD_ERROR;
        }


    }

    //batchDeleteNotice

    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(Integer[] ids){
        try {
            noticeService.batchDeleteNotice(ids);
            return ResultObj.DELETE_SUCESS;
        }catch (Exception e){

            return ResultObj.DELETE_ERROR;
        }

    }

    //loadNoticeById
    @RequestMapping("loadNoticeById")
    public DataGridView loadNoticeById(Integer id){
        return  new DataGridView(noticeService.getById(id));

    }

}


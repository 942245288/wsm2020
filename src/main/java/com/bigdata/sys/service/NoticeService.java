package com.bigdata.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.sys.domain.Notice;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.NoticeVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigdata
 * @since 2020-04-07
 */
public interface NoticeService extends IService<Notice> {


    DataGridView loadAllNotice(NoticeVo noticeVo);

    void deleteNotice(Integer id);

    void batchDeleteNotice(Integer[] ids);

    void addNotice(Notice notice);

    void updateNotice(Notice notice);
}

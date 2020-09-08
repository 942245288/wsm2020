package com.bigdata.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.sys.domain.Notice;
import com.bigdata.sys.mapper.NoticeMapper;
import com.bigdata.sys.service.NoticeService;
import com.bigdata.sys.utils.DataGridView;
import com.bigdata.sys.vo.NoticeVo;
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
 * @since 2020-04-07
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {




    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public DataGridView loadAllNotice(NoticeVo noticeVo) {
        QueryWrapper<Notice> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        qw.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        qw.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        qw.orderByDesc("createtime");

        //分页
        Page page=new Page<>(noticeVo.getPage(),noticeVo.getLimit());

        //查询数据库
        noticeMapper.selectPage(page,qw);

        return new DataGridView(page.getTotal(),page.getRecords());
    }


    @Transactional
    @Override
    public void deleteNotice(Integer id) {

        noticeMapper.deleteById(id);

    }

    @Transactional
    @Override
    public void batchDeleteNotice(Integer[] ids) {
        Collection<Serializable> idList=new ArrayList<>();
        for(Integer i:ids){
            idList.add(i);
        }
        noticeMapper.deleteBatchIds(idList);



    }

    @Transactional
    @Override
    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Transactional
    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateById(notice);
    }
}

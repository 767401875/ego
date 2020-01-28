package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.manager.service.ManagerContentService;
import com.qsq.ego.rpc.pojo.TbContent;
import com.qsq.ego.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentServiceImpl implements ManagerContentService {
    @Autowired
    TbContentService tbContentServiceProxy;
    @Override
    public PageResult<TbContent> loadTbContentService(Long categoryId, int pageNum, int rows) {
        PageResult<TbContent> tbContentPageResult = tbContentServiceProxy.selectContentList(categoryId, pageNum, rows);
        return tbContentPageResult;
    }

    @Override
    public EgoResult saveTbContentService(TbContent tbContent) {
        return tbContentServiceProxy.saveContent(tbContent);
    }

    @Override
    public EgoResult deleteTbContentService(String idsStr) {
        String[] ids = idsStr.split(",");
        List<Long> longList = new ArrayList<>();
        for(String id : ids){
            longList.add(Long.parseLong(id));
        }
        return tbContentServiceProxy.deleteContent(longList);
    }

    @Override
    public EgoResult editTbContentService(TbContent tbContent) {
        return tbContentServiceProxy.updateContent(tbContent);
    }
}

package com.qsq.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.mapper.TbContentMapper;
import com.qsq.ego.rpc.pojo.TbContent;
import com.qsq.ego.rpc.pojo.TbContentExample;
import com.qsq.ego.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    TbContentMapper tbContentMapper;
    @Override
    public PageResult<TbContent> selectContentList(Long categoryId, int pageNum, int rows) {
        PageResult<TbContent> pageResult = new PageResult<>();
        Page page  = PageHelper.startPage(pageNum, rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(tbContents);
        return pageResult;
    }

    @Override
    public EgoResult saveContent(TbContent tbContent) {
        EgoResult result = null;
        try {
            tbContent.setUpdated(new Date());
            tbContent.setCreated(new Date());
            tbContentMapper.insert(tbContent);
            result = new EgoResult();
            result.setStatus(200);
            result.setData(tbContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EgoResult deleteContent(List<Long> ids) {
        try {
            TbContentExample tbContentExample = new TbContentExample();
            TbContentExample.Criteria criteria = tbContentExample.createCriteria();
            criteria.andIdIn(ids);
            tbContentMapper.deleteByExample(tbContentExample);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EgoResult updateContent(TbContent tbContent) {
        tbContentMapper.updateByPrimaryKeySelective(tbContent);
        return EgoResult.ok();
    }

    @Override
    public List<TbContent> selectTbContentListByCid(Long categoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        return tbContents;
    }
}

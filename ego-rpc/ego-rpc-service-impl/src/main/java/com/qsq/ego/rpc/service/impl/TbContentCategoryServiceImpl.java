package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.mapper.TbContentCategoryMapper;
import com.qsq.ego.rpc.pojo.TbContentCategory;
import com.qsq.ego.rpc.pojo.TbContentCategoryExample;
import com.qsq.ego.rpc.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TbContentCategory> loadTbContentCategoryByPidService(Long pid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pid);
        List<TbContentCategory> res = tbContentCategoryMapper.selectByExample(example);
        return res;
    }

    @Override
    public EgoResult saveTbContentCategory(TbContentCategory tbContentCategory) {
        EgoResult result = null;
        try {
            Long pid = tbContentCategory.getParentId();
            TbContentCategory ptbContentCategory = new TbContentCategory();
            ptbContentCategory.setIsParent(true);
            ptbContentCategory.setId(pid);
            ptbContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKeySelective(ptbContentCategory);
            tbContentCategoryMapper.insertSelective(tbContentCategory);
            result = new EgoResult();
            result.setData(tbContentCategory);
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteTbContentCategory(Long id) {
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(contentCategory.getParentId());
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if(tbContentCategoryList != null&&tbContentCategoryList.size() == 1){
            TbContentCategory pContentCategory = new TbContentCategory();
            pContentCategory.setUpdated(new Date());
            pContentCategory.setIsParent(false);
            pContentCategory.setId(contentCategory.getParentId());
            tbContentCategoryMapper.updateByPrimaryKeySelective(pContentCategory);
        }
        tbContentCategoryMapper.deleteByPrimaryKey(id);
    }
}

package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.rpc.mapper.TbItemCatMapper;
import com.qsq.ego.rpc.pojo.TbItemCat;
import com.qsq.ego.rpc.pojo.TbItemCatExample;
import com.qsq.ego.rpc.pojo.TbItemParamItemExample;
import com.qsq.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> getItemCatListByParentId(Long id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        return  tbItemCats;
    }

    @Override
    public List<TbItemCat> loadItemCatList() {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        return tbItemCatMapper.selectByExample(tbItemCatExample);
    }
}

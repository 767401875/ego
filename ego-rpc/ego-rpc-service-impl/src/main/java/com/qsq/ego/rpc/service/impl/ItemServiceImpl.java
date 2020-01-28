package com.qsq.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.mapper.TbItemDescMapper;
import com.qsq.ego.rpc.mapper.TbItemMapper;
import com.qsq.ego.rpc.mapper.TbItemParamItemMapper;
import com.qsq.ego.rpc.pojo.*;
import com.qsq.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {
        Page ps = PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);
        PageResult<TbItem> result = new PageResult<>();
        result.setRows(list);
        result.setTotal(ps.getTotal());
        return result;
    }

    @Override
    public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag) {
        TbItem tbItem = new TbItem();
        if(flag){
            tbItem.setStatus((byte) 1);
        }else {
            tbItem.setStatus((byte) 2);
        }
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdIn(itemIds);
        tbItemMapper.updateByExampleSelective(tbItem, tbItemExample);
        return EgoResult.ok();
    }

    @Override
    public EgoResult deleteItem(List<Long> itemIds) {
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdIn(itemIds);
        tbItemMapper.deleteByExample(tbItemExample);
        return EgoResult.ok();
    }

    @Override
    public EgoResult saveItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem) {
        tbItemMapper.insert(item);
        tbItemDescMapper.insert(desc);
        tbItemParamItemMapper.insert(itemParamItem);
        return EgoResult.ok();
    }

    @Override
    public EgoResult updateItem(TbItem item, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) {
        this.tbItemMapper.updateByPrimaryKeySelective(item);
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria c = example.createCriteria();
        c.andItemIdEqualTo(tbItemDesc.getItemId());
        Integer count =  tbItemDescMapper.countByExample(example);
        if(count == 0){
            tbItemDescMapper.insert(tbItemDesc);
        }else {
            tbItemDesc.setCreated(null);
            tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        }

        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = itemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(item.getId());
        count = tbItemParamItemMapper.countByExample(itemParamItemExample);
        if(count == 0){
            tbItemParamItemMapper.insert(tbItemParamItem);
        }else {
            tbItemParamItem.setCreated(null);
            tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, itemParamItemExample);
        }
        return EgoResult.ok();
    }

}

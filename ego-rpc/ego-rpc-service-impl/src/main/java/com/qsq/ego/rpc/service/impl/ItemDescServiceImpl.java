package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.rpc.mapper.TbItemDescMapper;
import com.qsq.ego.rpc.pojo.TbItemDesc;
import com.qsq.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getItemDesc(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}

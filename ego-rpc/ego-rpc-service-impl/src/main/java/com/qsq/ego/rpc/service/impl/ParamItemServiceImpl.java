package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.rpc.service.ParamItemService;

import com.qsq.ego.rpc.mapper.TbItemParamItemMapper;
import com.qsq.ego.rpc.pojo.TbItemParamItem;
import com.qsq.ego.rpc.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamItemServiceImpl implements ParamItemService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem loadTbItemParamItemService(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null&&list.size() == 1){
            return list.get(0);
        }
        return null;
    }
}

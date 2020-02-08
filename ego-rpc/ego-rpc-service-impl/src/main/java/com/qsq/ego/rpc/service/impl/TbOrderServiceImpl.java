package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.rpc.mapper.TbOrderItemMapper;
import com.qsq.ego.rpc.mapper.TbOrderMapper;
import com.qsq.ego.rpc.mapper.TbOrderShippingMapper;
import com.qsq.ego.rpc.pojo.*;
import com.qsq.ego.rpc.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbOrderServiceImpl implements TbOrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping) {
        try {
            tbOrderMapper.insert(tbOrder);
            for(TbOrderItem tbOrderItem : orderItems){
                tbOrderItemMapper.insert(tbOrderItem);
            }
            tbOrderShippingMapper.insert(tbOrderShipping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TbOrder> selectTbOrderListService(Long id) {
        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        return tbOrderMapper.selectByExample(example);
    }

    @Override
    public List<TbOrderItem> selectTbOrderItemListService(String orderId) {
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return tbOrderItemMapper.selectByExample(example);
    }
}

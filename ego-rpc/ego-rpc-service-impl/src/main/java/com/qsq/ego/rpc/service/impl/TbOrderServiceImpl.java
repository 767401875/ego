package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.rpc.mapper.TbOrderItemMapper;
import com.qsq.ego.rpc.mapper.TbOrderMapper;
import com.qsq.ego.rpc.mapper.TbOrderShippingMapper;
import com.qsq.ego.rpc.pojo.TbOrder;
import com.qsq.ego.rpc.pojo.TbOrderItem;
import com.qsq.ego.rpc.pojo.TbOrderShipping;
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
}

package com.qsq.ego.rpc.service;

import com.qsq.ego.rpc.pojo.TbOrder;
import com.qsq.ego.rpc.pojo.TbOrderItem;
import com.qsq.ego.rpc.pojo.TbOrderShipping;

import java.util.List;

public interface TbOrderService {
    public void saveTbOrderService(TbOrder tbOrder, List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping);
}

package com.qsq.ego.order.service;

import com.qsq.ego.order.entity.CarItem;
import com.qsq.ego.rpc.pojo.TbOrder;
import com.qsq.ego.rpc.pojo.TbOrderItem;
import com.qsq.ego.rpc.pojo.TbOrderShipping;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public Map<Long, CarItem> loadCarItemMapService(Long uid);
    public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping orderShipping);
    public List<TbOrder> loadOrderListService(Long uId);
    public List<TbOrderItem> loadOrderItemListService(String orderId);
}

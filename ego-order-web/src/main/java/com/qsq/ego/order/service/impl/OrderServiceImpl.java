package com.qsq.ego.order.service.impl;

import com.qsq.ego.beans.IDUtils;
import com.qsq.ego.order.dao.CarItemDao;
import com.qsq.ego.order.entity.CarItem;
import com.qsq.ego.order.service.OrderService;
import com.qsq.ego.rpc.pojo.TbItem;
import com.qsq.ego.rpc.pojo.TbOrder;
import com.qsq.ego.rpc.pojo.TbOrderItem;
import com.qsq.ego.rpc.pojo.TbOrderShipping;
import com.qsq.ego.rpc.service.TbOrderService;
import com.qsq.ego.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CarItemDao carItemDao;
    @Autowired
    private TbOrderService tbOrderServiceProxy;
    @Override
    public Map<Long, CarItem> loadCarItemMapService(Long uid) {
        Map<String, String> carMapStr = carItemDao.loadCarItemMap(String.valueOf(uid));
        Map<Long, CarItem> carItemMap = new HashMap<>();
        for(Map.Entry<String, String> entry : carMapStr.entrySet()){
            carItemMap.put(Long.parseLong(entry.getKey()), JsonUtils.jsonToPojo(entry.getValue(), CarItem.class));
        }
        return carItemMap;
    }

    @Override
    public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping orderShipping) {
        Date date = new Date();
        String orderId = String.valueOf(IDUtils.genItemId());
        tbOrder.setOrderId(orderId);
        tbOrder.setPostFee("123");
        tbOrder.setStatus(2);
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        tbOrder.setPaymentTime(date);
        tbOrder.setConsignTime(date);
        tbOrder.setEndTime(date);
        tbOrder.setCloseTime(date);
        tbOrder.setShippingName("EMS");
        tbOrder.setShippingCode("110110");
        tbOrder.setUserId(uid);
        tbOrder.setBuyerMessage("message");
        tbOrder.setBuyerNick("qsq");
        tbOrder.setBuyerRate(0);

        Map<Long, CarItem> carMap = loadCarItemMapService(uid);
        List<TbOrderItem> list = new ArrayList<>();
        for(CarItem carItem : carMap.values()){
            String id = String.valueOf(IDUtils.genItemId());
            TbOrderItem orderItem = new TbOrderItem();
            orderItem.setId(id);
            TbItem item = carItem.getTbItem();
            orderItem.setItemId(String.valueOf(item.getId()));
            orderItem.setOrderId(orderId);
            orderItem.setNum(carItem.getNum());
            orderItem.setTitle(item.getTitle());
            orderItem.setPrice(item.getPrice());
            orderItem.setTotalFee(item.getPrice()* carItem.getNum());
            orderItem.setPicPath(item.getImages()[0]);
            list.add(orderItem);
        }
        orderShipping.setOrderId(orderId);
        orderShipping.setReceiverPhone("1110");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        tbOrderServiceProxy.saveTbOrderService(tbOrder, list, orderShipping);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("total", tbOrder.getPayment());
        carItemDao.deleteCarItemMap(String.valueOf(uid));
        return map;
    }
}

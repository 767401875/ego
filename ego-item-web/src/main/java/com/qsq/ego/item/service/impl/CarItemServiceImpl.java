package com.qsq.ego.item.service.impl;

import com.qsq.ego.item.dao.CarItemDao;
import com.qsq.ego.item.entity.CarItem;
import com.qsq.ego.item.service.CarItemService;
import com.qsq.ego.rpc.pojo.TbItem;
import com.qsq.ego.rpc.service.ItemService;
import com.qsq.ego.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarItemServiceImpl implements CarItemService {
    @Autowired
    private CarItemDao carItemDao;
    @Autowired
    private ItemService itemServiceProxy;
    @Override
    public void addItemToCarService(Long itemId, Long uId) {
        Map<String, String> carMap = null;
        CarItem carItem = null;
        TbItem tbItem = itemServiceProxy.selectItemById(itemId);
        carMap = carItemDao.loadCarMap(String.valueOf(uId));
        if(carMap == null){
            carMap = new HashMap<>();
        }
        String carItemStr = carItemDao.loadCarItem(String.valueOf(uId), String.valueOf(itemId));
        if(StringUtils.isEmpty(carItemStr)){
            carItem = new CarItem();
            carItem.setTbItem(tbItem);
            carItem.setNum(1);
        }else {
            carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
            carItem.setNum(carItem.getNum() + 1);
        }
        String carItemJsonStr = JsonUtils.objectToJson(carItem);
        carMap.put(String.valueOf(itemId), carItemJsonStr);
        carItemDao.addCarMap(String.valueOf(uId), carMap);
    }

    @Override
    public Map<Long, CarItem> loadCarItemListService(Long uid) {
        Map<Long, CarItem> carMap = new HashMap<>();
        Map<String, String> carMapStr = carItemDao.loadCarMap(String.valueOf(uid));
        for (Map.Entry<String, String> e:carMapStr.entrySet()) {
            String itemId = e.getKey();
            String carItemStr = e.getValue();
            CarItem carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
            carMap.put(Long.valueOf(itemId), carItem);
        }
        return carMap;
    }

    @Override
    public String updateCarItemNumService(Long itemId, Long uId, Integer num) {
        try {
            String carItemStr = carItemDao.loadCarItem(String.valueOf(uId), String.valueOf(itemId));
            CarItem carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
            carItem.setNum(num);
            String carItemJson = JsonUtils.objectToJson(carItem);
            carItemDao.updateCarMapNum(String.valueOf(uId), String.valueOf(itemId), carItemJson);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCarItemService(Long itemId, Long uid) {
        carItemDao.deleteCarMapItem(String.valueOf(uid), String.valueOf(itemId));
    }

    @Override
    public void deleteCarItemAllService(Long uid) {
        carItemDao.deleteCarMapAll(String.valueOf(uid));
    }
}

package com.qsq.ego.item.service;

import com.qsq.ego.item.entity.CarItem;

import java.util.Map;

public interface CarItemService {
    public void addItemToCarService(Long itemId, Long uId);
    public Map<Long, CarItem> loadCarItemListService(Long uid);
    public String updateCarItemNumService(Long itemId, Long uId, Integer num);
    public void deleteCarItemService(Long itemId, Long uid);
    public void deleteCarItemAllService(Long uid);
}

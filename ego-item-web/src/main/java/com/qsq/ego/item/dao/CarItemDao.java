package com.qsq.ego.item.dao;

import java.util.Map;

public interface CarItemDao {
    public void addCarMap(String uId, Map<String, String> carMap);
    public Map<String, String> loadCarMap(String uId);
    public String loadCarItem(String uId, String itemId);
    public void updateCarMapNum(String uId, String itemId, String carItemStr);
    public void deleteCarMapItem(String uid, String itemid);
    public void deleteCarMapAll(String uid);
}

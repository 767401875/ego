package com.qsq.ego.order.dao;

import java.util.Map;

public interface CarItemDao {
    public Map<String, String> loadCarItemMap(String uid);
    public void deleteCarItemMap(String uid);
}

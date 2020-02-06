package com.qsq.ego.item.dao.impl;

import com.qsq.ego.item.dao.CarItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

@Repository
public class CarItemDaoImpl implements CarItemDao {
    @Autowired
    private JedisCluster cluster;
    @Override
    public void addCarMap(String uId, Map<String, String> carMap) {
        cluster.hmset(uId, carMap);
    }

    @Override
    public Map<String, String> loadCarMap(String uId) {
        return cluster.hgetAll(uId);
    }

    @Override
    public String loadCarItem(String uId, String itemId) {
        return cluster.hget(uId, itemId);
    }

    @Override
    public void updateCarMapNum(String uId, String itemId, String carItemStr) {
        cluster.hset(uId, itemId, carItemStr);
    }

    @Override
    public void deleteCarMapItem(String uid, String itemid) {
        cluster.hdel(uid, itemid);
    }

    @Override
    public void deleteCarMapAll(String uid) {
        cluster.del(uid);
    }
}

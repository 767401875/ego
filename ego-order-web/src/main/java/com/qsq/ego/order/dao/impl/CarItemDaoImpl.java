package com.qsq.ego.order.dao.impl;

import com.qsq.ego.order.dao.CarItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

@Repository
public class CarItemDaoImpl implements CarItemDao {
    @Autowired
    private JedisCluster cluster;
    @Override
    public Map<String, String> loadCarItemMap(String uid) {
        return cluster.hgetAll(uid);
    }

    @Override
    public void deleteCarItemMap(String uid) {
        cluster.del(uid);
    }
}

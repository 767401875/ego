package com.qsq.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterDemo {
    public static void main(String[] args) {
        HostAndPort node1 = new HostAndPort("106.12.34.249", 6380);
        HostAndPort node2 = new HostAndPort("106.12.34.249", 6381);
        HostAndPort node3 = new HostAndPort("106.12.34.249", 6382);
        HostAndPort node4 = new HostAndPort("106.12.34.249", 6383);
        HostAndPort node5 = new HostAndPort("106.12.34.249", 6384);
        HostAndPort node6 = new HostAndPort("106.12.34.249", 6385);
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name", "qsq");
        jedisCluster.set("age", "31");
        jedisCluster.set("sex", "男");

        String result = jedisCluster.get("sex1");
        System.out.println(result);
    }
}

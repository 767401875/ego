package com.qsq.ego.sso.service.impl;

import com.qsq.ego.beans.CookieUtils;
import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.pojo.TbUser;
import com.qsq.ego.rpc.service.TbUserService;
import com.qsq.ego.sso.service.SsoUserService;
import com.qsq.ego.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class SsoUserServiceImpl implements SsoUserService {
    @Autowired
    TbUserService tbUserServiceProxy;
    @Autowired
    JedisCluster cluster;

    @Override
    public EgoResult loadUserByCondService(String cond, Integer type) {
        return tbUserServiceProxy.selectTbUserByCondition(cond, type);
    }

    @Override
    public EgoResult saveUserService(TbUser tbUser) {
        String password = tbUser.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        tbUser.setPassword(password);
        return tbUserServiceProxy.saveUserTbService(tbUser);
    }

    @Override
    public EgoResult loadUserService(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TbUser tbUser = tbUserServiceProxy.selectTbUserByName(username);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        EgoResult result = new EgoResult();
        if(tbUser != null){
                if(password.equals(tbUser.getPassword())){
                String jsonStr = JsonUtils.objectToJson(tbUser);
                String token = UUID.randomUUID().toString();
                cluster.set(token, jsonStr);
                cluster.expire(token, 1800);
                CookieUtils.setCookie(request, response, "sso_token", token);
                result.setStatus(200);
                result.setMsg("OK");
                result.setData(token);
                return result;
            }
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult loadUserByToken(String token) {
        EgoResult result = new EgoResult();
        String jsonStr = cluster.get(token);
        if(!StringUtils.isEmpty(jsonStr)){
            result.setStatus(200);
//            result.setData(jsonStr);
            TbUser tbUser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
            result.setData(tbUser);
            result.setMsg("OK");
            return result;
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult deleteUserByToken(String token) {
        EgoResult result = new EgoResult();
        Long del = cluster.del(token);
        if(del != 0){
            result.setStatus(200);
            result.setMsg("OK");
            result.setData("");
            return result;
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }
}

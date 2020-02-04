package com.qsq.ego.rpc.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.mapper.TbUserMapper;
import com.qsq.ego.rpc.pojo.TbUser;
import com.qsq.ego.rpc.pojo.TbUserExample;
import com.qsq.ego.rpc.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public EgoResult selectTbUserByCondition(String condition, Integer type) {
        EgoResult egoResult = new EgoResult();
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if(type.equals(1)){
            criteria.andUsernameEqualTo(condition);
        }else if(type.equals(2)){
            criteria.andPhoneEqualTo(condition);
        }else {
            criteria.andEmailEqualTo(condition);
        }
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if(tbUsers != null&&tbUsers.size() > 0){
            egoResult.setData(false);
        }else {
            egoResult.setData(true);
        }
        egoResult.setStatus(200);
        egoResult.setMsg("OK");
        return egoResult;
    }

    @Override
    public EgoResult saveUserTbService(TbUser tbUser) {
        EgoResult result = new EgoResult();
        try {
            Date date = new Date();
            tbUser.setCreated(date);
            tbUser.setUpdated(date);
            tbUserMapper.insert(tbUser);
            result.setStatus(200);
            result.setMsg("注册成功");
        } catch (Exception e) {
            result.setStatus(400);
            result.setMsg("注册失败");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbUser selectTbUserByName(String userName) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if(tbUsers != null&&tbUsers.size() > 0){
            return tbUsers.get(0);
        }
        return null;
    }
}

package com.qsq.ego.rpc.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.pojo.TbUser;

public interface TbUserService {
    EgoResult selectTbUserByCondition(String condition, Integer type);
    EgoResult saveUserTbService(TbUser tbUser);
    TbUser selectTbUserByName(String userName);
}

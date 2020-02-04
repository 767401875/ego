package com.qsq.ego.sso.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SsoUserService {
    EgoResult loadUserByCondService(String cond, Integer type);
    EgoResult saveUserService(TbUser tbUser);
    EgoResult loadUserService(String username, String password, HttpServletRequest request, HttpServletResponse response);
    EgoResult loadUserByToken(String token);
    EgoResult deleteUserByToken(String token);
}

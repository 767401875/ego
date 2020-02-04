package com.qsq.ego.sso.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.pojo.TbUser;
import com.qsq.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SsoUserController {
    @Autowired
    SsoUserService ssoUserService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue userCheck(@PathVariable String param, @PathVariable Integer type, @RequestParam(required = false) String callback){
        EgoResult egoResult = ssoUserService.loadUserByCondService(param, type);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(egoResult);
        if(!StringUtils.isEmpty(callback)){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public EgoResult userRegister(TbUser tbUser){
        return ssoUserService.saveUserService(tbUser);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public EgoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        return ssoUserService.loadUserService(username, password, request, response);

    }

    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public MappingJacksonValue userToken(@PathVariable String token, @RequestParam(required = false) String callback){
        EgoResult egoResult = ssoUserService.loadUserByToken(token);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(egoResult);
        if(!StringUtils.isEmpty(callback)){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }

    @RequestMapping("/user/logout/{token}")
    @ResponseBody
    public MappingJacksonValue logoutUser(@PathVariable String token, @RequestParam(required = false) String callback){
        EgoResult egoResult = ssoUserService.deleteUserByToken(token);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(egoResult);
        if(!StringUtils.isEmpty(callback)){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }
}

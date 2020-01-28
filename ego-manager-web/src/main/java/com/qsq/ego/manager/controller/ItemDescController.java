package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.manager.service.ManagerItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemDescController {
    @Autowired
    private ManagerItemDescService managerItemDescService;
    @RequestMapping(value = "/query/item/desc/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemDesc(@PathVariable Long itemId){
        return managerItemDescService.getItemDescService(itemId);
    }
}

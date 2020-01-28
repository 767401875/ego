package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.manager.service.ManagerParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamItemParamController {
    @Autowired
    private ManagerParamItemService managerParamItemService;

    @RequestMapping(value = "/param/item/query/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    EgoResult paramItemQuery(@PathVariable Long itemId){
        return managerParamItemService.loadParamItemService(itemId);
    }
}

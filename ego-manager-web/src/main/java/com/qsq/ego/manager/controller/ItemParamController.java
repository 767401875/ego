package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.manager.service.ManagerItemParamService;
import com.qsq.ego.rpc.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ManagerItemParamService managerItemParamService;
    @RequestMapping(value = "/item/param/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public PageResult<TbItemParam> itemParamList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows){
        return managerItemParamService.loadItemParamListService(page, rows);
    }

    @RequestMapping(value = "/item/param/query/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemParamQuery(@PathVariable Long cid){
        return managerItemParamService.loadItemParamByCidService(cid);
    }

    @RequestMapping(value = "/item/param/save/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult saveItemParam(@PathVariable Long cid, String paramData){
        return managerItemParamService.saveItemParamService(cid, paramData);
    }
    @RequestMapping(value = "/item/param/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult deleteItemParam(Long[] ids){
        return managerItemParamService.deleteItemParamService(ids);
    }
    @RequestMapping(value = "/item/param/select/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemParamSelect(@PathVariable Long cid){
        return managerItemParamService.loadItemParamByCidService(cid);
    }
}

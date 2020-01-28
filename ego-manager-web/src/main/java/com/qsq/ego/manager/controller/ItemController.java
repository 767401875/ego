package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.manager.service.ManagerItemService;
import com.qsq.ego.rpc.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ManagerItemService managerItemService;

    @RequestMapping(value = "/item/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public PageResult<TbItem> itemList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "30") Integer rows){
        return managerItemService.selectItemListService(page, rows);
    }

    @RequestMapping(value = "/item/reshelf", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemReshelf(Long[] ids){
        return managerItemService.reshelfItemListService(ids);
    }

    @RequestMapping(value = "/item/instock", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemInstock(Long[] ids){
        return managerItemService.instockItem(ids);
    }

    @RequestMapping(value = "/item/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemDelete(Long[] ids){
        return managerItemService.deleteItemService(ids);
    }

    @RequestMapping(value = "item/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemSave(TbItem item, String desc, String itemParams){
        return managerItemService.saveItemService(item, desc, itemParams);
    }
    @RequestMapping(value = "item/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public EgoResult itemUpdate(TbItem item, String desc, String itemParams){
        return managerItemService.updateItemService(item, desc, itemParams);
    }

}

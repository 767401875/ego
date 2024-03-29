package com.qsq.ego.search.controller;

import com.qsq.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemDescController {
    @Autowired
    private SearchItemDescService searchItemDescService;

    @RequestMapping(value = "/item/desc/{itemId}")
    @ResponseBody
    public String loadItemDesc(@PathVariable Long itemId){
        return searchItemDescService.loadItemDescService(itemId);
    }

}

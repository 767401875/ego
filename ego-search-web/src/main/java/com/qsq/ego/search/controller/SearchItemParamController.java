package com.qsq.ego.search.controller;

import com.qsq.ego.search.service.SearchItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemParamController {
    @Autowired
    private SearchItemParamService searchItemParamService;

    @RequestMapping(value = "/item/param/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String loadItemParam(@PathVariable Long id){
        return searchItemParamService.loadItemParamService(id);
    }
}

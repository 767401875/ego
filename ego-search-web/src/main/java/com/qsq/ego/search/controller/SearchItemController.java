package com.qsq.ego.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemController {
    @RequestMapping("/{url}")
    public String page(@PathVariable String url){
        return url;
    }
}

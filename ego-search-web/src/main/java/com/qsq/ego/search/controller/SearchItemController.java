package com.qsq.ego.search.controller;

import com.qsq.ego.search.entity.SearchResult;
import com.qsq.ego.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;
    @RequestMapping("/{url}")
    public String page(@PathVariable String url, String q, @RequestParam(defaultValue = "1") Integer page, Model model){
        String kws = null;
        try {
            kws = new String(q.getBytes("ISO-8859-1"), "UTF-8");
            SearchResult result = searchItemService.loadItemService(kws, page);
            model.addAttribute("query", kws);
            model.addAttribute("itemList", result.getList());
            model.addAttribute("page", page);
            model.addAttribute("maxpage", result.getMaxpage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}

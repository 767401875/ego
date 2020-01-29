package com.qsq.ego.portal.controller;

import com.qsq.ego.portal.service.PortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PortalContentController {
    @Autowired
    private PortalContentService portalContentService;

    @RequestMapping(value = "/content/index/list", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String contentList(Long categoryId){
        return portalContentService.loadTbContentService(categoryId);
    }
}

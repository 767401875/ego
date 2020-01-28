package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.manager.service.ManagerContentService;
import com.qsq.ego.rpc.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    ManagerContentService managerContentService;

    @RequestMapping(value = "/content/query/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    PageResult<TbContent> contentList(Long categoryId, int page, int rows) {
        return managerContentService.loadTbContentService(categoryId, page, rows);
    }

    @RequestMapping(value = "/content/save", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    EgoResult saveContent(TbContent tbContent) {
        return managerContentService.saveTbContentService(tbContent);
    }

    @RequestMapping(value = "/content/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    EgoResult deleteContents(String ids) {
        return managerContentService.deleteTbContentService(ids);
    }
}

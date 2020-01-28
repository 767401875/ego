package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.TreeNode;
import com.qsq.ego.manager.service.ManagerContentCategoryService;
import com.qsq.ego.rpc.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    ManagerContentCategoryService managerContentCategoryService;

    @RequestMapping(value = "/content/category/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    List<TreeNode> contentCategoryList(@RequestParam(defaultValue = "0") Long id){
        return managerContentCategoryService.loadContentCategoryService(id);
    }
    @RequestMapping(value = "/content/category/create", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    EgoResult saveContentCategory(TbContentCategory tbContentCategory){
        return managerContentCategoryService.saveContentCategoryService(tbContentCategory);
    }
    @RequestMapping(value = "/content/category/delete", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    EgoResult deleteContentCategory(Long id){
        managerContentCategoryService.deleteContentCategoryService(id);
        return null;
    }
}

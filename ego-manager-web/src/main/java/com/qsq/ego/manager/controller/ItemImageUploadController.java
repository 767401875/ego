package com.qsq.ego.manager.controller;

import com.qsq.ego.beans.PictureResult;
import com.qsq.ego.manager.service.ManagerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ItemImageUploadController {
    @Autowired
    private ManagerItemService managerItemService;

    @RequestMapping(value = "/pic/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public PictureResult picUpload(MultipartFile uploadFile){
        return managerItemService.uploadItemPic(uploadFile);
    }
}

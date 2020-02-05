package com.qsq.ego.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarItemController {
    @RequestMapping("/cart/add/{itemId}")
    public String carAdd(@PathVariable Long itemId){
        return "cartSuccess";
    }
}

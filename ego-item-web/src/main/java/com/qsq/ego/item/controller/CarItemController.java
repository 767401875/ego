package com.qsq.ego.item.controller;

import com.qsq.ego.item.entity.CarItem;
import com.qsq.ego.item.service.CarItemService;
import com.qsq.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CarItemController {
    @Autowired
    private CarItemService carItemService;
    @RequestMapping("/cart/add/{itemId}")
    public String carAdd(@PathVariable Long itemId, HttpServletRequest request){
        TbUser tbUser = (TbUser)request.getAttribute("user");
        carItemService.addItemToCarService(itemId, tbUser.getId());
        return "cartSuccess";
    }

    @RequestMapping("/cart/cart")
    public String loadCarItemList(HttpServletRequest request){
        TbUser tbUser = (TbUser)request.getAttribute("user");
        Map<Long, CarItem> carMap = carItemService.loadCarItemListService(tbUser.getId());
        request.setAttribute("carMap", carMap);
        return "cart";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public String carUpdateItem(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request){
        TbUser tbUser = (TbUser)request.getAttribute("user");
        return carItemService.updateCarItemNumService(itemId, tbUser.getId(), num);
    }
    @RequestMapping("/cart/delete/{itemId}")
    public String carDelete(@PathVariable Long itemId, HttpServletRequest request){
        TbUser tbUser = (TbUser)request.getAttribute("user");
        carItemService.deleteCarItemService(itemId, tbUser.getId());
        return "redirect:/cart/cart.html";
    }
    @RequestMapping("/delete/cart/all")
    public String carDeleteAll(HttpServletRequest request){
        TbUser tbUser = (TbUser)request.getAttribute("user");
        carItemService.deleteCarItemAllService(tbUser.getId());
        return "redirect:/cart/cart.html";
    }
}

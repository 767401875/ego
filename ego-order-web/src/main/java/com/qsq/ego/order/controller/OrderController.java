package com.qsq.ego.order.controller;

import com.qsq.ego.order.entity.CarItem;
import com.qsq.ego.order.service.OrderService;
import com.qsq.ego.rpc.pojo.TbOrder;
import com.qsq.ego.rpc.pojo.TbOrderShipping;
import com.qsq.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/order/cart")
    public String orderCart(HttpServletRequest request){
        TbUser tbUser = (TbUser) request.getAttribute("user");
        Map<Long, CarItem> carMap = orderService.loadCarItemMapService(tbUser.getId());
        request.setAttribute("carMap", carMap);
        return "ordercart";
    }

    @RequestMapping("/order/save")
    public String orderSave(TbOrder tbOrder, TbOrderShipping tbOrderShipping, HttpServletRequest request){
        TbUser tbUser = (TbUser) request.getAttribute("user");
        Map<String, String> orderMap = orderService.saveOrderService(tbOrder, tbUser.getId(), tbOrderShipping);
        request.setAttribute("orderId", orderMap.get("orderId"));
        request.setAttribute("total", orderMap.get("total"));
        return "success";
    }
}

package com.qsq.ego.portal.service.impl;

import com.qsq.ego.portal.beans.CatNode;
import com.qsq.ego.portal.beans.CatResult;
import com.qsq.ego.portal.service.PortalItemCatService;
import com.qsq.ego.portal.utils.JsonUtils;
import com.qsq.ego.rpc.pojo.TbItemCat;
import com.qsq.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {
    @Autowired
    ItemCatService itemCatServiceProxy;

    @Override
    public String loadItemCatService() {
        List<TbItemCat> itemCatList = itemCatServiceProxy.loadItemCatList();
        CatResult catResult = new CatResult();
        List<?> data = getChildren(0L, itemCatList);
        catResult.setData(data);
        String resStr = JsonUtils.objectToJson(catResult);
        return resStr;
    }
    private List<?> getChildren(Long parentId, List<TbItemCat> itemCats) {
        // 盛放指定分类下的所有子分类信息
        List resultList = new ArrayList();

        for (TbItemCat itemCat : itemCats) {

            if (itemCat.getParentId().equals(parentId)) {
                if (itemCat.getIsParent()) {
                    // 如果itemCat代表一级分类或者二级分类

                    CatNode catNode = new CatNode();

                    if (itemCat.getParentId().longValue() == 0) {
                        // 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
                        catNode.setName(
                                "<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    } else {
                        // 如果是二级分类 "电子书刊",
                        catNode.setName(itemCat.getName());
                    }
                    // "/products/2.html",
                    catNode.setUrl("/products/" + itemCat.getId() + ".html");
                    catNode.setList(getChildren(itemCat.getId(), itemCats));
                    // 将节点添加到list集合中
                    resultList.add(catNode);
                } else {
                    // 如果itemCat表示三级分类 "/products/3.html|电子书",
                    resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
                }
            }
        }
        return resultList;
    }
}

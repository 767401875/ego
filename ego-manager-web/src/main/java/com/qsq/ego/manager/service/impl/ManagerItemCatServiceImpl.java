package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.TreeNode;
import com.qsq.ego.manager.service.ManagerItemCatService;
import com.qsq.ego.rpc.pojo.TbItemCat;
import com.qsq.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {
    @Autowired
    private ItemCatService itemCatServiceProxy;
    @Override
    public List<TreeNode> getItemCatList(Long id) {
        List<TbItemCat> list = itemCatServiceProxy.getItemCatListByParentId(id);
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode node = null;
        for(TbItemCat cat : list){
            node = new TreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed" : "open");
            nodeList.add(node);
        }
        return nodeList;
    }
}

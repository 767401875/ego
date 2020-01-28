package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.TreeNode;
import com.qsq.ego.manager.service.ManagerContentCategoryService;
import com.qsq.ego.rpc.pojo.TbContentCategory;
import com.qsq.ego.rpc.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {
    @Autowired
    TbContentCategoryService tbContentCategoryServiceProxy;
    @Override
    public List<TreeNode> loadContentCategoryService(Long pid) {
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryServiceProxy.loadTbContentCategoryByPidService(pid);
        List<TreeNode> treeNodes = new ArrayList<>();
        for(TbContentCategory tbContentCategory : tbContentCategoryList){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(tbContentCategory.getId());
            treeNode.setText(tbContentCategory.getName());
            treeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }

    @Override
    public EgoResult saveContentCategoryService(TbContentCategory tbContentCategory) {
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        return tbContentCategoryServiceProxy.saveTbContentCategory(tbContentCategory);
    }

    @Override
    public void deleteContentCategoryService(Long id) {
        tbContentCategoryServiceProxy.deleteTbContentCategory(id);
    }
}

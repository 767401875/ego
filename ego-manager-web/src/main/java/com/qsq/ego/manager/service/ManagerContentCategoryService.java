package com.qsq.ego.manager.service;


import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.TreeNode;
import com.qsq.ego.rpc.pojo.TbContentCategory;

import java.util.List;

public interface ManagerContentCategoryService {
    List<TreeNode> loadContentCategoryService(Long pid);
    EgoResult saveContentCategoryService(TbContentCategory tbContentCategory);
    void deleteContentCategoryService(Long id);
}

package com.qsq.ego.rpc.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.rpc.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    List<TbContentCategory> loadTbContentCategoryByPidService(Long pid);
    EgoResult saveTbContentCategory(TbContentCategory tbContentCategory);
    void deleteTbContentCategory(Long id);

}

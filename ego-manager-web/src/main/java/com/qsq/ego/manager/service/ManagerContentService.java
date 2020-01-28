package com.qsq.ego.manager.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.pojo.TbContent;

public interface ManagerContentService {
    PageResult<TbContent> loadTbContentService(Long categoryId, int pageNum, int rows);
    EgoResult saveTbContentService(TbContent tbContent);
    EgoResult deleteTbContentService(String idsStr);
    EgoResult editTbContentService(TbContent tbContent);
}

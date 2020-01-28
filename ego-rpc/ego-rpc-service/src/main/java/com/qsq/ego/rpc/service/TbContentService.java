package com.qsq.ego.rpc.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.pojo.TbContent;

import java.util.List;

public interface TbContentService {
    PageResult<TbContent> selectContentList(Long categoryId, int pageNum, int rows);
    EgoResult saveContent(TbContent tbContent);
    EgoResult deleteContent(List<Long> ids);
    EgoResult updateContent(TbContent tbContent);
}

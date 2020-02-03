package com.qsq.ego.search.service;

import com.qsq.ego.rpc.pojo.TbItem;
import com.qsq.ego.search.entity.SearchResult;

public interface SearchItemService {
    public SearchResult loadItemService(String item_keywords, Integer page);
    public TbItem loadItemService(Long id);
}

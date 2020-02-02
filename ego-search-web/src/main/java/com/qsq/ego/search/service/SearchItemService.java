package com.qsq.ego.search.service;

import com.qsq.ego.search.entity.SearchResult;

public interface SearchItemService {
    public SearchResult loadItemService(String item_keywords, Integer page);
}

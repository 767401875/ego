package com.qsq.ego.search.service.impl;

import com.qsq.ego.search.dao.ItemDao;
import com.qsq.ego.search.entity.Item;
import com.qsq.ego.search.entity.SearchResult;
import com.qsq.ego.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private ItemDao itemDaoImpl;

    @Override
    public SearchResult loadItemService(String item_keywords, Integer page) {
        SolrQuery param = new SolrQuery();
        param.set("df", "item_keywords");
        if(!StringUtils.isEmpty(item_keywords)){
            param.setQuery(item_keywords);
        }else {
            param.set("q", "*:*");
        }
        Integer rows = 20;
        if(page < 1){
            page = 1;
        }
        Integer maxpage = 100;
        if(page > maxpage){
            page = maxpage;
        }
        Integer start = (page - 1) * rows;
        param.setStart(start);
        param.setRows(rows);

        param.setHighlight(true);
        param.addHighlightField("title");
        param.setHighlightSimplePre("<font color='red'>");
        param.setHighlightSimplePost("</font>");
        QueryResponse response = itemDaoImpl.loadItem(param);
        SolrDocumentList docList = response.getResults();
        Map<String, Map<String, List<String>>> hlts = response.getHighlighting();
        long total = docList.getNumFound();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<Item> list = binder.getBeans(Item.class, docList);
        for(Item t : list){
            String id = t.getId();
            Map<String, List<String>> map = hlts.get(id);
            List<String> lts = map.get("title");
            if(lts != null&&lts.size() > 0){
                t.setTitle(lts.get(0));
            }
        }
        SearchResult result = new SearchResult();
        result.setMaxpage(Long.parseLong(String.valueOf(maxpage)));
        result.setTotal(total);
        result.setList(list);
        return result;
    }
}

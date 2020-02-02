package com.qsq.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface ItemDao {
    QueryResponse loadItem(SolrQuery params);
}

package com.qsq.ego.search.dao.impl;

import com.qsq.ego.search.dao.ItemDao;
import com.qsq.ego.search.utils.CloudSolrClientUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ItemDaoImpl implements ItemDao {
    @Override
    public QueryResponse loadItem(SolrQuery params) {
        try {
            CloudSolrClient solrServer = CloudSolrClientUtils.getCloudSolrClient();
            solrServer.setDefaultCollection("qsq");
            return solrServer.query(params);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

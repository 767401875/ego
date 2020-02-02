package com.qsq.ego.search.utils;

import org.apache.solr.client.solrj.impl.CloudSolrClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloudSolrClientUtils {
    public static CloudSolrClient getCloudSolrClient() {
        List<String> zkHosts = new ArrayList<String>();
        zkHosts.add("106.12.34.249:2181");
        zkHosts.add("106.12.34.249:2182");
        zkHosts.add("106.12.34.249:2183");
        Optional<String> zkChroot = Optional.empty();
        return new CloudSolrClient.Builder(zkHosts, zkChroot).withConnectionTimeout(3000)
                .withSocketTimeout(3000).build();
    }
}

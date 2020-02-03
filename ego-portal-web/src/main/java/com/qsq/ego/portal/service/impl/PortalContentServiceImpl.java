package com.qsq.ego.portal.service.impl;

import com.qsq.ego.portal.beans.BigPicture;
import com.qsq.ego.portal.service.PortalContentService;
import com.qsq.ego.rpc.pojo.TbContent;
import com.qsq.ego.rpc.service.TbContentService;
import com.qsq.ego.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalContentServiceImpl implements PortalContentService {
    @Value("${CONTENT_PICTURE}")
    private String contentPicKey;
    @Autowired
    private TbContentService tbContentServiceProxy;
    @Autowired
    private JedisCluster cluster;

    @Override
    public String loadTbContentService(Long categoryId) {
        String resStr = cluster.get(contentPicKey);
        if(!StringUtils.isEmpty(resStr)){
            return resStr;
        }
        List<TbContent> tbContents = tbContentServiceProxy.selectTbContentListByCid(categoryId);
        List<BigPicture> bigPictureList = new ArrayList<>();
        for(TbContent tbContent : tbContents){
            BigPicture bigPicture = new BigPicture();
            bigPicture.setAlt(tbContent.getTitle());
            bigPicture.setHref(tbContent.getUrl());

            bigPicture.setSrc(tbContent.getPic());
            bigPicture.setHeight(240);
            bigPicture.setWidth(670);

            bigPicture.setSrcB(tbContent.getPic2());
            bigPicture.setHeight(240);
            bigPicture.setWidth(550);
            bigPictureList.add(bigPicture);
        }
        resStr = JsonUtils.objectToJson(bigPictureList);
        cluster.set(contentPicKey, resStr);
        return resStr;
    }
}

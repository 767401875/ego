package com.qsq.ego.portal.service.impl;

import com.qsq.ego.portal.beans.BigPicture;
import com.qsq.ego.portal.service.PortalContentService;
import com.qsq.ego.portal.utils.JsonUtils;
import com.qsq.ego.rpc.pojo.TbContent;
import com.qsq.ego.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalContentServiceImpl implements PortalContentService {
    @Autowired
    private TbContentService tbContentServiceProxy;

    @Override
    public String loadTbContentService(Long categoryId) {
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
        String resStr = JsonUtils.objectToJson(bigPictureList);
        return resStr;
    }
}

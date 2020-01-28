package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.*;
import com.qsq.ego.manager.service.ManagerItemService;
import com.qsq.ego.rpc.pojo.TbItem;
import com.qsq.ego.rpc.pojo.TbItemDesc;
import com.qsq.ego.rpc.pojo.TbItemParamItem;
import com.qsq.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ManagerItemServiceImpl implements ManagerItemService {
    @Autowired
    private ItemService itemServiceProxy;
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH}")
    private String FTP_PATH;
    @Value("${IMAGE_HTTP_PATH}")
    private String IMAGE_HTTP_PATH;
    @Override
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {
        return itemServiceProxy.selectItemList(page, rows);
    }

    @Override
    public EgoResult reshelfItemListService(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        return itemServiceProxy.updateItemStatus(list, true);
    }

    @Override
    public EgoResult instockItem(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        return itemServiceProxy.updateItemStatus(list, false);
    }

    @Override
    public EgoResult deleteItemService(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        return itemServiceProxy.deleteItem(list);
    }

    @Override
    public PictureResult uploadItemPic(MultipartFile file) {
        boolean flag = false;
        String filename = null;
        try {
            filename = IDUtils.genImageName();
            String oriName = file.getOriginalFilename();
            String ext = oriName.substring(oriName.lastIndexOf("."));
            filename += ext;
            InputStream local = file.getInputStream();
            flag = FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH, filename, local);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        PictureResult result = new PictureResult();
        if(flag){
            result.setError(0);
            result.setUrl(IMAGE_HTTP_PATH + "/" + filename);
            result.setMessage("ok");
        }else {
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }

    @Override
    public EgoResult saveItemService(TbItem tbItem, String tbItemDesc, String paramData) {
        Date date = new Date();
        Long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        TbItemDesc desc = new TbItemDesc();
        desc.setItemDesc(tbItemDesc);
        desc.setItemId(id);
        desc.setCreated(date);
        desc.setUpdated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        return itemServiceProxy.saveItem(tbItem, desc, tbItemParamItem);
    }

    @Override
    public EgoResult updateItemService(TbItem tbItem, String desc, String paramData) {
        Date date = new Date();
        tbItem.setUpdated(date);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setCreated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setItemId(tbItem.getId());
        return itemServiceProxy.updateItem(tbItem, tbItemDesc, tbItemParamItem);
    }
}

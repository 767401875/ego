package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.manager.service.ManagerItemParamService;
import com.qsq.ego.manager.service.ManagerItemService;
import com.qsq.ego.rpc.pojo.TbItemParam;
import com.qsq.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {
    @Autowired
    ItemParamService itemParamServiceProxy;
    @Override
    public PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows) {
        return itemParamServiceProxy.loadTbItemParamListService(page, rows);
    }

    @Override
    public EgoResult loadItemParamByCidService(Long cid) {
        EgoResult result = null;
        try {
            result = null;
            TbItemParam tbItemParam = itemParamServiceProxy.loadTbItemParamByCidService(cid);
            result = new EgoResult();
            result.setStatus(200);
            result.setData(tbItemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EgoResult saveItemParamService(Long cid, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        Date date = new Date();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
    }

    @Override
    public EgoResult deleteItemParamService(Long[] ids) {
        List idList = Arrays.asList(ids);
        return itemParamServiceProxy.deleteTbITemParamService(idList);
    }
}

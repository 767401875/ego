package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.manager.service.ManagerParamItemService;
import com.qsq.ego.rpc.pojo.TbItemParamItem;
import com.qsq.ego.rpc.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {
    @Autowired
    private ParamItemService paramItemServiceProxy;
    @Override
    public EgoResult loadParamItemService(Long itemId) {
        EgoResult egoResult = null;
        try {
            TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamItemService(itemId);
            egoResult = new EgoResult();
            egoResult.setStatus(200);
            egoResult.setData(tbItemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return egoResult;
    }
}

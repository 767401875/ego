package com.qsq.ego.manager.service.impl;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.manager.service.ManagerItemDescService;
import com.qsq.ego.rpc.pojo.TbItemDesc;
import com.qsq.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {
    @Autowired
    private ItemDescService itemDescServiceProxy;

    @Override
    public EgoResult getItemDescService(Long itemId) {
        TbItemDesc desc = itemDescServiceProxy.getItemDesc(itemId);
        if(desc != null){
            return new EgoResult(desc);
        }
        return new EgoResult(null);
    }
}

package com.qsq.ego.search.service.impl;

import com.qsq.ego.rpc.pojo.TbItemDesc;
import com.qsq.ego.rpc.service.ItemDescService;
import com.qsq.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {
    @Autowired
    private ItemDescService itemDescServiceProxy;

    @Override
    public String loadItemDescService(Long id) {
        TbItemDesc itemDesc = itemDescServiceProxy.getItemDesc(id);
        return itemDesc.getItemDesc();
    }
}

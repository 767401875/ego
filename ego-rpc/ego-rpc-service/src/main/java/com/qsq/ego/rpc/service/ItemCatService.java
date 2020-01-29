package com.qsq.ego.rpc.service;

import com.qsq.ego.rpc.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    List<TbItemCat> getItemCatListByParentId(Long id);
    List<TbItemCat> loadItemCatList();
}

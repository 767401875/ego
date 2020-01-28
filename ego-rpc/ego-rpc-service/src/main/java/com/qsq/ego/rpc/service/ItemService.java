package com.qsq.ego.rpc.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.pojo.TbItem;
import com.qsq.ego.rpc.pojo.TbItemDesc;
import com.qsq.ego.rpc.pojo.TbItemParam;
import com.qsq.ego.rpc.pojo.TbItemParamItem;

import java.util.List;

public interface ItemService {
    PageResult<TbItem> selectItemList(Integer page, Integer rows);
    EgoResult updateItemStatus(List<Long> itemIds, Boolean flag);
    EgoResult deleteItem(List<Long> itemIds);
    EgoResult saveItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem);
    EgoResult updateItem(TbItem item, TbItemDesc tbItemDesc, TbItemParamItem itemParamItem);
}

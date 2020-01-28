package com.qsq.ego.rpc.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.pojo.TbItemParam;

import java.util.List;

public interface ItemParamService {
    PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows);
    TbItemParam loadTbItemParamByCidService(Long cid);
    EgoResult saveTbItemParamService(TbItemParam tbItemParam);
    EgoResult deleteTbITemParamService(List<Long> ids);
}

package com.qsq.ego.manager.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.pojo.TbItemParam;

public interface ManagerItemParamService {
    PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows);
    EgoResult loadItemParamByCidService(Long cid);
    EgoResult saveItemParamService(Long cid, String paramData);
    EgoResult deleteItemParamService(Long[] ids);
}

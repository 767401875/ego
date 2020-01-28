package com.qsq.ego.manager.service;

import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.beans.PictureResult;
import com.qsq.ego.rpc.pojo.TbItem;
import org.springframework.web.multipart.MultipartFile;

public interface ManagerItemService {
    PageResult<TbItem> selectItemListService(Integer page, Integer rows);
    EgoResult reshelfItemListService(Long[] ids);
    EgoResult instockItem(Long[] ids);
    EgoResult deleteItemService(Long[] ids);
    PictureResult uploadItemPic(MultipartFile file);
    EgoResult saveItemService(TbItem tbItem, String tbItemDesc, String paramData);
    EgoResult updateItemService(TbItem tbItem, String desc, String paramData);
}

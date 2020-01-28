package com.qsq.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qsq.ego.beans.EgoResult;
import com.qsq.ego.beans.PageResult;
import com.qsq.ego.rpc.mapper.TbItemParamMapper;
import com.qsq.ego.rpc.pojo.TbItemParam;
import com.qsq.ego.rpc.pojo.TbItemParamExample;
import com.qsq.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows) {
        PageResult<TbItemParam> result = new PageResult<>();
        Page  pe = PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        result.setRows(list);
        result.setTotal(pe.getTotal());
        return result;
    }

    @Override
    public TbItemParam loadTbItemParamByCidService(Long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list != null&&list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public EgoResult saveTbItemParamService(TbItemParam tbItemParam) {
        try {
            tbItemParamMapper.insertSelective(tbItemParam);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EgoResult deleteTbITemParamService(List<Long> ids) {
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            tbItemParamMapper.deleteByExample(example);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

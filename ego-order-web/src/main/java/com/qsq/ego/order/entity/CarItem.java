package com.qsq.ego.order.entity;

import com.qsq.ego.rpc.pojo.TbItem;

import java.io.Serializable;

public class CarItem implements Serializable {
    private TbItem tbItem;
    private Integer num;

    public TbItem getTbItem() {
        return tbItem;
    }

    public void setTbItem(TbItem tbItem) {
        this.tbItem = tbItem;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

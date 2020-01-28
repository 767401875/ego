package com.qsq.ego.beans;

import java.io.Serializable;

public class EgoResult implements Serializable {
    private Integer status;
    private Object data;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EgoResult(Integer status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public EgoResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public EgoResult(Object data) {
        this.data = data;
    }

    public EgoResult() {
        super();
    }
    public static EgoResult ok(){
        return new EgoResult(null);
    }
}

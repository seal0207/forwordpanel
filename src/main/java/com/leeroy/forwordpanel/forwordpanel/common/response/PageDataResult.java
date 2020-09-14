package com.leeroy.forwordpanel.forwordpanel.common.response;

import java.util.List;

/**
 * @Title: PageDataResult
 * @Description: 封装DTO分页数据（记录数和所有记录）
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:15
 */
public class PageDataResult {

    private Integer code=200;

    //总记录数量
    private Long total;

    private List<?> list;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List <?> getList() {
        return list;
    }

    public void setList(List <?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDataResult{" +
                "code=" + code +
                ", totals=" + total +
                ", list=" + list +
                '}';
    }
}

package com.biz.lesson.vo.finance;

import java.math.BigDecimal;

import com.biz.lesson.vo.PersistentVo;

/**
 * Created by johnzheng on 09/02/2017.
 */
public class MonetarysVo extends PersistentVo{

    private Integer[] monetaryId;

    private BigDecimal[] rate;


    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal[] getRate() {
        return rate;
    }

    public void setRate(BigDecimal[] rate) {
        this.rate = rate;
    }

    public Integer[] getMonetaryId() {
        return monetaryId;
    }

    public void setMonetaryId(Integer[] monetaryId) {
        this.monetaryId = monetaryId;
    }

    public Integer getMonetaryId(int index){
        return monetaryId[index];
    }

    public BigDecimal getRate(int index){
        return rate[index];
    }


}

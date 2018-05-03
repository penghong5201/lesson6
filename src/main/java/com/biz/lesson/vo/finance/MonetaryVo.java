package com.biz.lesson.vo.finance;

import org.hibernate.validator.constraints.Length;

import com.biz.lesson.Constants;
import com.biz.lesson.vo.PersistentVo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by johnzheng on 10/02/2017.
 */
public class MonetaryVo extends PersistentVo {



    /**
     * 汇率 针对欧元
     */
    @Length(max = 18)
    @Digits(integer=16, fraction=2)
    private java.math.BigDecimal rate = new BigDecimal("0.00");
    /**
     * 名称
     */
    @NotNull
    @Length(max = 50, min = 1)
    private String name;

    @NotNull
    @Length(max = 50, min = 1)
    private String nameEn;
    /**
     * 符号
     */
    @Length(max = 20)
    private String symbol;
    /**
     * 文档符号
     */
    @Length(max = 50)
    private String docSymbol;

  

    public java.math.BigDecimal getRate() {
        return this.rate;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getDocSymbol() {
        return this.docSymbol;
    }

    public Integer getMonetaryId() {
        try {
            return Integer.parseInt(id);
        }catch (Exception e) {
            return null;
        }
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setRate(java.math.BigDecimal value) {
        this.rate = value;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void setSymbol(String value) {
        this.symbol = value;
    }

    public void setDocSymbol(String value) {
        this.docSymbol = value;
    }


    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}

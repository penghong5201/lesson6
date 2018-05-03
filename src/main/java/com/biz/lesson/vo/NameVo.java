package com.biz.lesson.vo;

import javax.validation.constraints.NotNull;

import com.biz.lesson.model.base.Name;

/**
 * Created by defei on 3/20/17.
 */
public class NameVo extends PersistentVo {

    @NotNull
    public Name name;

    /**
     * {@linkplain NameVo#name}
     */
    public Name getName() {

        return name;
    }

    /**
     * {@linkplain NameVo#name}
     */
    public void setName(Name name) {

        this.name = name;
    }
}

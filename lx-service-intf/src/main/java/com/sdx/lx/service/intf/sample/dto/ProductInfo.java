/*
 * Copyright (C), 2013-2014, 
 * FileName: ProductInfo.java
 * Author:   zhuliang
 * Date:     2014年10月16日 下午1:30:32
 */
package com.sdx.lx.service.intf.sample.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 实体类礼品   
 * 
 * @author zhuliang
 */
@Entity(name = "PRODUCT_INFO")
public class ProductInfo implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
    /**
     * 类型 类型 0:礼品盒 1:礼品袋 2:用户手册
     */
    private Integer type;
    /**
     * 礼品名称 礼品名称
     */
    private String name;
    /**
     * 尺寸 尺寸
     */
    private String kind;
    /**
     * 数量 数量
     */
    private String num;
    /**
     * 创建时间 创建时间
     */
    private java.util.Date createTime;

    /**
     * Get id
     * 
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return this.id;
    }

    /**
     * Set id
     * 
     * @param id 编号ID
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get type
     * 
     * @return type
     */
    @Column(name = "TYPE")
    public Integer getType() {
        return this.type;
    }

    /**
     * Set type
     * 
     * @param type 类型
     */

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Get name
     * 
     * @return name
     */
    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    /**
     * Set name
     * 
     * @param name 礼品名称
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get kind
     * 
     * @return kind
     */
    @Column(name = "KIND")
    public String getKind() {
        return this.kind;
    }

    /**
     * Set kind
     * 
     * @param kind 尺寸
     */

    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Get num
     * 
     * @return num
     */
    @Column(name = "NUM")
    public String getNum() {
        return this.num;
    }

    /**
     * Set num
     * 
     * @param num 数量
     */

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * Get createTime
     * 
     * @return createTime
     */
    @Column(name = "CREATE_TIME")
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * Set createTime
     * 
     * @param createTime 创建时间
     */

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}

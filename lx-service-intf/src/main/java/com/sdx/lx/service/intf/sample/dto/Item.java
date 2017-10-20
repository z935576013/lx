/*
 * Copyright (C), 2013-2014, 
 * FileName: Item.java
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
 * 实体类产品   
 * 
 * @author zhuliang
 */
@Entity(name = "ITEM")
public class Item implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
    /**
     * 产品名称 产品名称
     */
    private String productName;
    /**
     * 规格 规格
     */
    private String spec;
    /**
     * 数量 数量
     */
    private String num;

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
     * Get productName
     * 
     * @return productName
     */
    @Column(name = "PRODUCT_NAME")
    public String getProductName() {
        return this.productName;
    }

    /**
     * Set productName
     * 
     * @param productName 产品名称
     */

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Get spec
     * 
     * @return spec
     */
    @Column(name = "SPEC")
    public String getSpec() {
        return this.spec;
    }

    /**
     * Set spec
     * 
     * @param spec 规格
     */

    public void setSpec(String spec) {
        this.spec = spec;
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

}

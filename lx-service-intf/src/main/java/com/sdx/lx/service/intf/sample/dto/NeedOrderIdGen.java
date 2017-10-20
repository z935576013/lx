/*
 * Copyright (C), 2013-2014, 
 * FileName: NeedOrderIdGen.java
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
 * 实体类需货单编号生成   
 * 
 * @author zhuliang
 */
@Entity(name = "NEED_ORDER_ID_GEN")
public class NeedOrderIdGen implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
    /**
     * 需货编号 需货编号
     */
    private Integer orderCode;
    /**
     * 日期 日期
     */
    private java.util.Date orderDate;

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
     * Get orderCode
     * 
     * @return orderCode
     */
    @Column(name = "ORDER_CODE")
    public Integer getOrderCode() {
        return this.orderCode;
    }

    /**
     * Set orderCode
     * 
     * @param orderCode 需货编号
     */

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * Get orderDate
     * 
     * @return orderDate
     */
    @Column(name = "ORDER_DATE")
    public java.util.Date getOrderDate() {
        return this.orderDate;
    }

    /**
     * Set orderDate
     * 
     * @param orderDate 日期
     */

    public void setOrderDate(java.util.Date orderDate) {
        this.orderDate = orderDate;
    }

}

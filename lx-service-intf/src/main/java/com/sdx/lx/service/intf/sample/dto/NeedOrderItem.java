/*
 * Copyright (C), 2013-2014, 
 * FileName: NeedOrderItem.java
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
 * 实体类需货单项   
 * 
 * @author zhuliang
 */
@Entity(name = "NEED_ORDER_ITEM")
public class NeedOrderItem implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
    /**
     * 需货单编号 需货单编号
     */
    private Long needOrderId;
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
    private Integer num;
    /**
     * 项备注 项备注
     */
    private String itemNote;
    /**
     * 发货状态  0:未发货  1:已发货
     */
    private Integer deliverStatus;
    /**
     * 已发货数量
     */
    private Integer deliverNum;

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
     * Get needOrderId
     * 
     * @return needOrderId
     */
    @Column(name = "NEED_ORDER_ID")
    public Long getNeedOrderId() {
        return this.needOrderId;
    }

    /**
     * Set needOrderId
     * 
     * @param needOrderId 需货单编号
     */

    public void setNeedOrderId(Long needOrderId) {
        this.needOrderId = needOrderId;
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
    public Integer getNum() {
        return this.num;
    }

    /**
     * Set num
     * 
     * @param num 数量
     */

    public void setNum(Integer num) {
        this.num = num;
    }

	/**
	 * @return the itemNote
	 */
    @Column(name = "ITEM_NOTE")
	public String getItemNote() {
		return itemNote;
	}

	/**
	 * @param itemNote the itemNote to set
	 */
	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	/**
	 * @return the deliverStatus
	 */
	@Column(name = "DELIVER_STATUS")
	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	/**
	 * @param deliverStatus the deliverStatus to set
	 */
	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	/**
	 * @return the deliverNum
	 */
	@Column(name = "DELIVER_NUM")
	public Integer getDeliverNum() {
		return deliverNum;
	}

	/**
	 * @param deliverNum the deliverNum to set
	 */
	public void setDeliverNum(Integer deliverNum) {
		this.deliverNum = deliverNum;
	}


}

/*
 * Copyright (C), 2013-2014, 
 * FileName: NeedOrder.java
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
 * 实体类需货单   
 * 
 * @author zhuliang
 */
@Entity(name = "NEED_ORDER")
public class NeedOrder implements Serializable {
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
    private String needOrderCode;
    /**
     * 需货单位 需货单位
     */
    private String needCompany;
    /**
     * 订单日期 订单日期
     */
    private java.util.Date orderDate;
    /**
     * 需货日期 需货日期
     */
    private java.util.Date needDate;
    /**
     * 单位名称 单位名称
     */
    private String companyName;
    /**
     * 邮编 邮编
     */
    private String zipCode;
    /**
     * 收货地址 收货地址
     */
    private String receiveAddress;
    /**
     * 收货人 收货人
     */
    private String receiveName;
    /**
     * 收货人电话 收货人电话
     */
    private String receiveMobile;
    /**
     * 备注 备注
     */
    private String note;
    /**
     * 创建时间 创建时间
     */
    private java.util.Date createTime;
    /**
     * 发货状态  0:未发货  1:已发货
     */
    private Integer deliverStatus;
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
     * Get needOrderCode
     * 
     * @return needOrderCode
     */
    @Column(name = "NEED_ORDER_CODE")
    public String getNeedOrderCode() {
        return this.needOrderCode;
    }

    /**
     * Set needOrderCode
     * 
     * @param needOrderCode 需货编号
     */

    public void setNeedOrderCode(String needOrderCode) {
        this.needOrderCode = needOrderCode;
    }

    /**
     * Get needCompany
     * 
     * @return needCompany
     */
    @Column(name = "NEED_COMPANY")
    public String getNeedCompany() {
        return this.needCompany;
    }

    /**
     * Set needCompany
     * 
     * @param needCompany 需货单位
     */

    public void setNeedCompany(String needCompany) {
        this.needCompany = needCompany;
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
     * @param orderDate 订单日期
     */

    public void setOrderDate(java.util.Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Get needDate
     * 
     * @return needDate
     */
    @Column(name = "NEED_DATE")
    public java.util.Date getNeedDate() {
        return this.needDate;
    }

    /**
     * Set needDate
     * 
     * @param needDate 需货日期
     */

    public void setNeedDate(java.util.Date needDate) {
        this.needDate = needDate;
    }

    /**
     * Get companyName
     * 
     * @return companyName
     */
    @Column(name = "COMPANY_NAME")
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * Set companyName
     * 
     * @param companyName 单位名称
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Get zipCode
     * 
     * @return zipCode
     */
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Set zipCode
     * 
     * @param zipCode 邮编
     */

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get receiveAddress
     * 
     * @return receiveAddress
     */
    @Column(name = "RECEIVE_ADDRESS")
    public String getReceiveAddress() {
        return this.receiveAddress;
    }

    /**
     * Set receiveAddress
     * 
     * @param receiveAddress 收货地址
     */

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    /**
     * Get receiveName
     * 
     * @return receiveName
     */
    @Column(name = "RECEIVE_NAME")
    public String getReceiveName() {
        return this.receiveName;
    }

    /**
     * Set receiveName
     * 
     * @param receiveName 收货人
     */

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    /**
     * Get receiveMobile
     * 
     * @return receiveMobile
     */
    @Column(name = "RECEIVE_MOBILE")
    public String getReceiveMobile() {
        return this.receiveMobile;
    }

    /**
     * Set receiveMobile
     * 
     * @param receiveMobile 收货人电话
     */

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    /**
     * Get note
     * 
     * @return note
     */
    @Column(name = "NOTE")
    public String getNote() {
        return this.note;
    }

    /**
     * Set note
     * 
     * @param note 备注
     */

    public void setNote(String note) {
        this.note = note;
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
}

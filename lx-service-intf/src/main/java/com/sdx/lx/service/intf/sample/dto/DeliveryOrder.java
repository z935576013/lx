/*
 * Copyright (C), 2013-2014, 
 * FileName: DeliveryOrder.java
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
 * 实体类发货单   
 * 
 * @author zhuliang
 */
@Entity(name = "DELIVERY_ORDER")
public class DeliveryOrder implements Serializable {
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
     * 需货单项编号 需货单项编号
     */
    private Long needOrderItemId;
    /**
     * 产品名称 产品名称
     */
    private String productName;
    /**
     * 需货编号 需货编号
     */
    private String needOrderCode;
    /**
     * 需货单位 需货单位
     */
    private String needCompany;
    /**
     * 发往地 发往地
     */
    private String deliverCity;
    /**
     * 规格 规格
     */
    private String spec;
    /**
     * 数量 数量
     */
    private String num;
    /**
     * 物流编号 物流编号
     */
    private String logisticsCode;
    /**
     * 发货日期 发货日期
     */
    private java.util.Date deliverDate;
    /**
     * 批号 批号
     */
    private String batchNum;
    /**
     * 编码 编码
     */
    private String productCode;
    /**
     * 备注 备注
     */
    private String note;
    /**
     * 短信通知 短信通知
     */
    private Integer smsNeed;
    /**
     * 短信通知人 短信通知人
     */
    private String smsName;
    /**
     * 短信通知号码 短信通知号码
     */
    private String smsMobile;
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
	 * @return the needOrderItemId
	 */
    @Column(name = "NEED_ORDER_ITEM_ID")
	public Long getNeedOrderItemId() {
		return needOrderItemId;
	}

	/**
	 * @param needOrderItemId the needOrderItemId to set
	 */
	public void setNeedOrderItemId(Long needOrderItemId) {
		this.needOrderItemId = needOrderItemId;
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
     * Get deliverCity
     * 
     * @return deliverCity
     */
    @Column(name = "DELIVER_CITY")
    public String getDeliverCity() {
        return this.deliverCity;
    }

    /**
     * Set deliverCity
     * 
     * @param deliverCity 发往地
     */

    public void setDeliverCity(String deliverCity) {
        this.deliverCity = deliverCity;
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

    /**
     * Get logisticsCode
     * 
     * @return logisticsCode
     */
    @Column(name = "LOGISTICS_CODE")
    public String getLogisticsCode() {
        return this.logisticsCode;
    }

    /**
     * Set logisticsCode
     * 
     * @param logisticsCode 物流编号
     */

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    /**
     * Get deliverDate
     * 
     * @return deliverDate
     */
    @Column(name = "DELIVER_DATE")
    public java.util.Date getDeliverDate() {
        return this.deliverDate;
    }

    /**
     * Set deliverDate
     * 
     * @param deliverDate 发货日期
     */

    public void setDeliverDate(java.util.Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    /**
     * Get batchNum
     * 
     * @return batchNum
     */
    @Column(name = "BATCH_NUM")
    public String getBatchNum() {
        return this.batchNum;
    }

    /**
     * Set batchNum
     * 
     * @param batchNum 批号
     */

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * Get productCode
     * 
     * @return productCode
     */
    @Column(name = "PRODUCT_CODE")
    public String getProductCode() {
        return this.productCode;
    }

    /**
     * Set productCode
     * 
     * @param productCode 编码
     */

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
     * Get smsNeed
     * 
     * @return smsNeed
     */
    @Column(name = "SMS_NEED")
    public Integer getSmsNeed() {
        return this.smsNeed;
    }

    /**
     * Set smsNeed
     * 
     * @param smsNeed 短信通知
     */

    public void setSmsNeed(Integer smsNeed) {
        this.smsNeed = smsNeed;
    }

    /**
     * Get smsName
     * 
     * @return smsName
     */
    @Column(name = "SMS_NAME")
    public String getSmsName() {
        return this.smsName;
    }

    /**
     * Set smsName
     * 
     * @param smsName 短信通知人
     */

    public void setSmsName(String smsName) {
        this.smsName = smsName;
    }

    /**
     * Get smsMobile
     * 
     * @return smsMobile
     */
    @Column(name = "SMS_MOBILE")
    public String getSmsMobile() {
        return this.smsMobile;
    }

    /**
     * Set smsMobile
     * 
     * @param smsMobile 短信通知号码
     */

    public void setSmsMobile(String smsMobile) {
        this.smsMobile = smsMobile;
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

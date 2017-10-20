/*
 * Copyright (C), 2013-2014, 
 * FileName: Contact.java
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
 * 实体类联系人   
 * 
 * @author zhuliang
 */
@Entity(name = "CONTACT")
public class Contact implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
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

}

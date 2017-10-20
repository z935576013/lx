/*
 * Copyright (C), 2013-2014, 
 * FileName: UserInfo.java
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
 * 实体类用户   
 * 
 * @author zhuliang
 */
@Entity(name = "USER_INFO")
public class UserInfo implements Serializable {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号ID 编号ID
     */
    private Long id;
    /**
     * 账号 账号
     */
    private String userName;
    /**
     * 密码 密码
     */
    private String userPassword;
    /**
     * 所属单位 所属单位
     */
    private String company;
    /**
     * 类型 类型 0:管理员 1:普通用户 2:仓库管理员
     */
    private Integer userType;

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
     * Get userName
     * 
     * @return userName
     */
    @Column(name = "USER_NAME")
    public String getUserName() {
        return this.userName;
    }

    /**
     * Set userName
     * 
     * @param userName 账号
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get userPassword
     * 
     * @return userPassword
     */
    @Column(name = "USER_PASSWORD")
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
     * Set userPassword
     * 
     * @param userPassword 密码
     */

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Get company
     * 
     * @return company
     */
    @Column(name = "COMPANY")
    public String getCompany() {
        return this.company;
    }

    /**
     * Set company
     * 
     * @param company 所属单位
     */

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Get userType
     * 
     * @return userType
     */
    @Column(name = "USER_TYPE")
    public Integer getUserType() {
        return this.userType;
    }

    /**
     * Set userType
     * 
     * @param userType 类型
     */

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}

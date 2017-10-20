/*
 * Copyright (C), 2013-2014, 
 * FileName: Sample.java
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
 * sample
 * 
 * @author zhuliang
 */
@Entity(name = "SAMPLE")
public class Sample implements Serializable {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * ID
	 */
	private String name;

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
	 * @param id
	 *            ID
	 */

	public void setId(Long id) {
		this.id = id;
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
	 * @param name
	 *            姓名
	 */

	public void setName(String name) {
		this.name = name;
	}

}

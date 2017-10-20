package com.sdx.lx.service.intf.sample;

import java.util.List;

import com.sdx.lx.service.intf.sample.dto.UserInfo;

public interface UserService {

	/**
	 * 列表
	 */
	List<UserInfo> getUserList();

	/**
	 */
	UserInfo getUser(String userName, String password);

	UserInfo get(Long id);

	/**
	 * 保存
	 */
	Long saveUser(UserInfo userInfo);

	/**
	 * 删除
	 */
	void deleteUser(Long id);

	UserInfo getUserByName(String userName);

}

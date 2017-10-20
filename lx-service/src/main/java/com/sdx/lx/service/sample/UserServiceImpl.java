package com.sdx.lx.service.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.UserService;
import com.sdx.lx.service.intf.sample.dto.UserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	DalClient dalClient;

	@Override
	public List<UserInfo> getUserList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return dalClient.queryForList("USER_INFO.SELECT_BY_FIELDS", map, UserInfo.class);
	}

	@Override
	public UserInfo getUser(String userName, String userPassword) {
		if (StringUtils.isEmpty(userPassword) || StringUtils.isEmpty(userName)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userPassword", userPassword);
		List<UserInfo> userList = dalClient.queryForList("USER_INFO.SELECT_BY_FIELDS", map, UserInfo.class);
		if (userList == null || userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	@Override
	public UserInfo get(Long id) {
		if (id == null) {
			return null;
		}
		UserInfo user = new UserInfo();
		user.setId(id);
		user = dalClient.find(UserInfo.class, user);
		if (user == null) {
			return null;
		} else {
			user.setId(id);
			return user;
		}
	}

	@Override
	public Long saveUser(UserInfo userInfo) {
		Long id = null;
		if (userInfo != null) {
			id = userInfo.getId();
			if (id != null) {
				UserInfo old = get(id);
				BeanCopyUtils.copyProperties(userInfo, old);
				dalClient.merge(old);
			} else {
				id = dalClient.persist(userInfo).longValue();
			}
		}
		return id;
	}

	@Override
	public void deleteUser(Long id) {
		UserInfo user = get(id);
		if (user != null) {
			dalClient.remove(user);
		}

	}

	@Override
	public UserInfo getUserByName(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		List<UserInfo> userList = dalClient.queryForList("USER_INFO.SELECT_BY_FIELDS", map, UserInfo.class);
		if (userList == null || userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}

}

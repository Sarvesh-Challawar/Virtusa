package com.mvc.dao;

import java.util.List;

import com.mvc.bean.UserBean;

public interface ViewCurrentUserDAO {

	List<UserBean> get(String uname);
	
}

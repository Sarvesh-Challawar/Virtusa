package com.mvc.dao;

import java.util.List;

import com.mvc.bean.ShopProductBean;

public interface ShopViewGetByNameDAO {

	List<ShopProductBean> get(String name);
	
}

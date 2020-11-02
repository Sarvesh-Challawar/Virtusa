package com.mvc.dao;

import java.util.List;

import com.mvc.bean.ShopProductBean;

public interface ShopViewGetByLocationDAO {

	List<ShopProductBean> get(String location);
	
}

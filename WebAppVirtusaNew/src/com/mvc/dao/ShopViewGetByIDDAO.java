/**
 * 
 */
package com.mvc.dao;

import java.util.List;

import com.mvc.bean.ShopProductBean;

/**
 * @author sarvesh
 *
 */
public interface ShopViewGetByIDDAO {

	List<ShopProductBean> get(int id);
	
}

package com.qxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qxt.dao.FoodMapper;
import com.qxt.pojo.Food;

@Service
public class FoodService {

	@Autowired
	FoodMapper FoodDao;

	public PageInfo<Food> selectAllFood(Food bean) {
//		System.out.println(FoodDao.selectAll(bean));
		return new PageInfo<Food>(FoodDao.selectAll(bean));
	}
	

	public Food getFoodById(Long id) {
		return FoodDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int insertFood(Food bean) {
		return FoodDao.insert(bean);
	}
	
	@Transactional
	public int updateFood(Food bean) {
		return FoodDao.updateByPrimaryKey(bean);
	}
	
	@Transactional
	public Boolean deleteFood(Long id) {
		if(id == null) {
			return false;
		}else {
			FoodDao.deleteByPrimaryKey(id);
			return true;
		}
	}
	
	
	/**
	 * 验证 Food类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
	public boolean isVaildFood(Food bean,StringBuffer errMsg) {
		if(bean == null) {
			errMsg.append("该Food信息构建异常!");return false;
		}
		if(bean.getName() == null || bean.getName().isEmpty()) {
			errMsg.append("商品名不能为空!");return false;
		}
		if(bean.getYuanPrice()== null) {
			errMsg.append("原价不能为空!");return false;
		}
		return true;
	}
	

}

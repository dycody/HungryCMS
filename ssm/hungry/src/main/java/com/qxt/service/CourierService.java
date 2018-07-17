package com.qxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.Constants;
import com.qxt.dao.AccountMapper;
import com.qxt.dao.CourierMapper;
import com.qxt.pojo.Courier;

@Service
public class CourierService {

	@Autowired
	CourierMapper CourierDao;
	@Autowired
	AccountMapper accountDao;
	public PageInfo<Courier> selectAllCourier(int pageNum, Courier bean) {
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		return new PageInfo<Courier>(CourierDao.selectAll(bean));
	}
	

	public Courier getCourierById(Long id) {
		return CourierDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int insertCourier(Courier bean) {
		return CourierDao.insert(bean);
	}
	
	@Transactional
	public int updateCourier(Courier bean) {
		return CourierDao.updateByPrimaryKey(bean);
	}
	
	@Transactional
	public Boolean deleteCourier(Long id) {
		if(id == null) {
			return false;
		}else {
			accountDao.deleteByPrimaryKey(id);
			return true;
		}
	}
	
	
	/**
	 * 验证 Courier类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
	public boolean isVaildCourier(Courier bean,StringBuffer errMsg) {
		if(bean == null) {
			errMsg.append("该Courier信息构建异常!");return false;
		}
		if(bean.getName() == null || bean.getName().isEmpty()) {
			errMsg.append("昵称不能为空!");return false;
		}else if(bean.getName().length() < 2 || bean.getName().length() >20){
			errMsg.append("昵称长度为2-20字符!");return false;
		}

		if(bean.getrId()== null) {
			errMsg.append("所属区不能为空!");return false;
		}
		if(bean.getTel() != null && !bean.getTel().isEmpty()) {
			if(!bean.getTel().matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")) {
				errMsg.append("手机号不正确!");return false;
			}
		}
		return true;
	}
	

}

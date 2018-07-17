package com.qxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.Constants;
import com.qxt.dao.AccountMapper;
import com.qxt.dao.CourierMapper;
import com.qxt.dao.MergantMapper;
import com.qxt.dao.OrderInfMapper;
import com.qxt.dao.OrderuMapper;
import com.qxt.pojo.Courier;
import com.qxt.pojo.OrderInf;
import com.qxt.pojo.Orderu;
import com.qxt.pojo.User;

@Service
public class OrderuService {

	@Autowired
	OrderuMapper orderuDao;
	@Autowired
	OrderInfMapper orderInfDao;
	@Autowired
	AccountMapper accountDao;
	@Autowired
	CourierMapper courierDao;
	@Autowired
	MergantMapper mergantDao;
	
	public PageInfo<Orderu> selectAllOrderu(int pageNum, Orderu bean) {
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		return new PageInfo<Orderu>(orderuDao.selectAll(bean));
	}
	
	@Transactional
	public Long saveOrder(Orderu bean,List<OrderInf> orderInfList) {
		bean.setCourierId(this.getCourierByMergantId(bean.getMergantId()));
		orderuDao.insert(bean);
		Long oId = bean.getId();
		System.out.println(bean.getId());
		for (OrderInf orderInf : orderInfList) {
			orderInf.setOrderId(bean.getId());
			orderInfDao.insert(orderInf);
		}
		return oId;
	}
	
	public Orderu getOrderuById(Long orderId) {
		return orderuDao.selectByPrimaryKey(orderId);
	}
	
	public List<OrderInf> getOrderInfByoId(Long orderId) {
		return orderInfDao.selectByOrderId(orderId);
	}
	
	@Transactional
	public Boolean confirmOrderu(Long id) {
		if(id == null) {
			return false;
		}else {
			orderuDao.reachByPrimaryKey(id);
			return true;
		}
	}
	
	@Transactional
	public Boolean deleteOrderu(Long id) {
		if(id == null) {
			return false;
		}else {
			orderuDao.deleteorderByPrimaryKey(id);
			return true;
		}
	}
	
	public Long getCourierByMergantId(Long mId) {
		int rId = mergantDao.selectByPrimaryKey(mId).getrId();
		Courier courier = courierDao.selectBestByRegionId(rId);
		return courier.getuId();
	}
	
	
	/**
	 * 验证 User类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
	public boolean isVaildUser(User bean,StringBuffer errMsg) {
		if(bean == null) {
			errMsg.append("该User信息构建异常!");return false;
		}
		if(bean.getName() == null || bean.getName().isEmpty()) {
			errMsg.append("昵称不能为空!");return false;
		}else if(bean.getName().length() < 2 || bean.getName().length() >20){
			errMsg.append("昵称长度为2-20字符!");return false;
		}

		if(bean.getLocation()== null) {
			errMsg.append("地址不能为空!");return false;
		}
		if(bean.getTel() != null && !bean.getTel().isEmpty()) {
			if(!bean.getTel().matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")) {
				errMsg.append("手机号不正确!");return false;
			}
		}
		return true;
	}
	

}

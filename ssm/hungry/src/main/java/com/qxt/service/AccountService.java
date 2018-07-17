package com.qxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qxt.dao.AccountMapper;
import com.qxt.dao.CourierMapper;
import com.qxt.dao.MergantMapper;
import com.qxt.dao.UserMapper;
import com.qxt.pojo.Account;
import com.qxt.pojo.Courier;
import com.qxt.pojo.Mergant;
import com.qxt.pojo.User;

@Service
public class AccountService {

	@Autowired
	private AccountMapper accountDao;
	@Autowired
	private MergantMapper mergantDao;
	@Autowired
	private CourierMapper courierDao;
	@Autowired
	private UserMapper userDao;
	
	@Transactional
	public int saveAccount(Account bean) {
		if(bean.getId() != null) {
			return accountDao.updatePwByPrimaryKey(bean);
		}else {
			return accountDao.insert(bean);
		}
	}
	
	public Account loginAdmin(Account bean) {
		if(bean == null) {
			return null;
		}
		bean.setuType((byte)0);
		return accountDao.selectByUnAndPwAndType(bean);
	}
	
	public Mergant loginMergant(Account bean) {
		if(bean == null) {return null;}
		bean.setuType((byte)1);
		Account record = accountDao.selectByUnAndPwAndType(bean);
		if(record == null) {
			return null;
		}else {
			return mergantDao.selectByPrimaryKey(record.getId());
		}
	}
	
	public Courier loginCourier(Account bean) {
		if(bean == null) {return null;}
		bean.setuType((byte)2);
		Account record = accountDao.selectByUnAndPwAndType(bean);
		if(record == null) {
			return null;
		}else {
			return courierDao.selectByPrimaryKey(record.getId());
		}
	}
	
	public User loginUser(Account bean) {
		if(bean == null) {return null;}
		bean.setuType((byte)3);
		Account record = accountDao.selectByUnAndPwAndType(bean);
		if(record == null) {
			return null;
		}else {
			return userDao.selectByPrimaryKey(record.getId());
		}
	}
	
	/**
	 * 验证 Account类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
	public boolean isVaildAccount(Account bean,StringBuffer errMsg) {
		if(bean == null) {
			errMsg.append("该Account信息构建异常!");return false;
		}
		if(bean.getUsername() == null || bean.getUsername().isEmpty()) {
			errMsg.append("账号名不能为空!");return false;
		}else if(bean.getUsername().length() < 6 || bean.getUsername().length() >20){
			errMsg.append("账号名长度为6-20字符!");return false;
		}else if(!bean.getUsername().matches("^[a-zA-Z0-9]{6,20}$")) {
			errMsg.append("账户名只能为字符或数字!");return false;
		}else if(bean.getId() == null && accountDao.selectByUserName(bean.getUsername())!=null){
			errMsg.append("该账户已被注册！");return false;
		}
		
		if(bean.getId() == null && (bean.getPassword() == null || bean.getPassword().isEmpty())) {
			errMsg.append("密码不能为空!");return false;
		}
		
		if(bean.getPassword() != null && bean.getPassword().length() < 6) {
			errMsg.append("密码需要大于6位!");return false;
		}
		return true;
	}
}

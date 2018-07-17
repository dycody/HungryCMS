package com.qxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.Constants;
import com.qxt.dao.AccountMapper;
import com.qxt.dao.MergantMapper;
import com.qxt.pojo.Mergant;

@Service
public class MergantService {

	@Autowired
	MergantMapper MergantDao;
	@Autowired
	AccountMapper accountDao;
	public PageInfo<Mergant> selectAllMergant(int pageNum, Mergant bean) {
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
//		System.out.println(MergantDao.selectAll(bean));
		return new PageInfo<Mergant>(MergantDao.selectAll(bean));
	}
	

	public Mergant getMergantById(Long id) {
		return MergantDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int insertMergant(Mergant bean) {
		System.out.println(bean);
		return MergantDao.insert(bean);
	}
	
	@Transactional
	public int updateMergant(Mergant bean) {
		return MergantDao.updateByPrimaryKey(bean);
	}
	
	@Transactional
	public Boolean deleteMergant(Long id) {
		if(id == null) {
			return false;
		}else {
			accountDao.deleteByPrimaryKey(id);
			return true;
		}
	}
	
	
	/**
	 * 验证 Mergant类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
	public boolean isVaildMergant(Mergant bean,StringBuffer errMsg) {
		if(bean == null) {
			errMsg.append("该Mergant信息构建异常!");return false;
		}
		if(bean.getmName() == null || bean.getmName().isEmpty()) {
			errMsg.append("昵称不能为空!");return false;
		}else if(bean.getmName().length() < 2 || bean.getmName().length() >20){
			errMsg.append("昵称长度为2-20字符!");return false;
		}

		if(bean.getLocation()== null) {
			errMsg.append("地址不能为空!");return false;
		}
		if(bean.getmTel() != null && !bean.getmTel().isEmpty()) {
			if(!bean.getmTel().matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")) {
				errMsg.append("手机号不正确!");return false;
			}
		}
		return true;
	}
	

}

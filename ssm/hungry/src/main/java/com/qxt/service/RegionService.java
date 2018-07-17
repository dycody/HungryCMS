package com.qxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qxt.dao.AccountMapper;
import com.qxt.dao.RegionMapper;
import com.qxt.pojo.Region;

@Service
public class RegionService {

	@Autowired
	RegionMapper RegionDao;
	@Autowired
	AccountMapper accountDao;
	public PageInfo<Region> selectAllRegion() {
		return new PageInfo<Region>(RegionDao.selectAll());
	}
	
	@Transactional
	public int insertRegion(Region bean) {
		return RegionDao.insert(bean);
	}
	
	@Transactional
	public int updateRegion(Region bean) {
		return RegionDao.updateByPrimaryKey(bean);
	}
	
	@Transactional
	public Boolean deleteRegion(Long id) {
		if(id == null) {
			return false;
		}else {
			accountDao.deleteByPrimaryKey(id);
			return true;
		}
	}
	
	
	/**
	 * 验证 Region类型数据合法
	 * @param bean
	 * @param errMsg
	 * @return
	 */
//	public boolean isVaildRegion(Region bean,StringBuffer errMsg) {
//		if(bean == null) {
//			errMsg.append("该Region信息构建异常!");return false;
//		}
//		if(bean.getName() == null || bean.getName().isEmpty()) {
//			errMsg.append("昵称不能为空!");return false;
//		}else if(bean.getName().length() < 2 || bean.getName().length() >20){
//			errMsg.append("昵称长度为2-20字符!");return false;
//		}
//
//		if(bean.getLocation()== null) {
//			errMsg.append("地址不能为空!");return false;
//		}
//		if(bean.getTel() != null && !bean.getTel().isEmpty()) {
//			if(!bean.getTel().matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")) {
//				errMsg.append("手机号不正确!");return false;
//			}
//		}
//		return true;
//	}
	

}

package com.qxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.pojo.Account;
import com.qxt.pojo.Courier;
import com.qxt.service.AccountService;
import com.qxt.service.CourierService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/courier")
public class CourierController {
	
	@ResponseBody
	@RequestMapping(value="/o_getAllCourier",method=RequestMethod.POST)
	public JsonResponse getAllCourier(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		Integer pageNum = 1;
		Courier bean = new Courier();
		try {
			if(requestBody.get("pageNum") != null && !requestBody.get("pageNum").isEmpty()) {
				pageNum = Integer.parseInt(requestBody.get("pageNum"));
			}
			if(requestBody.get("name") != null && !requestBody.get("name").isEmpty()) {
				bean.setName(requestBody.get("name"));
			}
			if(requestBody.get("rId") != null && !requestBody.get("rId").isEmpty()) {
				bean.setrId(Integer.valueOf(requestBody.get("rId")));
			}
			if(requestBody.get("account") != null && !requestBody.get("account").isEmpty()) {
				Account account = new Account();
				account.setUsername(requestBody.get("account"));
				bean.setAccount(account);
			}
			data.setData(CourierService.selectAllCourier(pageNum,bean));
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(value="/o_saveCourier",method=RequestMethod.POST)
	public JsonResponse addCourier(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		Courier Courier = new Courier();
		Account account = new Account();
		StringBuffer errMsg = new StringBuffer();
		try {
			if(requestBody.get("uId") != null && !requestBody.get("uId").isEmpty()) {
				account.setId(Long.parseLong(requestBody.get("uId")));
				Courier.setuId(Long.parseLong(requestBody.get("uId")));
			}
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			account.setuType((byte)2);
			Courier.setName(requestBody.get("name"));
			Courier.setrId(Integer.valueOf(requestBody.get("rId")));
			Courier.setTel(requestBody.get("tel"));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		if(!accountService.isVaildAccount(account, errMsg) || !CourierService.isVaildCourier(Courier, errMsg)) {
			data.setErrMsg(errMsg.toString());
			return data;
		}else {
			if(account.getPassword() != null && !account.getPassword().isEmpty()) {
				accountService.saveAccount(account);
			}
			if(account.getId() == null) {
				data.setErrMsg("添加中断!");
				return data;
			}else {
				Long uId = account.getId();
				Courier.setuId(uId);
				if(requestBody.get("uId") == null || requestBody.get("uId").isEmpty()) {
					CourierService.insertCourier(Courier);
				}else {
					CourierService.updateCourier(Courier);
				}
			}
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_deleteCourier",method=RequestMethod.POST)
	public JsonResponse deleteCourier(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long uId = Long.parseLong(requestBody.get("id"));			
			if(!CourierService.deleteCourier(uId)) {
				data.setErrMsg("该用户不存在!");
				return data;
			}
		} catch(Exception e) {
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_getCourierById",method=RequestMethod.POST)
	public JsonResponse getCourierById(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long id = Long.parseLong(requestBody.get("id"));
			data.setData(CourierService.getCourierById(id));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Autowired
	CourierService CourierService;
	@Autowired
	AccountService accountService;

}

package com.qxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.pojo.Account;
import com.qxt.pojo.Courier;
import com.qxt.pojo.Mergant;
import com.qxt.pojo.User;
import com.qxt.service.AccountService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@ResponseBody
	@RequestMapping(value="/o_admin",method=RequestMethod.POST)
	public JsonResponse loginAdmin(@RequestBody Map<String,String> requestBody) {
		System.out.println(requestBody);
		JsonResponse data = new JsonResponse();
		try {
			Account account = new Account();
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			account = accountService.loginAdmin(account);
			if(account == null) {
				data.setErrMsg("用户名或密码错误,登录失败!");
				return data;
			}else {
				data.setData(account);
			}
		}catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_mergant",method=RequestMethod.POST)
	public JsonResponse loginMergant(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Account account = new Account();
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			Mergant mergantInf = accountService.loginMergant(account);
			if(mergantInf == null) {
				data.setErrMsg("用户名或密码错误,登录失败!");
				return data;
			}else {
				data.setData(mergantInf);
			}
		}catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_courier",method=RequestMethod.POST)
	public JsonResponse loginCourier(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Account account = new Account();
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			Courier courierInf = accountService.loginCourier(account);
			if(courierInf == null) {
				data.setErrMsg("用户名或密码错误,登录失败!");
				return data;
			}else {
				data.setData(courierInf);
			}
		}catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_user",method=RequestMethod.POST)
	public JsonResponse loginUser(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Account account = new Account();
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			User userInf = accountService.loginUser(account);
			if(userInf == null) {
				data.setErrMsg("用户名或密码错误,登录失败!");
				return data;
			}else {
				data.setData(userInf);
			}
		}catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Autowired
	private AccountService accountService;
}

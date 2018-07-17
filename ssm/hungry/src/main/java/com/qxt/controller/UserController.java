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
import com.qxt.pojo.User;
import com.qxt.service.AccountService;
import com.qxt.service.UserService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@ResponseBody
	@RequestMapping(value="/o_getAllUser",method=RequestMethod.POST)
	public JsonResponse getAllUser(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		Integer pageNum = 1;
		User bean = new User();
		try {
			if(requestBody.get("pageNum") != null && !requestBody.get("pageNum").isEmpty()) {
				pageNum = Integer.parseInt(requestBody.get("pageNum"));
			}
			if(requestBody.get("name") != null && !requestBody.get("name").isEmpty()) {
				bean.setName(requestBody.get("name"));
			}
			if(requestBody.get("account") != null && !requestBody.get("account").isEmpty()) {
				Account account = new Account();
				account.setUsername(requestBody.get("account"));
				bean.setAccount(account);
			}
			data.setData(userService.selectAllUser(pageNum,bean));
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
	@RequestMapping(value="/o_saveUser",method=RequestMethod.POST)
	public JsonResponse addUser(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		User User = new User();
		Account account = new Account();
		StringBuffer errMsg = new StringBuffer();
		try {
			if(requestBody.get("uId") != null && !requestBody.get("uId").isEmpty()) {
				account.setId(Long.parseLong(requestBody.get("uId")));
				User.setuId(Long.parseLong(requestBody.get("uId")));
			}
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			account.setuType((byte)3);
			User.setName(requestBody.get("name"));
			User.setLocation(requestBody.get("location"));
			User.setTel(requestBody.get("tel"));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		if(!accountService.isVaildAccount(account, errMsg) || !userService.isVaildUser(User, errMsg)) {
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
				System.out.println(User);
				Long uId = account.getId();
				User.setuId(uId);
				if(requestBody.get("uId") == null || requestBody.get("uId").isEmpty()) {
					userService.insertUser(User);
				}else {
					userService.updateUser(User);
				}
			}
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_deleteUser",method=RequestMethod.POST)
	public JsonResponse deleteUser(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long uId = Long.parseLong(requestBody.get("id"));			
			if(!userService.deleteUser(uId)) {
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
	@RequestMapping(value="/o_getUserById",method=RequestMethod.POST)
	public JsonResponse getUserById(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long id = Long.parseLong(requestBody.get("id"));
			data.setData(userService.getUserById(id));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;

}

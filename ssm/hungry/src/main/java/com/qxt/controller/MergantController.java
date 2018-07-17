package com.qxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.Constants;
import com.qxt.pojo.Account;
import com.qxt.pojo.Mergant;
import com.qxt.service.AccountService;
import com.qxt.service.MergantService;
import com.qxt.utils.DateUtil;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/mergant")
public class MergantController {
	
	@ResponseBody
	@RequestMapping(value="/o_getAllMergant",method=RequestMethod.POST)
	public JsonResponse getAllMergant(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		Integer pageNum = 1;
		Mergant bean = new Mergant();
		try {
			if(requestBody.get("pageNum") != null && !requestBody.get("pageNum").isEmpty()) {
				pageNum = Integer.parseInt(requestBody.get("pageNum"));
			}
			if(requestBody.get("mName") != null && !requestBody.get("mName").isEmpty()) {
				bean.setmName(requestBody.get("mName"));
			}
			if(requestBody.get("type") != null && !requestBody.get("type").isEmpty()) {
				bean.setType(requestBody.get("type"));
			}
			data.setData(MergantService.selectAllMergant(pageNum,bean));
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="/o_saveMergant",method=RequestMethod.POST)
	public JsonResponse addMergant(@RequestBody Map<String,String> requestBody) {
		System.out.println(requestBody);
		JsonResponse data = new JsonResponse();
		Mergant mergant = new Mergant();
		Account account = new Account();
		StringBuffer errMsg = new StringBuffer();
		try {
			if(requestBody.get("uId") != null && !requestBody.get("uId").isEmpty()) {
				account.setId(Long.parseLong(requestBody.get("uId")));
				mergant.setuId(Long.parseLong(requestBody.get("uId")));
			}
			account.setUsername(requestBody.get("username"));
			account.setPassword(requestBody.get("password"));
			account.setuType((byte)1);
			mergant.setmName(requestBody.get("mName"));
			mergant.setBossName(requestBody.get("bossName"));
			mergant.setBossTel(requestBody.get("bossTel"));
			mergant.setLocation(requestBody.get("location"));
			mergant.setType(requestBody.get("type"));
			if(requestBody.containsKey("minPrice") && requestBody.get("minPrice")!=null)
				mergant.setMinPrice(Double.valueOf(requestBody.get("minPrice")));
			if(requestBody.containsKey("addPrice") && requestBody.get("addPrice")!=null)
				mergant.setAddPrice(Double.valueOf(requestBody.get("addPrice")));
			mergant.setOpentime(DateUtil.hhmmToDate(requestBody.get("opentime")));
			mergant.setClosetime(DateUtil.hhmmToDate(requestBody.get("closetime")));
			mergant.setrId(Integer.valueOf(requestBody.get("rId")));
			mergant.setmTel(requestBody.get("mTel"));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		if(!accountService.isVaildAccount(account, errMsg) || !MergantService.isVaildMergant(mergant, errMsg)) {
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
				mergant.setuId(uId);
				if(requestBody.get("uId") == null || requestBody.get("uId").isEmpty()) {
					MergantService.insertMergant(mergant);
				}else {
					MergantService.updateMergant(mergant);
				}
			}
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_deleteMergant",method=RequestMethod.POST)
	public JsonResponse deleteMergant(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long uId = Long.parseLong(requestBody.get("id"));			
			if(!MergantService.deleteMergant(uId)) {
				data.setErrMsg("该商家不存在!");
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
	@RequestMapping(value="/o_getMergantType",method=RequestMethod.POST)
	public JsonResponse getMergantType(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		data.setData(Constants.MERGANTY_TYPE);
		data.setSuccess(true);
		return data;
	}
	
	 
	
	@ResponseBody
	@RequestMapping(value="/o_getMergantById",method=RequestMethod.POST)
	public JsonResponse getMergantById(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long id = Long.valueOf(requestBody.get("id"));
			data.setData(MergantService.getMergantById(id));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Autowired
	MergantService MergantService;
	@Autowired
	AccountService accountService;

}

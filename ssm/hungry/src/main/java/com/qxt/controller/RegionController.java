package com.qxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.service.RegionService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/region")
public class RegionController {
	
	@ResponseBody
	@RequestMapping(value="/o_getAllRegion",method=RequestMethod.POST)
	public JsonResponse getAllRegion(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			data.setData(RegionService.selectAllRegion());
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询异常!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
//	@ResponseBody
//	@Transactional
//	@RequestMapping(value="/o_saveRegion",method=RequestMethod.POST)
//	public JsonResponse addRegion(@RequestBody Map<String,String> requestBody) {
//		JsonResponse data = new JsonResponse();
//		Region Region = new Region();
//		Account account = new Account();
//		StringBuffer errMsg = new StringBuffer();
//		try {
//			if(requestBody.get("uId") != null && !requestBody.get("uId").isEmpty()) {
//				account.setId(Long.parseLong(requestBody.get("uId")));
//				Region.setuId(Long.parseLong(requestBody.get("uId")));
//			}
//			account.setRegionname(requestBody.get("Regionname"));
//			account.setPassword(requestBody.get("password"));
//			account.setuType((byte)1);
//			Region.setName(requestBody.get("name"));
//			Region.setLocation(requestBody.get("location"));
//			Region.setTel(requestBody.get("tel"));
//		} catch(Exception e) {
//			e.printStackTrace();
//			data.setErrMsg("数据异常请重新提交!");
//			return data;
//		}
//		if(!accountService.isVaildAccount(account, errMsg) || !RegionService.isVaildRegion(Region, errMsg)) {
//			data.setErrMsg(errMsg.toString());
//			return data;
//		}else {
//			if(account.getPassword() != null && !account.getPassword().isEmpty()) {
//				accountService.saveAccount(account);
//			}
//			if(account.getId() == null) {
//				data.setErrMsg("添加中断!");
//				return data;
//			}else {
//				Long uId = account.getId();
//				Region.setuId(uId);
//				if(requestBody.get("uId") == null || requestBody.get("uId").isEmpty()) {
//					RegionService.insertRegion(Region);
//				}else {
//					RegionService.updateRegion(Region);
//				}
//			}
//		}
//		data.setSuccess(true);
//		return data;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/o_deleteRegion",method=RequestMethod.POST)
//	public JsonResponse deleteRegion(@RequestBody Map<String,String> requestBody) {
//		System.out.println(requestBody);
//		JsonResponse data = new JsonResponse();
//		try {
//			Long uId = Long.parseLong(requestBody.get("id"));			
//			if(!RegionService.deleteRegion(uId)) {
//				data.setErrMsg("该商家不存在!");
//				return data;
//			}
//		} catch(Exception e) {
//			data.setErrMsg("查询参数不正确!");
//			return data;
//		}
//		data.setSuccess(true);
//		return data;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/o_getRegionType",method=RequestMethod.POST)
//	public JsonResponse getRegionType(@RequestBody Map<String,String> requestBody) {
//		JsonResponse data = new JsonResponse();
//		data.setData(Constants.RegionY_TYPE);
//		data.setSuccess(true);
//		return data;
//	}
//	
	 
//	
//	@ResponseBody
//	@RequestMapping(value="/o_getRegionById",method=RequestMethod.POST)
//	public JsonResponse getRegionById(@RequestBody Map<String,String> requestBody) {
//		JsonResponse data = new JsonResponse();
//		try {
//			Long id = Long.parseLong(requestBody.get("id"));
//			data.setData(RegionService.getRegionById(id));
//		} catch(Exception e) {
//			e.printStackTrace();
//			data.setErrMsg("查询参数不正确!");
//			return data;
//		}
//		data.setSuccess(true);
//		return data;
//	}
	
	@Autowired
	RegionService RegionService;

}

package com.qxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.pojo.Food;
import com.qxt.service.AccountService;
import com.qxt.service.FoodService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/food")
public class FoodController {
	
	@ResponseBody
	@RequestMapping(value="/o_getAllFood",method=RequestMethod.POST)
	public JsonResponse getAllFood(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		Food bean = new Food();
		try {
			if(requestBody.get("name") != null && !requestBody.get("name").isEmpty()) {
				bean.setName(requestBody.get("name"));
			}
			if(requestBody.get("uId") != null && !requestBody.get("uId").isEmpty()) {
				bean.setuId(Long.valueOf(requestBody.get("uId")));
			}
			data.setData(FoodService.selectAllFood(bean));
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
	@RequestMapping(value="/o_saveFood",method=RequestMethod.POST)
	public JsonResponse addFood(@RequestBody Map<String,String> requestBody) {
		System.out.println(requestBody);
		JsonResponse data = new JsonResponse();
		Food food = new Food();
		StringBuffer errMsg = new StringBuffer();
		try {
			if(requestBody.get("id") != null && !requestBody.get("id").isEmpty()) {
				food.setId(Long.parseLong(requestBody.get("id")));
			}
			food.setuId(Long.valueOf(requestBody.get("uId")));
			food.setName(requestBody.get("name"));
			food.setYuanPrice(Double.valueOf(requestBody.get("yuanPrice")));
			food.setCutPrice(Double.valueOf(requestBody.get("cutPrice")));
			food.setInf(requestBody.get("inf"));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("数据异常请重新提交!");
			return data;
		}
		if(!FoodService.isVaildFood(food, errMsg)) {
			data.setErrMsg(errMsg.toString());
			return data;
		}else {
				Long id = food.getId();
				food.setId(id);
				if(requestBody.get("id") == null || requestBody.get("id").isEmpty()) {
					FoodService.insertFood(food);
				}else {
					FoodService.updateFood(food);
				}
			}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_deleteFood",method=RequestMethod.POST)
	public JsonResponse deleteFood(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long id = Long.parseLong(requestBody.get("id"));			
			if(!FoodService.deleteFood(id)) {
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
	@RequestMapping(value="/o_getFoodById",method=RequestMethod.POST)
	public JsonResponse getFoodById(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long id = Long.parseLong(requestBody.get("id"));
			data.setData(FoodService.getFoodById(id));
		} catch(Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@Autowired
	FoodService FoodService;
	@Autowired
	AccountService accountService;

}

package com.qxt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qxt.pojo.OrderInf;
import com.qxt.pojo.Orderu;
import com.qxt.service.AccountService;
import com.qxt.service.OrderuService;
import com.qxt.utils.vo.JsonResponse;

@Controller
@RequestMapping(value="/order")
public class OrderuController {
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/o_add",method=RequestMethod.POST)
	public JsonResponse addOrder(@RequestBody Map<String,Object> requestBody) {
		JsonResponse data = new JsonResponse();
		Orderu bean = new Orderu();
		try {
			bean.setLocationFrom(requestBody.get("locationFrom").toString());
			bean.setLocationTo(requestBody.get("locationTo").toString());
			bean.setMergantId(Long.parseLong(requestBody.get("mergantId").toString()));
			bean.setUserId(Long.parseLong(requestBody.get("userId").toString()));
			bean.setRealPrice(Double.parseDouble(requestBody.get("realPrice").toString()));
			bean.setAddPrice(Double.parseDouble(requestBody.get("addPrice").toString()));
			bean.setRecName(requestBody.get("recName").toString());
			bean.setRecTel(requestBody.get("recTel").toString());
//			List<Map<String,String>> orderInfList = new Array
			List<OrderInf> orderInfList = new ArrayList<OrderInf>();
			List<Map<String,Object>> list = (List<Map<String,Object>>)requestBody.get("foodList");
			for (Map<String, Object> map : list) {
				OrderInf orderInf = new OrderInf();
				orderInf.setFoodName(map.get("fname").toString());
				orderInf.setNum((Integer)map.get("fnum"));
				orderInf.setPrice(Double.parseDouble(map.get("fvalue").toString()));
				orderInfList.add(orderInf);
			}
			orderuService.saveOrder(bean, orderInfList);
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_getOrderById",method=RequestMethod.POST)
	public JsonResponse getOrderById(@RequestBody Map<String,String> requestBody) {
//		System.out.println(requestBody);
		JsonResponse data = new JsonResponse();
		try {
			Long orderId = Long.parseLong(requestBody.get("orderId"));
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("order", orderuService.getOrderuById(orderId));
			map.put("orderInfList", orderuService.getOrderInfByoId(orderId));
			data.setData(map);
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_getAllOrder",method=RequestMethod.POST)
	public JsonResponse cgetAllOrderu(@RequestBody Map<String,String> requestBody) {
//		System.out.println(requestBody);
		JsonResponse data = new JsonResponse();
		Orderu bean = new Orderu();
		Integer pageNum = 1;
		try {
			if(requestBody.get("pageNum") != null && !requestBody.get("pageNum").isEmpty()) {
				pageNum = Integer.parseInt(requestBody.get("pageNum"));
			}
			if(requestBody.containsKey("mergantId")&&requestBody.get("mergantId") != null && !requestBody.get("mergantId").isEmpty()) {
				bean.setMergantId(Long.valueOf(requestBody.get("mergantId")));
			}
			if(requestBody.containsKey("courierId")&&requestBody.get("courierId") != null && !requestBody.get("courierId").isEmpty()) {
				bean.setCourierId(Long.valueOf(requestBody.get("courierId")));
			}
			data.setData(orderuService.selectAllOrderu(pageNum,bean));
		}catch (Exception e) {
			e.printStackTrace();
			data.setErrMsg("查询参数不正确!");
			return data;
		}
		data.setSuccess(true);
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/o_confirmOrder",method=RequestMethod.POST)
	public JsonResponse confirmOrderu(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long uId = Long.parseLong(requestBody.get("id"));			
			if(!orderuService.confirmOrderu(uId)) {
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
	@RequestMapping(value="/o_deleteOrder",method=RequestMethod.POST)
	public JsonResponse deleteOrderu(@RequestBody Map<String,String> requestBody) {
		JsonResponse data = new JsonResponse();
		try {
			Long uId = Long.parseLong(requestBody.get("id"));			
			if(!orderuService.deleteOrderu(uId)) {
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
	
	@Autowired
	OrderuService orderuService;
	@Autowired
	AccountService accountService;

}

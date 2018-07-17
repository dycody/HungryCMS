package com.qxt.utils.vo;

public class JsonResponse {
	
	/**
	 * 操作是否成功
	 */
	private Boolean success = false;
	
	/**
	 * 当 success = false 错误信息
	 */
	private String errMsg = "";
	
	/**
	 * 反馈json消息值
	 */
	private Object data;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}

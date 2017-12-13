package com.songfayuan.springBoot.utils;

public class Response {
	protected int code;
	protected String msg;
	protected Object data;

	private static final int SUCCESS_CODE = 200;
	private static final String SUCCESS_MSG = "success";
	private static final int ERROR_CODE = 500;
	private static final String ERROR_MSG = "服务器内部异常，请联系技术人员！";// 将error改成了内容信息

	public static Response success() {
		Response resp = new Response();
		resp.code = (SUCCESS_CODE);
		resp.msg = (SUCCESS_MSG);
		return resp;
	}

	public static Response successResponse(String msg) {
		Response resp = new Response();
		resp.code = SUCCESS_CODE;
		resp.msg = msg;
		return resp;
	}

	public static Response error() {
		Response resp = new Response();
		resp.code = (ERROR_CODE);
		resp.msg = (ERROR_MSG);
		return resp;
	}

	public static Response errorResponse(String msg) {
		Response resp = new Response();
		resp.code = ERROR_CODE;
		resp.msg = msg;
		return resp;
	}

	public static Response response(int code, String msg) {
		Response resp = new Response();
		resp.code = (code);
		resp.msg = (msg);
		return resp;
	}

	public static Response response(int code, String msg, Object data) {
		Response resp = new Response();
		resp.code = (code);
		resp.msg = (msg);
		resp.data = data;
		return resp;
	}

	public static Response success(Object data) {
		Response resp = new Response();
		resp.code = (SUCCESS_CODE);
		resp.msg = (SUCCESS_MSG);
		resp.data = data;
		return resp;
	}

	public static Response error(Object data) {
		Response resp = new Response();
		resp.code = (ERROR_CODE);
		resp.msg = (ERROR_MSG);
		resp.data = data;
		return resp;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

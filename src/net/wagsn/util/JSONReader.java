package net.wagsn.util;

import static net.wagsn.util.Logger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 读取请求中的JSON数据，假定请求数据为JSON数据
 * @class JSONReader
 * @package net.wagsn.util
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月15日
 * @version 1.0.0
 */
public class JSONReader {
	/**
	 * 获取request中的JSON数据
	 * @param request HttpServletRequest
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		log.methodStart();
		
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println("--request data-> "+sb.toString());
		//将json字符串转换为json对象
		JSONObject json=JSONObject.parseObject(sb.toString());
		
		log.methodEnd();
		return json;
	}
	
	public static String data(HttpServletRequest request) throws IOException {
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}

package net.wagsn.util;

import static net.wagsn.util.Logger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * ��ȡ�����е�JSON���ݣ��ٶ���������ΪJSON����
 * @class JSONReader
 * @package net.wagsn.util
 * @project SHT_Book
 * @author Wagsn
 * @date 2018��8��15��
 * @version 1.0.0
 */
public class JSONReader {
	/**
	 * ��ȡrequest�е�JSON����
	 * @param request HttpServletRequest
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		log.methodStart();
		
		// ��ȡ��������
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println("--request data-> "+sb.toString());
		//��json�ַ���ת��Ϊjson����
		JSONObject json=JSONObject.parseObject(sb.toString());
		
		log.methodEnd();
		return json;
	}
	
	public static String data(HttpServletRequest request) throws IOException {
		// ��ȡ��������
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}

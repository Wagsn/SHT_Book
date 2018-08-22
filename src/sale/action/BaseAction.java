/**
 * @file BaseAction.java
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月21日
 * @version 1.0.0
 */
package sale.action;

import static net.wagsn.util.Logger.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import net.wagsn.util.JSONReader;

/**
 * @class BaseAction
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月21日
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	/**
	 * 通用JSON请求处理并响应函数
	 * @param handle
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String handle(HandleRequest handle) throws UnsupportedEncodingException, IOException {
		log.methodStart();
		
		//1.获取请求数据，不能两次获取
		JSONObject reqdata =JSONReader.receivePost(ServletActionContext.getRequest());
		System.out.println("--request-> "+reqdata.toJSONString());
		
		//2 处理请求获取响应数据，输入JSONObject，输出String
		String resDataStr = handle.accept(reqdata);
		
		//3获取响应体，乱码处理
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		//4把响应数据返回给浏览器
		response.getWriter().write(resDataStr);
		System.out.println("--response->List<Chat>: "+resDataStr);
		
		log.methodEnd();
		return NONE;
	}
	@FunctionalInterface
	interface HandleRequest {
		public String accept(JSONObject reqdata);
	}
}

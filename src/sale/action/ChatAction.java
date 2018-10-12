/**
 * @file ChatAction.java
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.action;

import sale.service.ChatService;

import static net.wagsn.util.Logger.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import net.wagsn.util.JSONReader;

/**
 * @class ChatAction
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ChatAction extends ActionSupport {
	
	ChatService chatService;
	public ChatService getChatService() {
		return chatService;
	}
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
	
	public String findLst() throws UnsupportedEncodingException, IOException {
		log.methodStart();
		
		JSONObject reqdata =JSONReader.receivePost(ServletActionContext.getRequest());
		System.out.println("--request-> "+reqdata.toJSONString());
		
		log.methodEnd();
		return NONE;
	}
	
	/**
	 * 通过发信人与收信人id查询出对话列表通过JSON发送数据给前端
	 * @return
	 * @throws IOException
	 */
	public String findAllById() throws IOException {
		return handle((reqdata)->JSONArray.toJSONString(chatService.findAllById(reqdata.getIntValue("src_id"), reqdata.getIntValue("dst_id"))));
	}
	/**
	 * 通过用户id寻找最后对话消息列表
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public String findAllLastById() throws UnsupportedEncodingException, IOException {
		return handle((reqdata)->JSONArray.toJSONString(chatService.findAllLastById(reqdata.getIntValue("usr_id"))));
	}
	
	/**
	 * 发送私信
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public String addChat() throws UnsupportedEncodingException, IOException {
		return handle((reqdata)->{
			chatService.addChat(reqdata.getIntValue("src_id"), reqdata.getIntValue("dst_id"), reqdata.getString("msg"));
			return "{}";  // 这个是返回给浏览器的数据
		});
	}
	
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
}

@FunctionalInterface
interface HandleRequest { 
	public String accept(JSONObject reqdata);
}

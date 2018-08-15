/**
 * @file ChatAction.java
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.action;

import sale.domain.Chat;
import sale.service.ChatService;

import static net.wagsn.util.Logger.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @class ChatAction
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public class ChatAction extends ActionSupport {
	
	ChatService chatService;
	public ChatService getChatService() {
		return chatService;
	}
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
	/**
	 * 通过发信人与收信人id查询出对话列表通过JSON发送数据给前端
	 * @return
	 * @throws IOException
	 */
	public String findAllById() throws IOException {
		log.methodStart();
		
//		ActionContext.getContext().getSession().put("loginStaff", findStaff);

		//3、返回json数据，
		//3.1获取响应体
		HttpServletResponse response = ServletActionContext.getResponse();
		//3.2乱码处理
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		//3.3 获取原始数据包装成json
		List<Chat> chatList = chatService.findAllById(1, 2);
		String json = JSONArray.toJSONString(chatList);
		//3.4把json数据返回给浏览器
		response.getWriter().write(json);
		System.out.println("--response->List<Chat>: "+json);
		
		log.methodEnd();
		return NONE;
	}
}

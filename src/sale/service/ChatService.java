/**
 * @file ChatService.java
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.service;

import java.util.List;

import sale.domain.Chat;

/**
 * @class ChatService
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public interface ChatService {
	/**
	 * 根据发信人id和收信人id查找对话
	 * @param src_id 发信人id
	 * @param dst_id 收信人id
	 * @return
	 */
	public List<Chat> findAllById(int src_id, int dst_id);
	
	/**
	 * 查询两个人之间的最后对话
	 * @param src_id
	 * @param dst_id
	 * @return
	 */
	public Chat findLastById(int src_id, int dst_id);
	
	/**
	 * 查询一个人的所有最后聊天，只包含他关注的人
	 * @param src_id
	 * @return
	 */
	public List<Chat> findAllLastById(int src_id);
	
	/**
	 * 添加私聊信息
	 * @param src_id 发信人id
	 * @param dst_id 接收人id
	 * @param msg
	 */
	public void addChat(int src_id, int dst_id, String msg);
	
	/**
	 * 保存新的Chat，如果id=0表示新消息
	 * @param chat
	 */
	public void save(Chat chat);
}

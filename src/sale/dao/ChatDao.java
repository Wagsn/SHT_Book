/**
 * @file ChatDao.java
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.dao;

import java.util.List;

import sale.domain.Chat;

/**
 * @class ChatDao
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public interface ChatDao {
	/**
	 * 查询两个用户之间的对话信息列表
	 * @param src_id
	 * @param dst_id
	 * @return
	 */
	public List<Chat> findAllById(int src_id, int dst_id);
	
	/**
	 * 查询两个人之间的最后对话
	 * @param usr_id
	 */
	public Chat findLastById(int src_id, int dst_id);
	
	/**
	 * 添加私聊信息
	 * @param src_id 发信人id
	 * @param dst_id 接收人id
	 * @param msg
	 */
	public void addChat(int src_id, int dst_id, String msg);
	
	/**
	 * 保存私聊信息，如果id==0，则添加，否则为更新
	 * @param chat
	 */
	public void save(Chat chat);
	
	/**
	 * 加载两人之间的对话列表
	 * @param src_id
	 * @param dst_id
	 * @return
	 */
	public List<Chat> loadAll(int src_id, int dst_id);
	
	/**
	 * 加载收信人的所有对话列表的最后回复
	 * @param dst_id
	 */
	public void loadLast(int dst_id);
}

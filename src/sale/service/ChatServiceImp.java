/**
 * @file ChatServiceImp.java
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.service;

import java.util.List;

import sale.dao.ChatDao;
import sale.domain.Chat;

/**
 * @class ChatServiceImp
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public class ChatServiceImp implements ChatService {

	/**
	 * 数据库操作对象
	 */
	private ChatDao chatDao;
	public ChatDao getChatDao() {
		return chatDao;
	}
	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
	/* (non-Javadoc)
	 * @see sale.service.ChatService#findAllById(int, int)
	 */
	@Override
	public List<Chat> findAllById(int src_id, int dst_id) {
		// TODO Auto-generated method stub
		return chatDao.findAllById(src_id, dst_id);
	}
	/* (non-Javadoc)
	 * @see sale.service.ChatService#addChat(int, int, java.lang.String)
	 */
	@Override
	public void addChat(int src_id, int dst_id, String msg) {
		// TODO Auto-generated method stub
		
	}

}

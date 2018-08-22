/**
 * @file ChatServiceImp.java
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.service;

import static net.wagsn.util.Logger.log;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.log.Log;

import sale.dao.ChatDao;
import sale.dao.FriendRelationDao;
import sale.domain.Chat;
import sale.domain.Friend_Relation;

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
	
	private FriendRelationDao friendRelationDao;
	public FriendRelationDao getFriendRelationDao() {
		return friendRelationDao;
	}
	public void setFriendRelationDao(FriendRelationDao friendRelationDao) {
		this.friendRelationDao = friendRelationDao;
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
		chatDao.addChat(src_id, dst_id, msg);
	}
	/* (non-Javadoc)
	 * @see sale.service.ChatService#save(sale.domain.Chat)
	 */
	@Override
	public void save(Chat chat) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see sale.service.ChatService#findLastById(int, int)
	 */
	@Override
	public Chat findLastById(int src_id, int dst_id) {
		// TODO Auto-generated method stub
		return chatDao.findLastById(src_id, dst_id);
	}
	/* (non-Javadoc)
	 * @see sale.service.ChatService#findAllLastById(int)
	 */
	@Override
	public List<Chat> findAllLastById(int src_id) {
		log.methodStart();
		
		System.out.println("--params-> src_id: "+src_id);
		
		// 1. 查询所有好友
		List<Friend_Relation> friend_Relations = friendRelationDao.findAllRelationById(src_id);
		System.out.println(friend_Relations);
		List<Chat> result =new ArrayList<>();
		
		for (Friend_Relation friend_Relation : friend_Relations) {
			// 2. 根据所有好友查询两者之间的最后对话
			Chat chat = findLastById(friend_Relation.getSrc_id(), friend_Relation.getDst_id());
			if (chat!=null) {
				result.add(chat);
			}
		}
		System.out.println(result.toString());
		// 去重
		List<Chat> temp = new ArrayList<>();
		for (Chat chat : result) {
			if (!temp.contains(chat)) {
				temp.add(chat);
			}
		}
		result.clear();
		result.addAll(temp);
		System.out.println("--ret data-> "+JSONArray.toJSONString(result));
		
		log.methodEnd();
		return result;
	}

}

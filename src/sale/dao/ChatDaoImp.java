/**
 * @file ChatDapImp.java
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import static net.wagsn.util.Logger.log;
import static org.junit.Assert.*;

import sale.domain.Chat;

/**
 * ChatDao实现类
 * @see sale.dao.ChatDao
 * @class ChatDapImp
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public class ChatDaoImp implements ChatDao {
	
	SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Chat> findAllById(int src_id, int dst_id) {
		Session session = sessionFactory.getCurrentSession();
		// 查询src_id 发给 dst_id
		String hql = "from Chat where src_id = ? and dst_id = ?";  
		Query query = session.createQuery(hql);
		query.setParameter(0, src_id);
		query.setParameter(1, dst_id);
		List<Chat> result = new ArrayList<>();
		result.addAll(query.list());
		query.setParameter(0, dst_id);
		query.setParameter(1, src_id);
		result.addAll(query.list());
		result.sort((o1, o2)->{
			return o1.getTime().getTime()>o2.getTime().getTime()?1:(o1.getTime().getTime()==o2.getTime().getTime()?0:-1);
		});
		return result;
	}
	/**
	 * 查询两个人之间的最后对话
	 * @see sale.dao.ChatDao#findLastById(int, int)
	 */
	@Override
	public Chat findLastById(int src_id, int dst_id) {
		log.methodStart();
		
		System.out.println("--params-> src_id: "+src_id+", dst_id: "+dst_id);
		
		List<Chat> temp = findAllById(src_id, dst_id);
		if ((temp!=null)&&(!temp.isEmpty())) {
			temp.sort((o1, o2)->{
				return o1.getTime().getTime()>o2.getTime().getTime()?1:(o1.getTime().getTime()==o2.getTime().getTime()?0:-1);
			});
			System.out.println("--ret data-> "+JSONObject.toJSON(temp.get(temp.size()-1)));
			log.methodEnd();
			return temp.get(temp.size()-1);
		}
		System.out.println("--ret data-> "+ temp != null&&temp.isEmpty()?"null":JSONObject.toJSONString(temp.get(temp.size()-1)));
		
		log.methodEnd();
		return null;
	}
	/** 
	 * TODO 实现
	 */
	@Override
	public void addChat(int src_id, int dst_id, String msg) {
		log.methodStart();
		Session session = sessionFactory.getCurrentSession();
		session.save(new Chat(0, src_id, dst_id, msg, new Date()));
		log.methodEnd();
	}
	/* (non-Javadoc)
	 * @see sale.dao.ChatDao#save(sale.domain.Chat)
	 */
	@Override
	public void save(Chat chat) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see sale.dao.ChatDao#loadAll(int, int)
	 */
	@Override
	public List<Chat> loadAll(int src_id, int dst_id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see sale.dao.ChatDao#loadLast(int)
	 */
	@Override
	public void loadLast(int dst_id) {
		// TODO Auto-generated method stub
		
	}

}

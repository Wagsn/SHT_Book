/**
 * @file ChatDapImp.java
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.dao;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
public class ChatDapImp implements ChatDao {
	
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
		List<Chat> result = query.list();
		query.setParameter(0, dst_id);
		query.setParameter(1, src_id);
		result.addAll(query.list());
		result.sort(new Comparator<Chat>() {
			@Override
			public int compare(Chat o1, Chat o2) {
				return o1.getTime().getTime()>o2.getTime().getTime()?1:(o1.getTime().getTime()==o2.getTime().getTime()?0:-1);
			}
		});
		return result;
	}
	/** 
	 * TODO 实现
	 */
	@Override
	public void addChat(int src_id, int dst_id, String msg) {
		// TODO Auto-generated method stub
		
	}

}

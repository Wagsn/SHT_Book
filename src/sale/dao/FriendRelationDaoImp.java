/**
 * @file FriendRelationDaoImp.java
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
package sale.dao;

import static net.wagsn.util.Logger.log;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alibaba.fastjson.JSONArray;

import sale.domain.Chat;
import sale.domain.Friend_Relation;

/**
 * @class FriendRelationDaoImp
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
public class FriendRelationDaoImp implements FriendRelationDao {
	
	SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询 所有有关系的人
	 * @see sale.dao.FriendRelationDao#findAllRelationById(int)
	 */
	@Override
	public List<Friend_Relation> findAllRelationById(int src_id) {
		log.methodStart();
		
		Session session = sessionFactory.getCurrentSession();
		// 查村关注人
		String hql = "from Friend_Relation where src_id = ?";  
		Query query = session.createQuery(hql);
		query.setParameter(0, src_id);
		List<Friend_Relation> result = new ArrayList<>();
		result.addAll(query.list());
		// 查询粉丝
		String hql2 = "from Friend_Relation where dst_id = ?"; 
		Query query2 = session.createQuery(hql2); 
		query2.setParameter(0, src_id);
		result.addAll(query2.list());
		// 将两个人之间的关系去重
		System.out.println("--select data-> "+JSONArray.toJSONString(result));
		
		log.methodEnd();
		return result;
	}
	/**
	 * 关注
	 * @see sale.dao.FriendRelationDao#follow(int, int)
	 */
	@Override
	public int follow(int src_id, int dst_id) {
		log.methodStart();
		
		Session session = sessionFactory.getCurrentSession();
		// 关注人 
		session.save(new Friend_Relation(0, src_id, dst_id, 1));

		log.methodEnd();
		return 0;
	}

}

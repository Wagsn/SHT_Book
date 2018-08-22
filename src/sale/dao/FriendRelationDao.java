/**
 * @file Friend_Relation.java
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
package sale.dao;

import java.util.List;

import sale.domain.Friend_Relation;

/**
 * @class Friend_Relation
 * @package sale.dao
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
public interface FriendRelationDao {
	/**
	 * 通过发信人id找到他关注的人
	 * @param src_id
	 * @return
	 */
	public List<Friend_Relation> findAllRelationById(int src_id);
	
	/**
	 * 找到所有有关系的关系
	 * @param src_id
	 * @return
	 */
	public List<Friend_Relation> findAllById(int src_id);
}

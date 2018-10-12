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
	 * 关注其他用户
	 * @param src_id
	 * @param dst_id
	 * @return 0 成功, 1 已经关注的不能再次关注
	 */
	public int follow(int src_id, int dst_id);
}

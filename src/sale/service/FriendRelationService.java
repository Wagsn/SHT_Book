/**
 * @file FriendRelationService.java
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
package sale.service;

import java.util.List;

import sale.domain.Friend_Relation;

/**
 * @class FriendRelationService
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
public interface FriendRelationService {
	/**
	 * 通过发信人id找到他关注的人
	 * @param src_id
	 * @return
	 */
	public List<Friend_Relation> findAllById(int src_id);
	
	/**
	 * 关注
	 * @param src_id 关注人
	 * @param dst_id 被关注人
	 * @return
	 */
	public int follow(int src_id, int dst_id);
}

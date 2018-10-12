/**
 * @file FriendRelationServiceImp.java
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
package sale.service;

import java.util.List;

import sale.dao.FriendRelationDao;
import sale.domain.Friend_Relation;

/**
 * @class FriendRelationServiceImp
 * @package sale.service
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
public class FriendRelationServiceImp implements FriendRelationService {

	private FriendRelationDao friendRelationDao;
	
	
	public FriendRelationDao getFriendRelationDao() {
		return friendRelationDao;
	}


	public void setFriendRelationDao(FriendRelationDao friendRelationDao) {
		this.friendRelationDao = friendRelationDao;
	}


	/* (non-Javadoc)
	 * @see sale.service.FriendRelationService#findAllById(int)
	 */
	@Override
	public List<Friend_Relation> findAllById(int src_id) {
		// TODO Auto-generated method stub
		return friendRelationDao.findAllRelationById(src_id);
	}


	/* (non-Javadoc)
	 * @see sale.service.FriendRelationService#follow(int, int)
	 */
	@Override
	public int follow(int src_id, int dst_id) {
		return friendRelationDao.follow(src_id, dst_id);
	}

}

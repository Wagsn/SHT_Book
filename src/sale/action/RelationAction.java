/**
 * @file RelationAction.java
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月21日
 * @version 1.0.0
 */
package sale.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;

import sale.service.FriendRelationService;

/**
 * @class RelationAction
 * @package sale.action
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月21日
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class RelationAction extends BaseAction {
	
	private FriendRelationService friendRelationService;
	
	public FriendRelationService getFriendRelationService() {
		return friendRelationService;
	}
	public void setFriendRelationService(FriendRelationService friendRelationService) {
		this.friendRelationService = friendRelationService;
	}
	/**
	 * 查询用户的所有关系
	 * @param usr_id
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String findAllById() throws UnsupportedEncodingException, IOException {
		return handle((reqdata)->{
			return JSONArray.toJSONString(friendRelationService.findAllById(reqdata.getIntValue("usr_id")));
		});
	}
	
	public String follow() throws UnsupportedEncodingException, IOException {
		return handle((reqdata)->{
			friendRelationService.follow(reqdata.getIntValue("src_id"), reqdata.getIntValue("dst_id"));
			return "{\"ret_code\": 0}";  // 响应数据 res data
		});
	}
}

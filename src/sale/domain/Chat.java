/**
 * @file Chat.java
 * @package sale.domain
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package sale.domain;

import java.util.Date;

/**
 * 私信实体类，TODO 增加字段 chat_type 以实现红包链接等？？ chat_type 将msg通过约定的格式进行解析
 * @class Chat
 * @package sale.domain
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public class Chat {
	/**
	 * 私信id
	 */
	private int id;
	/**
	 * 发信人id，外键
	 */
	private int src_id;
	/**
	 * 收信人id，外键
	 */
	private int dst_id;
	/**
	 * 发送的消息
	 */
	private String msg;
	/**
	 * 发送时间
	 */
	private Date time;
	
	/**
	 * 空构造器
	 */
	public Chat() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 全参构造器
	 * @param id
	 * @param src_id
	 * @param dst_id
	 * @param msg
	 * @param time
	 */
	public Chat(int id, int src_id, int dst_id, String msg, Date time) {
		this.id = id;
		this.src_id = src_id;
		this.dst_id = dst_id;
		this.msg = msg;
		this.time = time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSrc_id() {
		return src_id;
	}
	public void setSrc_id(int src_id) {
		this.src_id = src_id;
	}
	public int getDst_id() {
		return dst_id;
	}
	public void setDst_id(int dst_id) {
		this.dst_id = dst_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}

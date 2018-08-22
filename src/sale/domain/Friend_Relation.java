/**
 * @file Friend_Relation.java
 * @package sale.domain
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
package sale.domain;

/**
 * @class Friend_Relation
 * @package sale.domain
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月17日
 * @version 1.0.0
 */
public class Friend_Relation {
	/**
	 * 关联表id
	 */
	private int id;
	/**
	 * 发信人id，关注人
	 */
	private int src_id;
	/**
	 * 收信人id，被关注人
	 */
	private int dst_id;
	/**
	 * 关系id，用于分组
	 */
	private int rel_id;
	
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
	public int getRel_id() {
		return rel_id;
	}
	public void setRel_id(int rel_id) {
		this.rel_id = rel_id;
	}
	
}

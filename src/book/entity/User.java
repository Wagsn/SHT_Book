package book.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 数据库的user表相对应的实体类
 * 使用注解方式
 * @author ff
 *
 */

/*
 * entity：实体类注解
 */
@Entity
public class User {
	private Integer userId;
	private String userName;
	private Integer userPwd;
	private String nickName;
	/*
	 * 和BookGallery多对一
	 */
	private BookGallery bookGallery;
	/*
	 * 和Book一对多
	 */
	private Set<Book> books=new HashSet<Book>();
	
	/*
	 * id：主键
	 * GeneratedValue：默认的自增模式
	 */
	
	@Id
	@GeneratedValue
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@OneToMany(cascade={CascadeType.ALL},
			mappedBy="user",
			fetch=FetchType.LAZY)
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(Integer userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	/*
	 * @ManyToOne：多对一的关系
	 * cascade:DML操作时候会自动保存另外一边
	 * fetch=FetchType.LAZY:当查询该字段的时候才会发出sql语句
	 * @JoinColumn：对应数据库中的字段
	 */
	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="bookGalleryId")
	public BookGallery getBookGallery() {
		return bookGallery;
	}

	public void setBookGallery(BookGallery bookGallery) {
		this.bookGallery = bookGallery;
	}
	
	@Override
	public String toString() {
		return "用户名:"+this.getUserName()
				+"用户昵称:"+this.getNickName()
				+"用户所属书廊:"+this.getBookGallery().getBookGalleryName();
	}
}

package book.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 数据库的bookGallery表相对应的实体类 使用注解方式
 * 
 * @author admin
 *
 */
//实体类注解
@Entity
public class BookGallery {
	private Integer bookGalleryId;
	private String bookGalleryName;
	private String bookGalleryIntroduction;
	/*
	 * 与user一对多
	 */
	private Set<User> users=new HashSet<User>();
	/*
	 * 与Book是一对多
	 */
	private Set<Book> books=new HashSet<Book>();
	
	
	/**
	 *id：主键
	 * GeneratedValue：默认的自增模式
	 * 
	 */
	
	@Id
	@GeneratedValue
	public Integer getBookGalleryId() {
		return bookGalleryId;
	}


	public void setBookGalleryId(Integer bookGalleryId) {
		this.bookGalleryId = bookGalleryId;
	}


	public String getBookGalleryName() {
		return bookGalleryName;
	}


	public void setBookGalleryName(String bookGalleryName) {
		this.bookGalleryName = bookGalleryName;
	}


	public String getBookGalleryIntroduction() {
		return bookGalleryIntroduction;
	}


	public void setBookGalleryIntroduction(String bookGalleryIntroduction) {
		this.bookGalleryIntroduction = bookGalleryIntroduction;
	}

	
	/*
	 * @OneToMany:一对多的关系
	 * cascade:DML操作时候会自动保存另外一边
	 * fetch=FetchType.LAZY:当查询该字段的时候才会发出sql语句
	 * mappedBy="bookGallery"：以User那边的bookGallery为主导，就是把数据库字段建立在那边
	 */
	
	@OneToMany(cascade={CascadeType.ALL},
			mappedBy="bookGallery",
			fetch=FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy="bookGallery",
			cascade={CascadeType.ALL})
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	@Override
	public String toString() {
		return "书廊名称:" + this.getBookGalleryName() + "书廊简介:" + this.getBookGalleryIntroduction();
	}
}

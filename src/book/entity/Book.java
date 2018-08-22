package book.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * ��Ӧ���ݿ�book��(ע��)
 * @author admin
 *
 */

@Entity
public class Book {
	private Integer bookId;
	/*
	 * ��userһ��һ
	 */
	private User user;
	private String bookName;
	private String bookIntroduction;
	private String bookPicture;
	/*
	 * ��type���һ
	 */
	private Type type;
	/*
	 * ��BookGallery���һ
	 */
	private BookGallery bookGallery;
	private String bookPost;
	private Date bookTime;
	
	
	@Id
	@GeneratedValue
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookIntroduction() {
		return bookIntroduction;
	}
	public void setBookIntroduction(String bookIntroduction) {
		this.bookIntroduction = bookIntroduction;
	}
	public String getBookPicture() {
		return bookPicture;
	}
	public void setBookPicture(String bookPicture) {
		this.bookPicture = bookPicture;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="typeId")
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="bookGalleryId")
	public BookGallery getBookGallery() {
		return bookGallery;
	}
	public void setBookGallery(BookGallery bookGallery) {
		this.bookGallery = bookGallery;
	}
	public String getBookPost() {
		return bookPost;
	}
	public void setBookPost(String bookPost) {
		this.bookPost = bookPost;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	
	@Override
	public String toString() {
		return "����:"+this.getBookName()
				+"�鼮���:"+this.getBookIntroduction();
	}
	
	
}

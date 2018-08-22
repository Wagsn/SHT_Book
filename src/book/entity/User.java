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
 * ���ݿ��user�����Ӧ��ʵ����
 * ʹ��ע�ⷽʽ
 * @author ff
 *
 */

/*
 * entity��ʵ����ע��
 */
@Entity
public class User {
	private Integer userId;
	private String userName;
	private Integer userPwd;
	private String nickName;
	/*
	 * ��BookGallery���һ
	 */
	private BookGallery bookGallery;
	/*
	 * ��Bookһ�Զ�
	 */
	private Set<Book> books=new HashSet<Book>();
	
	/*
	 * id������
	 * GeneratedValue��Ĭ�ϵ�����ģʽ
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
	 * @ManyToOne�����һ�Ĺ�ϵ
	 * cascade:DML����ʱ����Զ���������һ��
	 * fetch=FetchType.LAZY:����ѯ���ֶε�ʱ��Żᷢ��sql���
	 * @JoinColumn����Ӧ���ݿ��е��ֶ�
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
		return "�û���:"+this.getUserName()
				+"�û��ǳ�:"+this.getNickName()
				+"�û���������:"+this.getBookGallery().getBookGalleryName();
	}
}

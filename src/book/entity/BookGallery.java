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
 * ���ݿ��bookGallery�����Ӧ��ʵ���� ʹ��ע�ⷽʽ
 * 
 * @author admin
 *
 */
//ʵ����ע��
@Entity
public class BookGallery {
	private Integer bookGalleryId;
	private String bookGalleryName;
	private String bookGalleryIntroduction;
	/*
	 * ��userһ�Զ�
	 */
	private Set<User> users=new HashSet<User>();
	/*
	 * ��Book��һ�Զ�
	 */
	private Set<Book> books=new HashSet<Book>();
	
	
	/**
	 *id������
	 * GeneratedValue��Ĭ�ϵ�����ģʽ
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
	 * @OneToMany:һ�Զ�Ĺ�ϵ
	 * cascade:DML����ʱ����Զ���������һ��
	 * fetch=FetchType.LAZY:����ѯ���ֶε�ʱ��Żᷢ��sql���
	 * mappedBy="bookGallery"����User�Ǳߵ�bookGalleryΪ���������ǰ����ݿ��ֶν������Ǳ�
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
		return "��������:" + this.getBookGalleryName() + "���ȼ��:" + this.getBookGalleryIntroduction();
	}
}

package book.dao;

import java.util.List;

import book.entity.BookGallery;

public interface BookGalleryDao {

	/*
	 * 通过id来查询BookGallery，
	 * 通过BookGallery对象里面的Set<Book>来查询该书廊下所有的书籍信息
	 * 通过BookGallery对象里面的Set<User>来查询该书廊下所有的用户信息
	 */
	public BookGallery findById(Integer bookGalleryId);

	// 通过名字来查询书廊
	public List<BookGallery> findByName(String bookGalleryName);
	
	//查询所有的书廊
	public List<BookGallery> findAll();
	
}

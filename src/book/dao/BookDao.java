package book.dao;

import java.util.List;

import book.entity.Book;

public interface BookDao {
	
	//查找所有书籍，用于显示
	public List<Book> findAll(); 
	
	//用于查找该书籍信息
	public Book findById(Integer bookId);
	
	//添加书籍
	public boolean save(Book book);
	
	//删除书籍
	public boolean update(Book book);
	//修改书籍
	public boolean remove(Book book);
	
	public boolean remove(Integer id);
	
	
}

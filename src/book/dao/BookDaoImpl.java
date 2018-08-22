package book.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import book.entity.Book;

public class BookDaoImpl implements BookDao {

	private Session session;

	public BookDaoImpl(SessionFactory sessionFactory) {
		this.session=sessionFactory.openSession();
	}
	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		return session.createQuery("from Book").list();
		
	}

	public Book findById(Integer bookId) {
		return (Book) session.get(Book.class, bookId);
	}
	@Override
	public boolean save(Book book) {
		boolean flag=false;
		if(book.getBookGallery()!=null){
			book.getBookGallery().getBooks().add(book);
		}else if(book.getType()!=null){
			book.getType().getBooks().add(book);
		}else if(book.getUser()!=null){
			book.getUser().getBooks().add(book);
		}
		Integer i=(int) session.save(book);
		if(i!=null){
			flag=true;
		}
		return flag;
	}
	@Override
	public boolean update(Book book) {
		try {
			if(book.getBookGallery()!=null){
				book.getBookGallery().getBooks().add(book);
			}else if(book.getType()!=null){
				book.getType().getBooks().add(book);
			}else if(book.getUser()!=null){
				book.getUser().getBooks().add(book);
			}
			session.update(book);
			session.flush();
			return true;
		} catch (Exception e) {
			System.out.println("更新Book错误");
			return false;
		}
	}
	@Override
	public boolean remove(Book book) {
		boolean flag=false;
		Integer i=session.createQuery("delete from Book b where b.id = ?")
				.setParameter(0, book.getBookId()).executeUpdate();
		if(i!=null){
			flag=true;
		}
		return flag;
	}
	@Override
	public boolean remove(Integer id) {
		boolean flag=false;
		Integer i=session.createQuery("delete from Book b where b.id = ?")
				.setParameter(0, id).executeUpdate();
		if(i!=null){
			flag=true;
		}
		return flag;
	}
}

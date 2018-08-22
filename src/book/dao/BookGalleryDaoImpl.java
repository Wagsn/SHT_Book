package book.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import book.entity.BookGallery;

public class BookGalleryDaoImpl implements BookGalleryDao {

	private Session session;
	public BookGalleryDaoImpl(SessionFactory sessionFactory ) {
		this.session=sessionFactory.openSession();
	}
	@Override
	public BookGallery findById(Integer bookGalleryId) {
		return (BookGallery) session.get(BookGallery.class, bookGalleryId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookGallery> findByName(String bookGalleryName) {
		return session.createQuery("from BookGallery where bookGalleryName=?")
				.setParameter(0, bookGalleryName)
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookGallery> findAll() {
		return session.createQuery("from BookGallery").list();
	}


	
}

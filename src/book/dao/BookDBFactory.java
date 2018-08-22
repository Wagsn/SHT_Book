package book.dao;


/**
 * Book操作数据库的对象工厂
 * @author admin
 *
 */
public class BookDBFactory {
	private BookDao bookDao;
	private BookGalleryDao bookGalleryDao;
	private FatherTypeDao fatherTypeDao;
	private OrderDao orderDao;
	private TypeDao typeDao;
	private UserDao userDao;
	
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public BookGalleryDao getBookGalleryDao() {
		return bookGalleryDao;
	}
	public void setBookGalleryDao(BookGalleryDao bookGalleryDao) {
		this.bookGalleryDao = bookGalleryDao;
	}
	public FatherTypeDao getFatherTypeDao() {
		return fatherTypeDao;
	}
	public void setFatherTypeDao(FatherTypeDao fatherTypeDao) {
		this.fatherTypeDao = fatherTypeDao;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public TypeDao getTypeDao() {
		return typeDao;
	}
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}

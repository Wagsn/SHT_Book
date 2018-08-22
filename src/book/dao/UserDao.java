package book.dao;

import java.util.List;

import book.entity.User;


/**
 * User对象操作接口
 * @author admin
 *
 */
public interface UserDao {
	/*
	 * 通过id来查询User对象
	 * 通过User对象下Set<Book>查询该用户下所有的书籍信息
	 */
	public User findById(Integer userId);

	//通过User的Name来查询User对象
	public List<User> findByName(String userName);
	
	//查询所有的用户
	public List<User> findAll();
	
	/**
	 * 把BookGallery的数据封装到BookGallery对象中
	 * 再把该对象设置到User中，最后传入User
	 * @param user
	 * @return
	 */
	public boolean save(User user);
	
	//修改User对象
	public boolean update(User user);
	
	//删除User对象
	public boolean remove(User user);
	
	//删除id所对应的User对象
	public boolean remove(int id);
	
	
}

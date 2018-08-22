package book.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import book.entity.User;

public class UserDaoImpl implements UserDao{
	private Session session;
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.session=sessionFactory.openSession();
	}
	
	@Override
	public User findById(Integer userId) {
		return (User) session.get(User.class, userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String userName) {
		return  session.createQuery("from User where userName=?")
				.setParameter(0, userName)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return session.createQuery("from User").list();
	}
	
	
	
	@Override
	public boolean save(User user) {
		boolean flag=false;
		if(user.getBookGallery()!=null){
			user.getBookGallery().getUsers().add(user);
		}
		Integer i=(int) session.save(user);
		if(i!=null){
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean update(User user) {
		try {
			if(user.getBookGallery()!=null){
				user.getBookGallery().getUsers().add(user);
			}
			session.update(user);
			session.flush();
			System.out.println("???");
			return true;
		} catch (Exception e) {
			System.out.println("¸üÐÂUser´íÎó");
			return false;
		}
		
	}

	@Override
	public boolean remove(User user) {
		boolean flag=false;
		Integer i=session.createQuery("delete from User u where u.id = ?")
				.setParameter(0, user.getUserId()).executeUpdate();
		if(i!=null){
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean remove(int id) {
		boolean flag=false;
		Integer i=session.createQuery("delete from User u where u.id = ?")
				.setParameter(0, id).executeUpdate();
		if(i!=null){
			flag=true;
		}
		return flag;
	}

	
	
	
}

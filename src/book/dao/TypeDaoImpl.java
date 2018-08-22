package book.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import book.entity.Type;

public class TypeDaoImpl implements TypeDao{
	private Session session;
	
	public TypeDaoImpl(SessionFactory sessionFactory) {
		this.session=sessionFactory.openSession();
	}

	@Override
	public Type findByTypeId(Integer typeId) {
		return (Type) session.get(Type.class, typeId);
	}
	
	

}

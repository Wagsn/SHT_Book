package book.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import book.entity.FatherType;

public class FatherTypeDaoImpl implements FatherTypeDao{
	private Session session;
	public FatherTypeDaoImpl(SessionFactory sessionFactory) {
		this.session=sessionFactory.openSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FatherType> findAll() {
		return session.createQuery("from FatherType").list();
	}

	@Override
	public FatherType findByFathertypeId(int fathertypeId) {
		return (FatherType) session.get(FatherType.class, fathertypeId);
	}
	

}

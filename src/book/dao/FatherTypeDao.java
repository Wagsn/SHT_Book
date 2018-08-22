package book.dao;

import java.util.List;
import java.util.Set;

import book.entity.FatherType;
import book.entity.Type;

public interface FatherTypeDao {

	//主页显示书的大分类,所有
	public List<FatherType> findAll();
	
	/*
	 * 通过id查询FatherType
	 * 通过FatherType对象里面的Set<Type>来获取相大分类下对应的小分类
	 */
	public FatherType findByFathertypeId(int fathertypeId);
}

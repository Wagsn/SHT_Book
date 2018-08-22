package book.dao;
import book.entity.Type;

public interface TypeDao {

	/*
	 * 通过id查询Type
	 * 通过Type对象下Set<Book>来获取对应小模块下所有的书籍信息
	 */
	public Type findByTypeId(Integer typeId);
	
}

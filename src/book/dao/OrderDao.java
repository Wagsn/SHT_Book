package book.dao;

import java.util.List;

import book.entity.Order;

public interface OrderDao {

	//查找订单
	public Order findById(Integer orderId);
	
	//卖家查找自己卖出商品的订单
	public List<Order> findBySellerid(Integer sellerid);
	
	//买家查找自己买过商品的订单
	public List<Order> findByBuyerid(Integer buyerid);
}

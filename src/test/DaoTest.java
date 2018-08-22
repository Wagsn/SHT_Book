/**
 * @file DaoTest.java
 * @package test
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import sale.dao.ChatDaoImp;

/**
 * @class DaoTest
 * @package test
 * @project SHT_Book
 * @author Wagsn
 * @date 2018年8月14日
 * @version 1.0.0
 */
public class DaoTest {
	public static void main(String[] args) {
		System.out.println(JSONArray.toJSONString(new ChatDaoImp().findAllById(1, 2)));
	}
}

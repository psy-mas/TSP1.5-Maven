package edu.scut.emos.tsp.dao.mysql.format;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.scut.emos.tsp.model.DOrder;

/**
 * 完成MySQL DAO层适配工作
 * 
 * @author emos
 */
public class MySQLDaoAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(MySQLDaoAdapter.class);
	
	// 维护一个单例事务对象
	private static MySQLTrans mySQLTrans = new MySQLTrans();
	
	/**
	 * 根据一批orderid查询t_order表中一批条目
	 * 
	 * @param orderids
	 * @return
	 */
	public static List<DOrder> dOrderSelectAllByOrderids(List<String> orderids) {
		return mySQLTrans.dOrderSelectAllByOrderids(orderids);
	}
}

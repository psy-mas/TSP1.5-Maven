package edu.scut.emos.tsp.dao.mysql.format;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.Position;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.time_windows.CommitResult;

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
		return mySQLTrans.dOrderSelectByOrderids(orderids);
	}
	
	/**
	 * 存储TimeWindows方法的结果
	 * 
	 * @param cr
	 * @return
	 */
	public static boolean storeCommitResult(CommitResult cr) {
		return mySQLTrans.transCommitResult(cr);
	}
	
	public static List<Vehicle> getVehicle(Position orderPosition, String type) {
		double orderLongtitude = orderPosition.getLongitude();
		double orderLatitude = orderPosition.getLatitude();
		
		switch (type) {
		case "square" :
			double minLongtitude, maxLongtitude, minLatitude, maxLatitude;
			
			break;
		case "circle" :
			break;
		default :
			break;
		}
	}
}

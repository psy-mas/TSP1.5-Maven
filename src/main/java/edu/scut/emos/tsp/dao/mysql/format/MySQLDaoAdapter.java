package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.DRoute;
import edu.scut.emos.tsp.model.DVehicle;
import edu.scut.emos.tsp.model.Position;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.time_windows.CommitResult;
import edu.scut.emos.tsp.utils.PropertiesUtil;

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
	 * 获取订单
	 * 
	 * @param orderids
	 * @return
	 */
	public static List<DOrder> getOrders(List<String> orderids) {
		return mySQLTrans.dOrderSelectByOrderids(orderids);
	}
	
	/**
	 * 获取车辆
	 * 
	 * @param orderPosition
	 * @param type
	 * @return
	 */
	public static List<Vehicle> getVehicles(Position orderPosition, String type) {
		double orderLongitude = orderPosition.getLongitude();
		double orderLatitude = orderPosition.getLatitude();
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		switch (type) {
		case "square" :
			double minLongitude, maxLongitude, minLatitude, maxLatitude;
			double temp;
			temp = orderLongitude - (PropertiesUtil.vehicleScope() / (111.0 * Math.cos(orderLatitude)));
			minLongitude = temp > 0 ? temp : 0;
			temp = orderLongitude + (PropertiesUtil.vehicleScope() / (111.0 * Math.cos(orderLatitude)));
			maxLongitude = temp < 180 ? temp : 180;
			temp = orderLatitude - (PropertiesUtil.vehicleScope() / (111.0));
			minLatitude = temp > 0 ? temp : 0;
			temp = orderLatitude + (PropertiesUtil.vehicleScope() / (111.0));
			maxLatitude = temp < 90 ? temp : 90;
			// 获取一批DVehicle
			List<DVehicle> dVehicles = mySQLTrans.dVehicleSelectBySquareScope(minLongitude, maxLongitude, minLatitude, maxLatitude);
			// 构造Vehicle
			Vehicle vehicle;
			for(DVehicle dVehicle : dVehicles) {
				List<DRoute> dRoutes = mySQLTrans.dRouteSelectByVehicleid(dVehicle.getVehicleid());
				vehicle = _2Vehicle.a(dVehicle, dRoutes);
				vehicles.add(vehicle);
			}
			break;
		case "circle" :
			// TODO circle方法待完成
			break;
		default :
			break;
		}
		
		return vehicles;
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
}

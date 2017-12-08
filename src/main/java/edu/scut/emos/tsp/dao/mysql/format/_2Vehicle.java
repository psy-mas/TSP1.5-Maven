package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.DRoute;
import edu.scut.emos.tsp.model.DVehicle;
import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Position;
import edu.scut.emos.tsp.model.Route;
import edu.scut.emos.tsp.model.ScheduleTask;
import edu.scut.emos.tsp.model.Vehicle;

/**
 * @author emos
 */
public class _2Vehicle {

	public static Vehicle a(DVehicle dVehicle, List<DRoute> dRoutes) {
		LinkedList<ScheduleTask> loadedTasks = new LinkedList<ScheduleTask>();
		LinkedList<ScheduleTask> planTasks = new LinkedList<ScheduleTask>();

		Map<Integer, DRoute> loadeddRoutesmap = new TreeMap<Integer, DRoute>();
		Map<Integer, DRoute> plandRoutesmap = new TreeMap<Integer, DRoute>();
		List<String> dRouteorderids = new ArrayList<String>();

		// 以isloaded分类
		for (DRoute dRoute : dRoutes) {
			dRouteorderids.add(dRoute.getRouteorderid());
			if (dRoute.getIsloaded().intValue() == 1) {
				loadeddRoutesmap.put(dRoute.getSequence(), dRoute);
			} else {
				plandRoutesmap.put(dRoute.getSequence(), dRoute);
			}
		}

		// 查询order
		Map<String, Order> dOrdermap = new HashMap<String, Order>();
		List<DOrder> dOrders = MySQLDaoAdapter.getOrders(dRouteorderids);
		if (dOrders == null) {
			// 查询不到order数据
		}
		for (DOrder dOrder : dOrders) {
			dOrdermap.put(dOrder.getOrderid(), _2Order.a(dOrder));
		}

		// map转换
		Iterator<Entry<Integer, DRoute>> loadeddRoutesmapiterator = loadeddRoutesmap.entrySet().iterator();
		while (loadeddRoutesmapiterator.hasNext()) {
			Map.Entry<Integer, DRoute> entry = loadeddRoutesmapiterator.next();
			loadedTasks.add(new ScheduleTask(dOrdermap.get(entry.getValue().getRouteorderid()),
					entry.getValue().getAction().intValue()));
		}
		Iterator<Entry<Integer, DRoute>> plandRoutesmapiterator = plandRoutesmap.entrySet().iterator();
		while (plandRoutesmapiterator.hasNext()) {
			Map.Entry<Integer, DRoute> entry = plandRoutesmapiterator.next();
			planTasks.add(new ScheduleTask(dOrdermap.get(entry.getValue().getRouteorderid()),
					entry.getValue().getAction().intValue()));
		}

		// 创建Route对象
		Route route = new Route();
		route.setLoadedTasks(loadedTasks);
		route.setPlanTasks(planTasks);

		// 创建Vehicle对象
		return new Vehicle(dVehicle.getVehicleid(), dVehicle.getLength(), dVehicle.getWidth(), dVehicle.getHeight(),
				dVehicle.getTypeofvehicle(), dVehicle.getMaxweight(), dVehicle.getMaxvolume(),
				new Position(dVehicle.getLatestlatitude(), dVehicle.getLatestlongitude()), dVehicle.getGpsuploadtime(),
				dVehicle.getFuelconsumption(), dVehicle.getLastupdatetime(), dVehicle.getLoadedweight(),
				dVehicle.getLoadedvolume(), ((dVehicle.getIslocked().intValue() == 1) ? true : false), route);
	}
}

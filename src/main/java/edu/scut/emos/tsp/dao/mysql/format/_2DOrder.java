package edu.scut.emos.tsp.dao.mysql.format;

import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.Order;

/**
 * @author emos
 */
public class _2DOrder {

	public static DOrder a(Order order) {
		DOrder dorder = new DOrder();
		dorder.setOrderid(order.getId());
		dorder.setPickuplongitude(order.getPickup().getLongitude());
		dorder.setPickuplatitude(order.getPickup().getLatitude());
		dorder.setDeliverylongitude(order.getDelivery().getLongitude());
		dorder.setDeliverylatitude(order.getDelivery().getLatitude());
		dorder.setTypeofgoods(order.getTypeOfGoods());
		dorder.setWeightofgoods(order.getWeightOfGoods());
		dorder.setVolumeofgoods(order.getVolumeOfGoods());
		dorder.setEarlytimeofpickup(order.getEarlyTimeOfPickup());
		dorder.setEarlytimeofdelivery(order.getEarlyTimeOfDelivery());
		dorder.setLasttimeofpickup(order.getLatestTimeOfPickup());
		dorder.setLasttimeofdelivery(order.getEarlyTimeOfDelivery());
		dorder.setPickupservicetime(order.getPickupServiceTime());
		dorder.setDeliveryservicetime(order.getDeliveryServiceTime());
		dorder.setServiceresult(order.getServiceResult());
		dorder.setCommendvehicle(order.getCommendVehicle());
		dorder.setFactualvehicle(order.getFactualVehicle());
		
		return dorder;
	}
}

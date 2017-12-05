package edu.scut.emos.tsp.dao.mysql.format;

import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Position;

/**
 * @author emos
 */
public class _2Order {

	public static Order a(DOrder dOrder) {

		return new Order(dOrder.getOrderid(), new Position(dOrder.getPickuplatitude(), dOrder.getPickuplongitude()),
				new Position(dOrder.getDeliverylatitude(), dOrder.getDeliverylongitude()), dOrder.getTypeofgoods(),
				dOrder.getWeightofgoods(), dOrder.getVolumeofgoods(), dOrder.getEarlytimeofpickup(),
				dOrder.getLasttimeofpickup(), dOrder.getEarlytimeofdelivery(), dOrder.getLasttimeofdelivery(),
				dOrder.getPickupservicetime(), dOrder.getDeliveryservicetime(), dOrder.getServiceresult(),
				dOrder.getCommendvehicle(), dOrder.getFactualvehicle());
	}
}

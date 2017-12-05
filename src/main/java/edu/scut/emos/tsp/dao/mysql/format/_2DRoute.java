package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.model.DRoute;
import edu.scut.emos.tsp.model.Route;
import edu.scut.emos.tsp.model.ScheduleTask;
import edu.scut.emos.tsp.model.Vehicle;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * @author emos
 */
public class _2DRoute {

	public static List<DRoute> a(Vehicle vehicle) {
		List<DRoute> droutes = new ArrayList<DRoute>();
		DRoute droute;
		
		Route route = vehicle.getRoute();
		
		int i = 0;
		for(ScheduleTask st : route.getLoadedTasks()) {
			i++;
			droute = new DRoute();
			droute.setRouteid(UUIDGenerator.generate());
			droute.setVehicleid(vehicle.getId());
			droute.setRouteorderid(st.getOrder().getId());
			// action need to be in -128 ~ 127
			droute.setAction((byte) st.getAction());
			droute.setIsloaded((byte) 1);
			droute.setSequence(i);
			droutes.add(droute);
		}
		
		i = 0;
		for(ScheduleTask st : route.getPlanTasks()) {
			i++;
			droute = new DRoute();
			droute.setRouteid(UUIDGenerator.generate());
			droute.setVehicleid(vehicle.getId());
			droute.setRouteorderid(st.getOrder().getId());
			// action need to be in -128 ~ 127
			droute.setAction((byte) st.getAction());
			droute.setIsloaded((byte) 0);
			droute.setSequence(i);
			droutes.add(droute);
		}
		
		return droutes;
	}
}

package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.TimeWindows.CacheResult;
import edu.scut.emos.tsp.model.DWindowCacheResultRoute;
import edu.scut.emos.tsp.model.ScheduleTask;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * @author emos
 */
public class _2DWindowCacheResultRoute {

	public static List<DWindowCacheResultRoute> a(CacheResult cr) {
		List<DWindowCacheResultRoute> dwcrrs = new ArrayList<DWindowCacheResultRoute>();
		DWindowCacheResultRoute dwcrr;
		
		int i = 0;
		for(ScheduleTask st : cr.getRecommendRoute().getLoadedTasks()) {
			i++;
			dwcrr = new DWindowCacheResultRoute();
			dwcrr.setCacheresultroutesid(UUIDGenerator.generate());
			dwcrr.setVehicleid(cr.getVehicleId());
			dwcrr.setRouteorderid(st.getOrder().getId());
			// action need to be in -128 ~ 127
			dwcrr.setAction((byte) st.getAction());
			dwcrr.setIsloaded((byte) 1);
			dwcrr.setSequence(i);
			dwcrrs.add(dwcrr);
		}
		
		i = 0;
		for(ScheduleTask st : cr.getRecommendRoute().getPlanTasks()) {
			i++;
			dwcrr = new DWindowCacheResultRoute();
			dwcrr.setCacheresultroutesid(UUIDGenerator.generate());
			dwcrr.setVehicleid(cr.getVehicleId());
			dwcrr.setRouteorderid(st.getOrder().getId());
			// action need to be in -128 ~ 127
			dwcrr.setAction((byte) st.getAction());
			dwcrr.setIsloaded((byte) 0);
			dwcrr.setSequence(i);
			dwcrrs.add(dwcrr);
		}
		
		return dwcrrs;
		
	}
}

package edu.scut.emos.tsp.dao.mysql.format;
/**
 * @author emos
 */

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.model.DWindowCacheResultOrder;
import edu.scut.emos.tsp.time_windows.CacheResult;
import edu.scut.emos.tsp.utils.UUIDGenerator;

public class _2DWindowCacheResultOrder {

	public static List<DWindowCacheResultOrder> a(CacheResult cr) {
		List<DWindowCacheResultOrder> dwcros = new ArrayList<DWindowCacheResultOrder>();
		DWindowCacheResultOrder dwcro;
		
		for(String s  : cr.getOrderIds()) {
			dwcro = new DWindowCacheResultOrder();
			dwcro.setCacheresultordersid(UUIDGenerator.generate());
			dwcro.setVehicleid(cr.getVehicleId());
			dwcro.setOrderid(s);
			dwcros.add(dwcro);
		}
		
		return dwcros;
	}
}

package edu.scut.emos.tsp.dao.mysql.format;
/**
 * @author emos
 */

import edu.scut.emos.tsp.model.DWindowCacheResult;
import edu.scut.emos.tsp.time_windows.CacheResult;
import edu.scut.emos.tsp.utils.UUIDGenerator;

public class _2DWindowCacheResult {

	public static DWindowCacheResult a(CacheResult cr) {
		DWindowCacheResult dwcr = new DWindowCacheResult();
		dwcr.setCacheresultid(UUIDGenerator.generate());
		dwcr.setVehicleid(cr.getVehicleId());
		dwcr.setLastupdatetime(cr.getLastUpdateTime());
		
		return dwcr;
	}
	
	public static DWindowCacheResult b(String uuid, CacheResult cr) {
		DWindowCacheResult dwcr = new DWindowCacheResult();
		dwcr.setCacheresultid(uuid);
		dwcr.setVehicleid(cr.getVehicleId());
		dwcr.setLastupdatetime(cr.getLastUpdateTime());
		
		return dwcr;
	}
}

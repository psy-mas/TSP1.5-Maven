package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.model.DWindowCommitCacheResult;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * @author emos
 */
public class _2DWindowCommitCacheResult {

	public static List<DWindowCommitCacheResult> a(String commitresultid, List<String> cacheresultids, int isvalid) {
		List<DWindowCommitCacheResult> dwccrs = new ArrayList<DWindowCommitCacheResult>();
		DWindowCommitCacheResult dwccr;
		
		for(String cacheresultid : cacheresultids) {
			dwccr = new DWindowCommitCacheResult();
			dwccr.setCommitcacheresultid(UUIDGenerator.generate());
			dwccr.setCommitresultid(commitresultid);
			dwccr.setCacheresultid(cacheresultid);
			dwccr.setIsvalid((byte) isvalid);
			dwccrs.add(dwccr);
		}
		
		return dwccrs;
	}
}

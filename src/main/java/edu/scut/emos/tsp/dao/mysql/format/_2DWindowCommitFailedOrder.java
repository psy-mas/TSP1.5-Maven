package edu.scut.emos.tsp.dao.mysql.format;
/**
 * @author emos
 */

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.model.DWindowCommitFailedOrder;
import edu.scut.emos.tsp.time_windows.CommitResult;
import edu.scut.emos.tsp.time_windows.FailedOrder;
import edu.scut.emos.tsp.utils.UUIDGenerator;

public class _2DWindowCommitFailedOrder {
	
	public static List<DWindowCommitFailedOrder> a(CommitResult cr, String crid) {
		List<DWindowCommitFailedOrder> dwcfos = new ArrayList<DWindowCommitFailedOrder>();
		DWindowCommitFailedOrder dwcfo;
		
		for(FailedOrder fo : cr.getFailedOrderIDs()) {
			dwcfo = new DWindowCommitFailedOrder();
			dwcfo.setCommitfailedorderid(UUIDGenerator.generate());
			dwcfo.setCommitresultid(crid);
			dwcfo.setFailedorderid(fo.getOrderId());
			dwcfo.setType(fo.getStatus());
			dwcfos.add(dwcfo);
		}
		
		return dwcfos;
	}
}

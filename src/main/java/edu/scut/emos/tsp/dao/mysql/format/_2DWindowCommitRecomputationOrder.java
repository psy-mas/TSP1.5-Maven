package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.List;

import edu.scut.emos.tsp.TimeWindows.CommitResult;
import edu.scut.emos.tsp.model.DWindowCommitRecomputationOrder;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * @author emos
 */
public class _2DWindowCommitRecomputationOrder {

	public static List<DWindowCommitRecomputationOrder> a(CommitResult cr, String crid) {
		List<DWindowCommitRecomputationOrder> dwcros = new ArrayList<DWindowCommitRecomputationOrder>();
		DWindowCommitRecomputationOrder dwcro;
		
		for(String s : cr.getRecomputationOrderIDs()) {
			dwcro = new DWindowCommitRecomputationOrder();
			dwcro.setCommitrecomputationorderid(UUIDGenerator.generate());
			dwcro.setCommitresultid(crid);
			dwcro.setRecomputationorderid(s);
			dwcros.add(dwcro);
		}
		
		return dwcros;
	}
}

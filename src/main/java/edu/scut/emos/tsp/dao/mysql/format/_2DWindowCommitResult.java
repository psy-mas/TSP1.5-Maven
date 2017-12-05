package edu.scut.emos.tsp.dao.mysql.format;

import java.util.Date;

import edu.scut.emos.tsp.model.DWindowCommitResult;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * @author emos
 */
public class _2DWindowCommitResult {

	public static DWindowCommitResult a(Date date) {
		DWindowCommitResult dwcr = new DWindowCommitResult();
		dwcr.setCommitresultid(UUIDGenerator.generate());
		dwcr.setCommittime(date);
		
		return dwcr;
	}
}

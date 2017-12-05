package edu.scut.emos.tsp.dao.mysql.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.scut.emos.tsp.TimeWindows.CacheResult;
import edu.scut.emos.tsp.TimeWindows.CommitResult;
import edu.scut.emos.tsp.dao.mysql.DOrderMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCacheResultMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCacheResultOrderMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCacheResultRouteMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCommitCacheResultMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCommitRecomputationOrderMapper;
import edu.scut.emos.tsp.dao.mysql.DWindowCommitResultMapper;
import edu.scut.emos.tsp.model.DOrder;
import edu.scut.emos.tsp.model.DWindowCacheResult;
import edu.scut.emos.tsp.model.DWindowCacheResultOrder;
import edu.scut.emos.tsp.model.DWindowCacheResultRoute;
import edu.scut.emos.tsp.model.DWindowCommitCacheResult;
import edu.scut.emos.tsp.model.DWindowCommitRecomputationOrder;
import edu.scut.emos.tsp.model.DWindowCommitResult;
import edu.scut.emos.tsp.utils.MybatisSqlSession;
import edu.scut.emos.tsp.utils.UUIDGenerator;

/**
 * 完成MySQL 事务操作
 * 
 * @author emos
 */
public class MySQLTrans {
	private static final Logger LOG = LoggerFactory.getLogger(MySQLTrans.class);

	/**
	 * 事务存储TimeWindows方法的结果
	 * 
	 * @param commitResult
	 * @return
	 */
	public boolean transCommitResult(CommitResult commitResult) {
		boolean flag = true;
		SqlSession sqlSession = null;
		try {
			// 事务存储TimeWindows方法的结果
			sqlSession = MybatisSqlSession.openSessionManually();
			
			// DWindowCommitResult
			DWindowCommitResult dWindowCommitResult = _2DWindowCommitResult.a(new Date());
			DWindowCommitResultMapper dWindowCommitResultMapper = sqlSession.getMapper(DWindowCommitResultMapper.class);
			dWindowCommitResultMapper.insert(dWindowCommitResult);
			
			// 为cacheresult生成UUID，并进行绑定
			Map<String, CacheResult> cacheresultmap = new HashMap<String, CacheResult>();
			List<String> cacheresultids = new ArrayList<String>();
			for(CacheResult cacheResult : commitResult.getCacheResults()) {
				String uuid = UUIDGenerator.generate();
				cacheresultids.add(uuid);
				cacheresultmap.put(uuid, cacheResult);
			}
			
			// DWindowCommitCacheResult 默认有效状态为0
			List<DWindowCommitCacheResult> dWindowCommitCacheResults = _2DWindowCommitCacheResult.a(dWindowCommitResult.getCommitresultid(), cacheresultids, 0);
			DWindowCommitCacheResultMapper dWindowCommitCacheResultMapper = sqlSession.getMapper(DWindowCommitCacheResultMapper.class);
			dWindowCommitCacheResultMapper.insertAll(dWindowCommitCacheResults);
			
			// DWindowCommitRecomputationOrder
			List<DWindowCommitRecomputationOrder> dWindowCommitRecomputationOrders = _2DWindowCommitRecomputationOrder.a(commitResult, dWindowCommitResult.getCommitresultid());
			DWindowCommitRecomputationOrderMapper dWindowCommitRecomputationOrderMapper = sqlSession.getMapper(DWindowCommitRecomputationOrderMapper.class);
			dWindowCommitRecomputationOrderMapper.insertAll(dWindowCommitRecomputationOrders);
			
			// DWindowCacheResult
			List<DWindowCacheResult> dWindowCacheResults = new ArrayList<DWindowCacheResult>();
			DWindowCacheResult dWindowCacheResult;
			for(Map.Entry<String, CacheResult> entry : cacheresultmap.entrySet()) {
				dWindowCacheResult = _2DWindowCacheResult.b(entry.getKey(), entry.getValue());
				dWindowCacheResults.add(dWindowCacheResult);
			}
			DWindowCacheResultMapper dWindowCacheResultMapper = sqlSession.getMapper(DWindowCacheResultMapper.class);
			dWindowCacheResultMapper.insertAll(dWindowCacheResults);
			
			// DWindowCacheResultOrder
			List<DWindowCacheResultOrder> dWindowCacheResultOrders = new ArrayList<DWindowCacheResultOrder>();
			for(Map.Entry<String, CacheResult> entry : cacheresultmap.entrySet()) {
				dWindowCacheResultOrders.addAll(_2DWindowCacheResultOrder.a(entry.getValue()));
			}
			DWindowCacheResultOrderMapper dWindowCacheResultOrderMapper = sqlSession.getMapper(DWindowCacheResultOrderMapper.class);
			dWindowCacheResultOrderMapper.insertAll(dWindowCacheResultOrders);
			
			// DWindowCacheResultRoute
			List<DWindowCacheResultRoute> dWindowCacheResultRoutes = new ArrayList<DWindowCacheResultRoute>();
			for(Map.Entry<String, CacheResult> entry : cacheresultmap.entrySet()) {
				dWindowCacheResultRoutes.addAll(_2DWindowCacheResultRoute.a(entry.getValue()));
			}
			DWindowCacheResultRouteMapper dWindowCacheResultRouteMapper = sqlSession.getMapper(DWindowCacheResultRouteMapper.class);
			dWindowCacheResultRouteMapper.insertAll(dWindowCacheResultRoutes);
			
			sqlSession.commit();
		} catch (Exception e) {
			// 出错回滚
			flag = false;
			LOG.error("MySQL Transaction Error", e);
			if(sqlSession != null)
				sqlSession.rollback();
		}
		finally {
			if(sqlSession != null)
				sqlSession.close();
		}
		return flag;
	}
	
	/**
	 * 根据orderid查询t_order表中条目
	 * 
	 * @param orderid
	 * @return
	 */
	public DOrder dOrderSelectByPrimaryKey(String orderid) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisSqlSession.openSession();
			DOrderMapper dOrderMapper = sqlSession.getMapper(DOrderMapper.class);
			return dOrderMapper.selectByPrimaryKey(orderid);
		}finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	/**
	 * 根据一批orderid查询t_order表中一批条目
	 * 
	 * @param orderids
	 * @return
	 */
	public List<DOrder> dOrderSelectAllByOrderids(List<String> orderids) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisSqlSession.openSession();
			DOrderMapper dOrderMapper = sqlSession.getMapper(DOrderMapper.class);
			return dOrderMapper.selectAllByOrderids(orderids);
		}finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
}

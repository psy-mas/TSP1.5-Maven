package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCommitRecomputationOrder;

/**
 * @author emos
 *
 */
public interface DWindowCommitRecomputationOrderMapper {
    int deleteByPrimaryKey(String commitrecomputationorderid);

    int insert(DWindowCommitRecomputationOrder record);

    int insertSelective(DWindowCommitRecomputationOrder record);

    DWindowCommitRecomputationOrder selectByPrimaryKey(String commitrecomputationorderid);

    int updateByPrimaryKeySelective(DWindowCommitRecomputationOrder record);

    int updateByPrimaryKey(DWindowCommitRecomputationOrder record);
    
    // 添加方法
    
    int insertAll(List<DWindowCommitRecomputationOrder> records);
}
package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCommitRecomputationOrder;

public interface DWindowCommitRecomputationOrderMapper {
    int deleteByPrimaryKey(String commitrecomputationorderid);

    int insert(DWindowCommitRecomputationOrder record);

    int insertSelective(DWindowCommitRecomputationOrder record);

    DWindowCommitRecomputationOrder selectByPrimaryKey(String commitrecomputationorderid);

    int updateByPrimaryKeySelective(DWindowCommitRecomputationOrder record);

    int updateByPrimaryKey(DWindowCommitRecomputationOrder record);
}
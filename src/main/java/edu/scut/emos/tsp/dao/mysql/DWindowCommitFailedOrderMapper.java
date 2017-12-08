package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCommitFailedOrder;

public interface DWindowCommitFailedOrderMapper {
    int deleteByPrimaryKey(String commitfailedorderid);

    int insert(DWindowCommitFailedOrder record);

    int insertSelective(DWindowCommitFailedOrder record);

    DWindowCommitFailedOrder selectByPrimaryKey(String commitfailedorderid);

    int updateByPrimaryKeySelective(DWindowCommitFailedOrder record);

    int updateByPrimaryKey(DWindowCommitFailedOrder record);
}
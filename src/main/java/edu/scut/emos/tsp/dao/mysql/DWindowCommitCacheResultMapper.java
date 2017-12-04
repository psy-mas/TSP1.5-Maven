package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCommitCacheResult;

public interface DWindowCommitCacheResultMapper {
    int deleteByPrimaryKey(String commitcacheresultid);

    int insert(DWindowCommitCacheResult record);

    int insertSelective(DWindowCommitCacheResult record);

    DWindowCommitCacheResult selectByPrimaryKey(String commitcacheresultid);

    int updateByPrimaryKeySelective(DWindowCommitCacheResult record);

    int updateByPrimaryKey(DWindowCommitCacheResult record);
}
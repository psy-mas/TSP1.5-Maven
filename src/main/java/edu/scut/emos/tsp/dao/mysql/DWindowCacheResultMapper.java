package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCacheResult;

public interface DWindowCacheResultMapper {
    int deleteByPrimaryKey(String cacheresultid);

    int insert(DWindowCacheResult record);

    int insertSelective(DWindowCacheResult record);

    DWindowCacheResult selectByPrimaryKey(String cacheresultid);

    int updateByPrimaryKeySelective(DWindowCacheResult record);

    int updateByPrimaryKey(DWindowCacheResult record);
}
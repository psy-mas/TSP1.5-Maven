package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCacheResultOrder;

public interface DWindowCacheResultOrderMapper {
    int deleteByPrimaryKey(String cacheresultordersid);

    int insert(DWindowCacheResultOrder record);

    int insertSelective(DWindowCacheResultOrder record);

    DWindowCacheResultOrder selectByPrimaryKey(String cacheresultordersid);

    int updateByPrimaryKeySelective(DWindowCacheResultOrder record);

    int updateByPrimaryKey(DWindowCacheResultOrder record);
}
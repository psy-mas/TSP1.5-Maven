package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCacheResultRoute;

public interface DWindowCacheResultRouteMapper {
    int deleteByPrimaryKey(String cacheresultroutesid);

    int insert(DWindowCacheResultRoute record);

    int insertSelective(DWindowCacheResultRoute record);

    DWindowCacheResultRoute selectByPrimaryKey(String cacheresultroutesid);

    int updateByPrimaryKeySelective(DWindowCacheResultRoute record);

    int updateByPrimaryKey(DWindowCacheResultRoute record);
}
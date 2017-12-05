package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCacheResultRoute;

/**
 * @author emos
 *
 */
public interface DWindowCacheResultRouteMapper {
    int deleteByPrimaryKey(String cacheresultroutesid);

    int insert(DWindowCacheResultRoute record);

    int insertSelective(DWindowCacheResultRoute record);

    DWindowCacheResultRoute selectByPrimaryKey(String cacheresultroutesid);

    int updateByPrimaryKeySelective(DWindowCacheResultRoute record);

    int updateByPrimaryKey(DWindowCacheResultRoute record);
    
    // 添加方法
    
    int insertAll(List<DWindowCacheResultRoute> records);
}
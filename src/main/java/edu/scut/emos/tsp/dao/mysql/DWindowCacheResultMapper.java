package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCacheResult;

/**
 * @author emos
 *
 */
public interface DWindowCacheResultMapper {
    int deleteByPrimaryKey(String cacheresultid);

    int insert(DWindowCacheResult record);

    int insertSelective(DWindowCacheResult record);

    DWindowCacheResult selectByPrimaryKey(String cacheresultid);

    int updateByPrimaryKeySelective(DWindowCacheResult record);

    int updateByPrimaryKey(DWindowCacheResult record);
    
    // 添加方法
    
    int insertAll(List<DWindowCacheResult> records);
}
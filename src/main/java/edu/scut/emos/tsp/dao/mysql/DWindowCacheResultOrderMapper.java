package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCacheResultOrder;

/**
 * @author emos
 *
 */
public interface DWindowCacheResultOrderMapper {
    int deleteByPrimaryKey(String cacheresultordersid);

    int insert(DWindowCacheResultOrder record);

    int insertSelective(DWindowCacheResultOrder record);

    DWindowCacheResultOrder selectByPrimaryKey(String cacheresultordersid);

    int updateByPrimaryKeySelective(DWindowCacheResultOrder record);

    int updateByPrimaryKey(DWindowCacheResultOrder record);
    
    // 添加方法
    
    int insertAll(List<DWindowCacheResultOrder> records);
}
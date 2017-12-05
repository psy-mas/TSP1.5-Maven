package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCommitCacheResult;

/**
 * @author emos
 *
 */
public interface DWindowCommitCacheResultMapper {
    int deleteByPrimaryKey(String commitcacheresultid);

    int insert(DWindowCommitCacheResult record);

    int insertSelective(DWindowCommitCacheResult record);

    DWindowCommitCacheResult selectByPrimaryKey(String commitcacheresultid);

    int updateByPrimaryKeySelective(DWindowCommitCacheResult record);

    int updateByPrimaryKey(DWindowCommitCacheResult record);
    
    // 添加方法
    
    int insertAll(List<DWindowCommitCacheResult> records);
}
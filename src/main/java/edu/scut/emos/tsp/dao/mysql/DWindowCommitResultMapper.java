package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DWindowCommitResult;

/**
 * @author emos
 *
 */
public interface DWindowCommitResultMapper {
    int deleteByPrimaryKey(String commitresultid);

    int insert(DWindowCommitResult record);

    int insertSelective(DWindowCommitResult record);

    DWindowCommitResult selectByPrimaryKey(String commitresultid);

    int updateByPrimaryKeySelective(DWindowCommitResult record);

    int updateByPrimaryKey(DWindowCommitResult record);
    
    // 添加方法
    
    int insertAll(List<DWindowCommitResult> records);
}
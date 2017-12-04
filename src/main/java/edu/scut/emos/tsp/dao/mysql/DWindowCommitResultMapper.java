package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DWindowCommitResult;

public interface DWindowCommitResultMapper {
    int deleteByPrimaryKey(String commitresultid);

    int insert(DWindowCommitResult record);

    int insertSelective(DWindowCommitResult record);

    DWindowCommitResult selectByPrimaryKey(String commitresultid);

    int updateByPrimaryKeySelective(DWindowCommitResult record);

    int updateByPrimaryKey(DWindowCommitResult record);
}
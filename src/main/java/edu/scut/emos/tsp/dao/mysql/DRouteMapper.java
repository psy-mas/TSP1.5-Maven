package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DRoute;

public interface DRouteMapper {
    int deleteByPrimaryKey(String routeid);

    int insert(DRoute record);

    int insertSelective(DRoute record);

    DRoute selectByPrimaryKey(String routeid);

    int updateByPrimaryKeySelective(DRoute record);

    int updateByPrimaryKey(DRoute record);
}
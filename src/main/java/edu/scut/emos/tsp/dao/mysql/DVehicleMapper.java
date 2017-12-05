package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DVehicle;

public interface DVehicleMapper {
    int deleteByPrimaryKey(String vehicleid);

    int insert(DVehicle record);

    int insertSelective(DVehicle record);

    DVehicle selectByPrimaryKey(String vehicleid);

    int updateByPrimaryKeySelective(DVehicle record);

    int updateByPrimaryKey(DVehicle record);
}
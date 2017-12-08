package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.scut.emos.tsp.model.DVehicle;

public interface DVehicleMapper {
    int deleteByPrimaryKey(String vehicleid);

    int insert(DVehicle record);

    int insertSelective(DVehicle record);

    DVehicle selectByPrimaryKey(String vehicleid);

    int updateByPrimaryKeySelective(DVehicle record);

    int updateByPrimaryKey(DVehicle record);
    
    // 添加方法
    
    List<DVehicle> selectBySquareScope(@Param("minlongitude")Double minlongitude, @Param("maxlongitude")Double maxlongitude, @Param("minlatitude")Double minlatitude, @Param("maxlatitude")Double maxlatitude);
}
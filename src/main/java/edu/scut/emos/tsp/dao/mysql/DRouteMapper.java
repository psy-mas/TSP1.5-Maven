package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DRoute;

/**
 * @author emos
 *
 */
public interface DRouteMapper {
    int deleteByPrimaryKey(String routeid);

    int insert(DRoute record);

    int insertSelective(DRoute record);

    DRoute selectByPrimaryKey(String routeid);

    int updateByPrimaryKeySelective(DRoute record);

    int updateByPrimaryKey(DRoute record);
    
    // 添加方法
    
    List<DRoute> selectByVehicleid(String vehicleid);
}
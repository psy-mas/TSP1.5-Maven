package edu.scut.emos.tsp.dao.mysql;

import java.util.List;

import edu.scut.emos.tsp.model.DOrder;

/**
 * @author emos
 *
 */
public interface DOrderMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(DOrder record);

    int insertSelective(DOrder record);

    DOrder selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(DOrder record);

    int updateByPrimaryKey(DOrder record);
    
    // 添加方法
    
    List<DOrder> selectAllByOrderids(List<String> orderids);
}
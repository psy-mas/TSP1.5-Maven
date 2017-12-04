package edu.scut.emos.tsp.dao.mysql;

import edu.scut.emos.tsp.model.DOrder;

public interface DOrderMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(DOrder record);

    int insertSelective(DOrder record);

    DOrder selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(DOrder record);

    int updateByPrimaryKey(DOrder record);
}
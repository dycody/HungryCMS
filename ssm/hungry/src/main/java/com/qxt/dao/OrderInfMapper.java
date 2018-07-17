package com.qxt.dao;

import com.qxt.pojo.OrderInf;
import java.util.List;

public interface OrderInfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInf record);

    OrderInf selectByPrimaryKey(Long id);

    List<OrderInf> selectAll();

    int updateByPrimaryKey(OrderInf record);
    
    List<OrderInf> selectByOrderId(Long oId);
}
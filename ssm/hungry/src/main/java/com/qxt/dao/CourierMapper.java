package com.qxt.dao;

import com.qxt.pojo.Courier;
import java.util.List;

public interface CourierMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(Courier record);

    Courier selectByPrimaryKey(Long uId);

    List<Courier> selectAll(Courier record);

    int updateByPrimaryKey(Courier record);
    
    Courier selectBestByRegionId(Integer rId);
}
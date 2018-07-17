package com.qxt.dao;

import com.qxt.pojo.Region;
import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    Region selectByPrimaryKey(Integer id);

    List<Region> selectAll();

    int updateByPrimaryKey(Region record);
}
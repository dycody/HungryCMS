package com.qxt.dao;

import com.qxt.pojo.UserLocation;
import java.util.List;

public interface UserLocationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLocation record);

    UserLocation selectByPrimaryKey(Long id);

    List<UserLocation> selectAll();

    int updateByPrimaryKey(UserLocation record);
}
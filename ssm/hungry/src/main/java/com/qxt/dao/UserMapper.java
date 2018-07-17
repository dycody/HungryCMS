package com.qxt.dao;

import com.qxt.pojo.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(User record);

    User selectByPrimaryKey(Long uId);

    List<User> selectAll(User record);

    int updateByPrimaryKey(User record);
}
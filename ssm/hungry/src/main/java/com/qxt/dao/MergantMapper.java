package com.qxt.dao;

import com.qxt.pojo.Mergant;
import java.util.List;

public interface MergantMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(Mergant record);

    Mergant selectByPrimaryKey(Long uId);

    List<Mergant> selectAll(Mergant record);

    int updateByPrimaryKey(Mergant record);
}
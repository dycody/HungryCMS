package com.qxt.dao;

import com.qxt.pojo.Orderu;
import java.util.List;

public interface OrderuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderu record);

    Orderu selectByPrimaryKey(Long id);

    List<Orderu> selectAll(Orderu record);

    int updateByPrimaryKey(Orderu record);

	void reachByPrimaryKey(Long id);
	
	void deleteorderByPrimaryKey(Long id);
}
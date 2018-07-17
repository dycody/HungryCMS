package com.qxt.dao;

import com.qxt.pojo.Food;
import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Food record);

    Food selectByPrimaryKey(Long id);

    List<Food> selectAll(Food record);

    int updateByPrimaryKey(Food record);
}
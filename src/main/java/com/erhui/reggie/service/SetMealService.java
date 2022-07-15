package com.erhui.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.erhui.reggie.dto.SetmealDto;
import com.erhui.reggie.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author:erhui
 * version:1.0
 **/

public interface SetMealService extends IService<Setmeal> {
    // 新增套餐，同时保存套餐和菜品的关联关系
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}

package com.erhui.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.erhui.reggie.dto.DishDto;
import com.erhui.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * author:erhui
 * version:1.0
 **/

public interface DishService extends IService<Dish> {
    // 新增菜品，同时插入菜品对应的口味数据，需要同时操作两张表：dish,dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，口味信息
    public void updateWithFlavor(DishDto dishDto);
}

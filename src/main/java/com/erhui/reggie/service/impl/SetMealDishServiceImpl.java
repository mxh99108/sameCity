package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.entity.SetmealDish;
import com.erhui.reggie.mapper.SetMealDishMapper;
import com.erhui.reggie.mapper.SetMealMapper;
import com.erhui.reggie.service.SetMealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/
@Service
@Slf4j

public class SetMealDishServiceImpl extends ServiceImpl<SetMealDishMapper, SetmealDish> implements SetMealDishService {
}

package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.entity.DishFlavor;
import com.erhui.reggie.mapper.DishFlavorMapper;
import com.erhui.reggie.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

    @Autowired
    private DishFlavorService dishFlavorService;



}

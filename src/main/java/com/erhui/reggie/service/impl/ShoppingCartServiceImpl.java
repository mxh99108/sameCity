package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.entity.ShoppingCart;
import com.erhui.reggie.mapper.ShoppingCartMapper;
import com.erhui.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}

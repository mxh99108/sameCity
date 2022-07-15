package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.entity.OrderDetail;
import com.erhui.reggie.mapper.OrderDetailMapper;
import com.erhui.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper,OrderDetail> implements OrderDetailService {
}

package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.entity.User;
import com.erhui.reggie.mapper.UserMapper;
import com.erhui.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

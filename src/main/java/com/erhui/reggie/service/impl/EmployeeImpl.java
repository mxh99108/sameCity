package com.erhui.reggie.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erhui.reggie.common.R;
import com.erhui.reggie.entity.Employee;
import com.erhui.reggie.mapper.EmployeeMapper;
import com.erhui.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

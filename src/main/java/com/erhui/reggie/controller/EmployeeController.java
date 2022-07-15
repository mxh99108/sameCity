package com.erhui.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erhui.reggie.common.R;
import com.erhui.reggie.entity.Employee;
import com.erhui.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import javax.xml.crypto.KeySelector;
import java.time.LocalDateTime;

/**
 * author:erhui
 * version:1.0
 **/

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据id查员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        if (emp != null){
            return R.success(emp);
        }
        return R.error("没有查到对应员工信息");
    }

    /**
     * 修改信息
     * @param employee
     * @param request
     * @return
     */
    @PutMapping()
    public R<String> update(@RequestBody Employee employee,HttpServletRequest request){
//        employee.setUpdateTime(LocalDateTime.now());
//        Long emp = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(emp);
        long id = Thread.currentThread().getId();
        log.info("线程ID为 " + id);
        employeeService.updateById(employee);
        return R.success("修改成功");
    }


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(Integer page,Integer pageSize,String name){
        log.info("page = {}, pageSize = {} ,name = {}",page,pageSize,name);
        // 构造器分页
        Page pageInfo = new Page(page,pageSize);
        // 条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        // 添加过滤条件
        queryWrapper.like(StringUtils.hasText(name),Employee::getName,name);
        // 添加排序
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        // 查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }


    /**
     * 新增
     * @param employee
     * @param request
     * @return
     */
    @PostMapping()
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){
        log.info("新增员工信息：" + employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex(("123456").getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
        boolean save = employeeService.save(employee);
        if (save){
            return R.success("新增成功");
        }
        return R.error("新增失败");
    };


    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){

        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("账号不存在");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("账号或密码错误");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        System.out.println(emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}

package com.erhui.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erhui.reggie.common.R;
import com.erhui.reggie.dto.SetmealDto;
import com.erhui.reggie.entity.Category;
import com.erhui.reggie.entity.Setmeal;
import com.erhui.reggie.entity.SetmealDish;
import com.erhui.reggie.service.CategoryService;
import com.erhui.reggie.service.SetMealDishService;
import com.erhui.reggie.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 套餐管理
 * author:erhui
 * version:1.0
 **/

@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Autowired
    private SetMealDishService setMealDishService;

    @Autowired
    private SetMealService setMealService;

    @Autowired
    private CategoryService categoryService;

    @CacheEvict(value = "setmealCache", allEntries = true)
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setMealService.save(setmealDto);
        return R.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, String name) {

        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setMealService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, setmealDtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(list);

        return R.success(setmealDtoPage);
    }

    @PostMapping("/status/{status}")
    public R<String> changeStatus(@PathVariable("status") Integer status, @RequestParam List<String> ids) {
        String id;
        for (int i = 0; i < ids.size(); i++) {
            id = ids.get(i);
            Setmeal setmeal = setMealService.getById(id);
            setmeal.setStatus(status == 0 ? 0 : 1);
            setMealService.updateById(setmeal);
        }
        return R.success("修改成功");
    }


    @CacheEvict(value = "setmealCache", allEntries = true)
    @DeleteMapping()
    public R<String> delete(@RequestParam List<Long> ids) {
        setMealService.removeWithDish(ids);
        return R.success("删除成功");
    }

    @Cacheable(value = "setmealCache", key = "#setmeal.categoryId")
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getStatus, 1);
        List<Setmeal> list = setMealService.list(queryWrapper);

        return R.success(list);
    }
}



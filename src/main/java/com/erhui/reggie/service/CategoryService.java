package com.erhui.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.erhui.reggie.entity.Category;

/**
 * author:erhui
 * version:1.0
 **/
public interface CategoryService extends IService<Category> {

    void remove(Long id);
}

package com.erhui.reggie.dto;

import com.erhui.reggie.entity.Setmeal;

import com.erhui.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

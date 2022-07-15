package com.erhui.reggie.common;

/**
 * 自定义业务异常
 * author:erhui
 * version:1.0
 **/
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}

package com.song.framework.mvc;

import com.song.framework.exception.MyException;
import com.song.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 全局异常处理
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException exception){
        exception.printStackTrace();
        return Result.ok(exception.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handle(MyException exception){
        exception.printStackTrace();
        return Result.ok(exception.getMessage());
    }

}

package com.hqm.rabbit.utils.error;



import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{


    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        System.out.println("-------未知异常----------"+e.getMessage());
        String requestURI = request.getRequestURI();
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(MsgException.class)
    public AjaxResult Exception403(MsgException e, HttpServletRequest request)
    {
        System.out.println("-------未知异常11----------"+e.getMessage());
        String requestURI = request.getRequestURI();
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request)
    {
        System.out.println("-------系统异常----------");
        String requestURI = request.getRequestURI();
        return AjaxResult.error(e.getMessage());
    }

}

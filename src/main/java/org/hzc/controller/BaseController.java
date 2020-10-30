package org.hzc.controller;

import org.hzc.exception.HRSException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
//根据不同错误转向不同页面，即异常与View的对应关系
        if(ex instanceof HRSException) {
            return "error";
        }
        return "error";
}
}
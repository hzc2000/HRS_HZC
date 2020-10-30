package org.hzc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HRSExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        HRSException HRSException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if(ex instanceof HRSException) {
            HRSException = (HRSException) ex;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            //这里我就也有CSMException省事了，实际中应该要再定义一个新的异常
            HRSException  = new IllegalFormatException("定义的新异常");
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", HRSException.getMessage());
        modelAndView.setViewName("/my_error");

        return modelAndView;
    }
}

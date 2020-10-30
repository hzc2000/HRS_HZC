package org.hzc.interceptor;

import lombok.Data;
import org.hzc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import static org.hzc.util.Constants.DEFAULT_SESSION_ATTRIBUTE_NAME_FOR_USER;

@Data
public class AuthenticationInterceptor implements HandlerInterceptor {
    private String userSessionKey = DEFAULT_SESSION_ATTRIBUTE_NAME_FOR_USER;



    private String[] notRequiringLoginUrls ={ "/login","/register"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        // 不需要登陆的页面，检查

        for (String notRequiringLoginUrl : notRequiringLoginUrls) {
            if(url.contains(notRequiringLoginUrl) || url.contains("/css") ||url.contains("/images")  ||url.contains("/js")) {
                // 不需要登陆，返回

                return true;
            }
        }

        User user = (User) request.getSession().getAttribute(userSessionKey);
        if (Objects.isNull(user)) {
            //没有登录, 转到登陆页面
            request.setAttribute("msg", "还没登录，请先登录！");
            request.getRequestDispatcher("/login")
                    .forward(request, response);
            //response.sendRedirect("/login");
            return false;
        }
        return true;
    }}




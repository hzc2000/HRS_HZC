package org.hzc.interceptor;

import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.SecurityService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hzc.util.Constants.DEFAULT_SESSION_ATTRIBUTE_NAME_FOR_USER;

@Data
public class AuthorizationInterceptor implements HandlerInterceptor {
    private String userSessionKey = DEFAULT_SESSION_ATTRIBUTE_NAME_FOR_USER;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, HRSException {
        User user = (User) request.getSession().getAttribute(userSessionKey);
        String action = null;
        String path = request.getServletPath();
        if (null != path) {
            if (path.contains("user")) {
                action = "user";
            }
        }
        if (null == action) { // there is no action
            return true;
        }
        boolean isAuthorized = user != null && securityService.isAuthorized(action, user.getRole());

        if (!isAuthorized) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }
}

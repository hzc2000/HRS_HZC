package org.hzc.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        ServletRegistration.Dynamic customServlet = servletContext.addServlet("customServlet", CustomServlet.class);
//        customServlet.addMapping("/custom/**");
    }
}

package org.hzc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 该类是用来替代web.xml
 */
//public class WebInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
//        ac.register(MvcConfig.class);
//        ac.setServletContext(servletContext);
//        // 初始化此DispatcherServlet必须提供ac，否则就需要配置dispatcherServlet-servlet.xml
//        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(ac));
//        dispatcherServlet.addMapping("/");
//        dispatcherServlet.setLoadOnStartup(1);
//    }
//}

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {BeanConfig.class};
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 配置上传文件相关的选项，至少配置location
        registration.setMultipartConfig(new MultipartConfigElement("/"));
    }



}

package org.hzc.config;


import org.hzc.exception.HRSExceptionResolver;
import org.hzc.interceptor.AuthenticationInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

@Configuration
// 相当于xml的mvc:annotation-driven
@EnableWebMvc
@ComponentScan(value = "org.hzc.controller")
/*public class MvcConfig {
 与web相关的配置
 相当于dispatcherServlet-servlet.xml
 */
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public HRSExceptionResolver exceptionHandler() {
        return new HRSExceptionResolver();
    }




    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setDefaultErrorView("error");
        exceptionResolver.setExceptionAttribute("ex");
        Properties mappings = new Properties();
        mappings.put("org.hzc.exception.IllegalFormatException", "my_error");
        mappings.put("org.hzc.exception.HRSException", "my_error");
        exceptionResolver.setExceptionMappings(mappings);
        return exceptionResolver;
    }
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//         直接添加，会导致系统默认的异常处理器起作用
//         添加到最开始的位置，自定义异常处理才起作用
        resolvers.add(0, exceptionHandler());
        resolvers.add(1, exceptionResolver());
    }

    // 文件上传必须multipart解析器
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
/*        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 可以不配置UploadTempDir
        multipartResolver.setUploadTempDir(new FileSystemResource("B:\\tmp"));
        return multipartResolver;
        */
        return new StandardServletMultipartResolver();
    }
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不配置路径，全局拦截器，拦截所有请求
        //registry.addInterceptor(new TimeInterceptor());
        // 路径中的**表示匹配任意字符
//        registry.addInterceptor(new DemoInterceptor());
//        registry.addInterceptor(new TimeInterceptor()).addPathPatterns("/register/**");
        // preHandle执行顺序为配置执行顺序
        // postHandle, afterCompletion则是反序执行
        String[] excludePathPatterns = {"/js/**", "/css/**", "/images/**", "/login/**", "/logout/**", "/register/**"};
        registry.addInterceptor(authenticationInterceptor()).excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(new AuthorizationInterceptor()).excludePathPatterns(excludePathPatterns);
    }

    @Bean
    public HandlerInterceptor authenticationInterceptor() {
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor();
        interceptor.setNotRequiringLoginUrls(new String[]{"/login", "/register", "/about"});
        return interceptor;
    }*/
@Override
public void addInterceptors(InterceptorRegistry registry) {
    // 不配置路径，全局拦截器，拦截所有请求
    //registry.addInterceptor(new TimeInterceptor());
    // 路径中的**表示匹配任意字符
//        registry.addInterceptor(new DemoInterceptor());
//        registry.addInterceptor(new TimeInterceptor()).addPathPatterns("/register/**");
    // preHandle执行顺序为配置执行顺序
    // postHandle, afterCompletion则是反序执行
    registry.addInterceptor(authenticationInterceptor());
        String[] excludePathPatterns = { "/index"
                ,"/house/getAllHouseByPage"
                ,"/house/getTotalPageHouseByPage"
                ,"/house/getTotalSizeHouseByPage"
                ,"/login"
                , "/register"
                ,"/register/**"
                ,"/login/**"
                ,"/css/**"
                ,"/images/**"
                ,"/js/**"};
       registry.addInterceptor(authenticationInterceptor()).excludePathPatterns(excludePathPatterns);


}

    @Bean
    public HandlerInterceptor authenticationInterceptor() {
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor();
        interceptor.setNotRequiringLoginUrls(new String[]{
                "/index"
                ,"/house/getAllHouseByPage"
                ,"/house/getTotalPageHouseByPage"
                ,"/house/getTotalSizeHouseByPage"
                ,"/login"
                , "/register"
                ,"/register/**"
                ,"/login/**"
                ,"/css/**"
                ,"/images/**"
                ,"/js/**"});
        return interceptor;
    }
    /**
     * 不配置MessageSource和LocalValidatorFactoryBean，
     * 默认错误消息文件位置：classpath:ValidationMessages.properties
     * @return
     */
    @Bean
    MessageSource errorMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:errorMessages");
        ms.setDefaultEncoding(StandardCharsets.UTF_8.name());
        ms.setCacheSeconds(20);
        return ms;
    }

    // 必须使用override来覆盖getValidator，要不然自定义消息不起作用
    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(errorMessageSource());
        return bean;
    }

    @Bean
    BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views"); // 默认就是views
        viewResolver.setOrder(1);
        return viewResolver;
    }

    // 默认的视图解析器: BeanNameViewResolver
    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    /**
     * 添加converter和formatter
     *
     * @param registry
     */


//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(jacksonMessageConverter());
//    }
//
//    private HttpMessageConverter<?> jacksonMessageConverter() {
//        MappingJackson2HttpMessageConverter messageConverter
//                = new  MappingJackson2HttpMessageConverter();
//
//        ObjectMapper mapper = new ObjectMapper();
//        //Registering Hibernate5Module to support lazy objects
//        mapper.registerModule(new Hibernate5Module().configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true));
//
//        messageConverter.setObjectMapper(mapper);
//        return messageConverter;
//    }

    //    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    /**
     * 我们配置dispatcher servlet处理'/'，所有的请求都会通过它处理。
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**", "/css/**","/images/**")
                .addResourceLocations("classpath:/static/js/", "classpath:/static/css/","classpath:/static/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/register").setViewName("register");
        // 如果有controller对url的特定请求有支持，view controller将失效
//        registry.addViewController("/login").setViewName("login");
    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/classes/views/", ".jsp");
//    }
}

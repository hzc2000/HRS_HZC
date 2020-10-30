package org.hzc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// 该注解启动切面
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.hzc.service", "org.hzc.config"})
public class AspectConfig {
//    @Bean
//    public InspectAspect inspectAspect() {
//        return new InspectAspect();
//    }
}

package org.hzc.config;

import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * UsernamePasswordAuthenticationFilter: process authentication, responds by default to “/login” URL
 * AnonymousAuthenticationFilter: when there's no authentication object in SecurityContextHolder,
 * it creates an anonymous authentication object and put it there
 * FilterSecurityInterceptor: raise exceptions when access is denied
 * ExceptionTranslationFilter: catch Spring Security exceptions
 */
@Configuration
// 需要配合WebSecurityConfigurer
@EnableWebSecurity(debug = true)
//@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     */
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    /**
//     */
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    /**
     * 不能使用passwordEncoder()，否则使用的还是DelegatingPasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 如果想使用hasRole，必须保证用户的autority是以'ROLE_'开头。
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors();
//        httpSecurity.csrf().disable();
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler);

// csrf must disable to test through postman or http client
//
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
            .antMatchers(HttpMethod.DELETE, "/user/*").hasRole("LANDLORD")
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ROLE_LANDLORD")
//                .antMatchers(HttpMethod.DELETE, "/user/*").hasAuthority("LANDLORD")
//                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("LANDLORD")
            .anyRequest().permitAll()
//            .and().rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(86400).key("csmKey")
                .and()
            .formLogin().loginPage("/login")
                .usernameParameter("phone") // username
//                .passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
            .and().httpBasic();

//        httpSecurity.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.
//        // https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format
//        // http://www.appsdeveloperblog.com/spring-security-in-memory-authentication/
//        // https://www.concretepage.com/spring-5/spring-security-userdetailsservice
//        // https://www.baeldung.com/spring-security-authentication-with-a-database
//        // https://www.baeldung.com/spring-security-expressions
//        //inMemoryAuth(auth);
//        //jdbcAuth(auth);
//        auth.userDetailsService(userService).passwordEncoder(bcryptPasswordEncoder());
//    }

    /*private void jdbcAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT phone, pwdHash, true FROM user WHERE phone=?")
                .authoritiesByUsernameQuery("SELECT phone, concat('ROLE_', role) FROM user WHERE phone=?")
        .passwordEncoder(new BCryptPasswordEncoder());
    }

    private void inMemoryAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG").roles("USER").and()
                .withUser("admin").password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG").roles("ADMIN");
    }*/
}

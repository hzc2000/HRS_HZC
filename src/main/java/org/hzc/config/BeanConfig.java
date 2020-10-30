package org.hzc.config;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hzc.entity.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@EnableScheduling
@Configuration
@ComponentScan(value = "org.hzc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
@EnableJpaRepositories("org.hzc.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
// spring相关的配置
// 相当于beans.xml, spring.xml
@Data
public class BeanConfig {
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${permission.user}")
    private Set<String> permissionUserRoles;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Bean
    public DataSource poolDataSource() {
        BasicDataSource ds = new BasicDataSource();
        // 下面4个setter是必须的
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        // 以下的setter是连接池相关的，可选
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        ds.setMaxIdle(6);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager(emf);
        return tm;
    }



    @Bean
    HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        return jpaVendorAdapter;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(poolDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        // 告知哪些类是实体类，不需要使用persistence.xml来配置持久化单元
        // 至关重要！！！IllegalArgumentException: Not a managed type
        entityManagerFactoryBean.setPackagesToScan("org.hzc.entity");
        return entityManagerFactoryBean;
    }

    @Bean
    public Map<String, Set<String>> permissions() {
        Map<String, Set<String>> m = new HashMap<>();
        Set<String> roles = new HashSet<>();
        roles.add(Role.LANDLORD.name());
        m.put("user", roles);
        return m;
    }
}

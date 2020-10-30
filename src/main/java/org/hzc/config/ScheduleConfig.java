package org.hzc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {//定时任务配置
 @Override
 public void configureTasks(ScheduledTaskRegistrar taskRegistrar){
 TaskScheduler taskScheduler = taskScheduler();
 taskRegistrar.setTaskScheduler(taskScheduler);
 }
 @Bean(destroyMethod = "shutdown")
 public ThreadPoolTaskScheduler taskScheduler() {
 ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
 scheduler.setPoolSize(20);
 scheduler.setThreadNamePrefix("task-");
 scheduler.setAwaitTerminationSeconds(60);
 scheduler.setWaitForTasksToCompleteOnShutdown(true);
 return scheduler;
 }
}
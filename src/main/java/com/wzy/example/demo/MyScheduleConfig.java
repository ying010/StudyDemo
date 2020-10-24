package com.wzy.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * @author 王志英
 */
@Configuration
@EnableScheduling
public class MyScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("线程：" + Thread.currentThread().getName() + "--->执行定时任务1--" + LocalDateTime.now());
                },
                triggerContext -> {
                    String cron = "0/5 * * * * ?";

                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("线程：" + Thread.currentThread().getName() + "--->执行定时任务2--" + LocalDateTime.now());
                },
                triggerContext -> {
                    String cron = "3/2 * * * * ?";

                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}

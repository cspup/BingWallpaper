package com.csp.bingwallpaper.Utils;

import com.csp.bingwallpaper.Service.GetBingWallpaper;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author csp
 * @date 2022/2/27 15:19
 * @description
 */
@Component
//@EnableScheduling
public class TaskUtil {

    private String cron = "0/5 * * * * *";
    private Object object;
    private Object[] args;
    private Method method;


    public void setCron(String cron) {
        this.cron = cron;
    }





    public void setObject(Object object) {
        this.object = object;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

package com.jx.background.config;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jx.background.config.quartzJob.BgWxCallbackQuartzJob;
import com.jx.common.util.QuartzManager;

@Component
public class BgApplicationListener implements ApplicationListener<ApplicationContextEvent> {

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
			if (event instanceof ContextRefreshedEvent) {
				try {
					QuartzManager.addJob("BgWxConfigQuartzJob", BgWxCallbackQuartzJob.class, "0 */30 * * * ?");
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			}else if (event instanceof ContextClosedEvent) {
				System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
			}
		}
	}
	
}

package com.jx.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringContextUtil implements ApplicationContextAware{
	
	 	private static ApplicationContext appCtx;

	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	    	appCtx = applicationContext;
	    }
	    
	    public static ApplicationContext getApplicationContext(){
	        return appCtx;
	    }

	    public static Object getBean(String beanName) throws BeansException{
	        return appCtx.getBean(beanName);
	    }
}

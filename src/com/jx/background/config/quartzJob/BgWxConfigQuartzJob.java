package com.jx.background.config.quartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jx.common.service.ComWxAccountService;
import com.jx.common.util.SpringContextUtil;

public class BgWxConfigQuartzJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ComWxAccountService comWxAccountService = (ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		
	}
	
}

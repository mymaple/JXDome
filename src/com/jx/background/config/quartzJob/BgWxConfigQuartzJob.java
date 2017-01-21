package com.jx.background.config.quartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jx.common.entity.ComWxAccount;
import com.jx.common.service.ComWxAccountService;
import com.jx.common.util.SpringContextUtil;
import com.jx.common.util.WxConnUtil;

public class BgWxConfigQuartzJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		ComWxAccountService comWxAccountService = (ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		try {
			ComWxAccount comWxAccount = comWxAccountService.findStart();
			String accessToken = WxConnUtil.getAccessToken(comWxAccount.getAppId(), comWxAccount.getAppSecret());
			String jsApiTicket = WxConnUtil.getJsApiTicket(accessToken);
			ComWxAccount comWxAccountChange = new ComWxAccount();
			comWxAccountChange.setWxAccountId(comWxAccount.getWxAccountId());
			comWxAccountChange.setAccessToken(accessToken);
			comWxAccountChange.setJsApiTicket(jsApiTicket);
			comWxAccountService.change(comWxAccountChange);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

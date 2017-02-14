package com.jx.background.config.quartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jx.common.entity.ComWxAccount;
import com.jx.common.service.ComWxAccountService;
import com.jx.common.util.SpringContextUtil;
import com.jx.common.util.WxConnUtil;

public class BgWxCallbackQuartzJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		ComWxAccountService comWxAccountService = (ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		try {
			ComWxAccount comWxAccount = comWxAccountService.findCurrent();
			String accessToken = WxConnUtil.getAccessToken(comWxAccount.getAppId(), comWxAccount.getAppSecret());
			String jsApiTicket = WxConnUtil.getJsApiTicket();
			ComWxAccount comWxAccountChange = new ComWxAccount();
			comWxAccountChange.setWxAccountId(comWxAccount.getWxAccountId());
			comWxAccountChange.setAccessToken(accessToken);
			comWxAccountChange.setJsApiTicket(jsApiTicket);
			comWxAccountChange.setModifyUserId("wx");
			comWxAccountService.change(comWxAccountChange);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

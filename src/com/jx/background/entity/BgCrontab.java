package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgCrontab extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 定时任务 主键id */
	@NotBlank(message="定时任务 主键id 不能为空", groups={ValidationEdit.class})
	private String crontabId;
	
	/** 定时任务代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="定时任务代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String crontabCode;
		
	/** 定时任务名称 */
	@NotBlank(message="定时任务名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String crontabName;
		
	/** 定时任务类型 */
	@NotBlank(message="定时任务类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String crontabType;
		
	/** 定时任务状态 */
	private String crontabStatus;
		
	/** 启动次数 */
	private String startupTimes;
		
	/** 任务名 */
	@NotBlank(message="任务名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String jobName;
		
	/** 任务组名 */
	@NotBlank(message="任务组名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String jobGroupName;
		
	/** 任务 */
	@NotBlank(message="任务 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String jobClass;
		
	/** 触发器名 */
	@NotBlank(message="触发器名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String triggerName;
		
	/** 触发器组名 */
	@NotBlank(message="触发器组名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String triggerGroupName;
		
	/** 运作次数 */
	private String triggerTimes;
		
	/** 停止时间 */
	private Date endTime;
		
	/** 时间表达式 */
	@NotBlank(message="时间表达式 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String timeExp;
		
	
	
	/**
	 * 设置定时任务 主键id
	 * 
	 * @param String crontabId
	 */
	public void setCrontabId(String crontabId) {
		this.crontabId = MapleStringUtil.trim(crontabId);
	}
	
	/**
	 * 获取定时任务 主键id
	 * 
	 * @return String crontabId
	 */
	public String getCrontabId() {
		return this.crontabId;
	}
	
	/**
	 * 设置 定时任务代号
	 * 
	 * @param String crontabCode
	 */
	public void setCrontabCode(String crontabCode) {
		this.crontabCode = MapleStringUtil.trim(crontabCode);
	}
	
	/**
	 * 获取 定时任务代号
	 * 
	 * @return String crontabCode
	 */
	public String getCrontabCode() {
		return this.crontabCode;
	}
	
	/**
	 * 设置 定时任务名称
	 * 
	 * @param String crontabName
	 */
	public void setCrontabName(String crontabName) {
		this.crontabName = MapleStringUtil.trim(crontabName);
	}
	
	/**
	 * 获取 定时任务名称
	 * 
	 * @return String crontabName
	 */
	public String getCrontabName() {
		return this.crontabName;
	}
	
	/**
	 * 设置 定时任务类型
	 * 
	 * @param String crontabType
	 */
	public void setCrontabType(String crontabType) {
		this.crontabType = MapleStringUtil.trim(crontabType);
	}
	
	/**
	 * 获取 定时任务类型
	 * 
	 * @return String crontabType
	 */
	public String getCrontabType() {
		return this.crontabType;
	}
	
	/**
	 * 设置 定时任务状态
	 * 
	 * @param String crontabStatus
	 */
	public void setCrontabStatus(String crontabStatus) {
		this.crontabStatus = MapleStringUtil.trim(crontabStatus);
	}
	
	/**
	 * 获取 定时任务状态
	 * 
	 * @return String crontabStatus
	 */
	public String getCrontabStatus() {
		return this.crontabStatus;
	}
	
	/**
	 * 设置 启动次数
	 * 
	 * @param String startupTimes
	 */
	public void setStartupTimes(String startupTimes) {
		this.startupTimes = startupTimes;
	}
	
	/**
	 * 获取 启动次数
	 * 
	 * @return String startupTimes
	 */
	public String getStartupTimes() {
		return this.startupTimes;
	}
	
	/**
	 * 设置 任务名
	 * 
	 * @param String jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = MapleStringUtil.trim(jobName);
	}
	
	/**
	 * 获取 任务名
	 * 
	 * @return String jobName
	 */
	public String getJobName() {
		return this.jobName;
	}
	
	/**
	 * 设置 任务组名
	 * 
	 * @param String jobGroupName
	 */
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = MapleStringUtil.trim(jobGroupName);
	}
	
	/**
	 * 获取 任务组名
	 * 
	 * @return String jobGroupName
	 */
	public String getJobGroupName() {
		return this.jobGroupName;
	}
	
	/**
	 * 设置 任务
	 * 
	 * @param String jobClass
	 */
	public void setJobClass(String jobClass) {
		this.jobClass = MapleStringUtil.trim(jobClass);
	}
	
	/**
	 * 获取 任务
	 * 
	 * @return String jobClass
	 */
	public String getJobClass() {
		return this.jobClass;
	}
	
	/**
	 * 设置 触发器名
	 * 
	 * @param String triggerName
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = MapleStringUtil.trim(triggerName);
	}
	
	/**
	 * 获取 触发器名
	 * 
	 * @return String triggerName
	 */
	public String getTriggerName() {
		return this.triggerName;
	}
	
	/**
	 * 设置 触发器组名
	 * 
	 * @param String triggerGroupName
	 */
	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = MapleStringUtil.trim(triggerGroupName);
	}
	
	/**
	 * 获取 触发器组名
	 * 
	 * @return String triggerGroupName
	 */
	public String getTriggerGroupName() {
		return this.triggerGroupName;
	}
	
	/**
	 * 设置 运作次数
	 * 
	 * @param String triggerTimes
	 */
	public void setTriggerTimes(String triggerTimes) {
		this.triggerTimes = triggerTimes;
	}
	
	/**
	 * 获取 运作次数
	 * 
	 * @return String triggerTimes
	 */
	public String getTriggerTimes() {
		return this.triggerTimes;
	}
	
	/**
	 * 设置 停止时间
	 * 
	 * @param Date endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取 停止时间
	 * 
	 * @return Date endTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}	
		
	public void setEndTimeStr(String endTimeStr) throws Exception{
		endTimeStr = MapleStringUtil.trim(endTimeStr);
		if(!endTimeStr.equals("")){
			try{
				setEndTime(MapleDateUtil.parseDateStr(endTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getEndTimeStr(){
		return MapleDateUtil.formatDate(getEndTime());
	}	
	
	/**
	 * 设置 时间表达式
	 * 
	 * @param String timeExp
	 */
	public void setTimeExp(String timeExp) {
		this.timeExp = MapleStringUtil.trim(timeExp);
	}
	
	/**
	 * 获取 时间表达式
	 * 
	 * @return String timeExp
	 */
	public String getTimeExp() {
		return this.timeExp;
	}
	
	
	/**************************table prop  end  *********************************/
}
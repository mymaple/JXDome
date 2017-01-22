package com.jx.common.util;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.jx.background.config.quartzJob.BgWxCallbackQuartzJob;

  
/** 定时任务管理类 
 * @author maple
 * @date 2016-4-9
 */
public class QuartzManager {  
	
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();  	//创建一个SchedulerFactory工厂实例
    private static String JOB_GROUP_NAME = "mapleJobGroupName";  					//任务组
    private static String TRIGGER_GROUP_NAME = "mapleTriggerGroupName";  			//触发器组
  
    
    public static void main(String[] args) throws Exception {
    	addJob("BgWxConfigQuartzJob", BgWxCallbackQuartzJob.class, "* 0/1 * * * ?");
	}
    
    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名  
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     * @throws SchedulerException 
     */
    public static void addJob(String jobName, Class<? extends Job> jobClass, String time) throws SchedulerException {  
    	addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, jobClass, time, null);
    }  
    
    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名  （带参数）
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     * @throws SchedulerException 
     */
    public static void addJob(String jobName, Class<? extends Job> jobClass, String time, Map<String,Object> params) throws SchedulerException {  
    	addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, jobClass, time, params);
    }  
  
    /**添加一个定时任务 
     * @param jobName	任务名 
     * @param jobGroupName	任务组名 
     * @param triggerName	触发器名 
     * @param triggerGroupName	触发器组名 
     * @param jobClass	任务 
     * @param time	时间设置，参考quartz说明文档 
     * @throws SchedulerException 
     */
    public static void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class<? extends Job> jobClass,  
            String time) throws SchedulerException {  
    	addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, time, null);
    }  
    
    /**添加一个定时任务  （带参数）
     * @param jobName	任务名 
     * @param jobGroupName	任务组名 
     * @param triggerName	触发器名 
     * @param triggerGroupName	触发器组名 
     * @param jobClass	任务 
     * @param time	时间设置，参考quartz说明文档 
     * @throws SchedulerException 
     */
    public static void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class<? extends Job> jobClass,  
            String time, Map<String,Object> params) throws SchedulerException {  
        Scheduler sched = schedulerFactory.getScheduler();
        JobDetail jobDetail= JobBuilder
        		.newJob(jobClass)
        		.withIdentity(jobName,jobGroupName)
        		.build();														// 任务名，任务组，任务执行类
        jobDetail.getJobDataMap().put("params", params);						// 传参数
        CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity(triggerName, triggerGroupName)
				.withSchedule(CronScheduleBuilder.cronSchedule(time))
				.build();														// 触发器  
        sched.scheduleJob(jobDetail, trigger);
        if (!sched.isShutdown()) {  
            sched.start();  	  												// 启动  
        } 
    } 
  
    /** 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName	任务名 
     * @param time	新的时间设置
     * @throws SchedulerException 
     */
    public static void modifyJobTime(String jobName, String time) throws SchedulerException {  
    	modifyJobTime(jobName, TRIGGER_GROUP_NAME, time);
    }  
    
    /**修改一个任务的触发时间 
     * @param triggerName	任务名称
     * @param triggerGroupName	传过来的任务名称
     * @param time	更新后的时间规则
     * @throws SchedulerException 
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) throws SchedulerException {  
        Scheduler sched = schedulerFactory.getScheduler();  							//通过SchedulerFactory构建Scheduler对象
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName); 	//通过触发器名和组名获取TriggerKey
        CronTrigger trigger = (CronTrigger)sched.getTrigger(triggerKey);				//通过TriggerKey获取CronTrigger
        if (trigger == null)  return;  
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
        String oldTime = trigger.getCronExpression();  
        if (!oldTime.equalsIgnoreCase(time)) {  
        	trigger = (CronTrigger)trigger.getTriggerBuilder()							//重新构建trigger
        			.withIdentity(triggerKey)
        			.withSchedule(scheduleBuilder)
        			.withSchedule(CronScheduleBuilder.cronSchedule(time))
    				.build();
        	sched.rescheduleJob(triggerKey, trigger);									//按新的trigger重新设置job执行
        }
    }  
    
    /**移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
     * @param jobName	任务名称
     * @throws SchedulerException 
     */
    public static void removeJob(String jobName) throws SchedulerException {  
    	removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
    }  
    
    /**移除一个任务
     * @param jobName	任务名
     * @param jobGroupName	任务组名
     * @param triggerName	触发器名
     * @param triggerGroupName	触发器组名
     * @throws SchedulerException 
     */
    public static void removeJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName) throws SchedulerException {  
        Scheduler sched = schedulerFactory.getScheduler();  
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName); 	//通过触发器名和组名获取TriggerKey
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);							//通过任务名和组名获取JobKey
        sched.pauseTrigger(triggerKey);	// 停止触发器  
        sched.unscheduleJob(triggerKey);// 移除触发器  
        sched.deleteJob(jobKey);		// 删除任务  
    } 
    
    /**
     * 启动所有定时任务 
     * @throws SchedulerException 
     */
    public static void startJobs() throws SchedulerException {  
        Scheduler sched = schedulerFactory.getScheduler();  
        sched.start();  
    }  
  
    /**
     * 关闭所有定时任务 
     * @throws SchedulerException 
     */
    public static void shutdownJobs() throws SchedulerException {  
        Scheduler sched = schedulerFactory.getScheduler();  
        if (!sched.isShutdown()) {  
            sched.shutdown();  
        }  
    }  
}  
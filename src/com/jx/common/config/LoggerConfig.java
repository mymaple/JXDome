package com.jx.common.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerConfig {
	
	private final static Logger logger = Logger.getLogger(LoggerConfig.class);
	
	private String logStr = "" ; 
	 /** 
     * 前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点前的执行 
     */  
    public void doBefore(JoinPoint jp) {      
        logStr = jp.getTarget().getClass().getName() + " 类的 "  
                + jp.getSignature().getName()+" 方法开始执行 ***Start***";  
        logger.info(logStr);  
    }  
      
      
    /** 
     * 环绕通知：包围一个连接点的通知，可以在方法的调用前后完成自定义的行为，也可以选择不执行 
     */  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
          
        Object result=null;  
        try{    
            result = pjp.proceed();    
        }catch(Exception e){    
            logStr = "方法："+pjp.getTarget().getClass() + "." + pjp.getSignature().getName()+ "()  ";  
            logStr = logStr+"错误信息如下：["+e+"]";  
            logger.info(logStr);   
        }  
    
        return result;  
    }  
      
      
    /** 
     * 后置通知 
     */  
    public void doAfter(JoinPoint jp) {  
        logStr =jp.getTarget().getClass().getName() + " 类的 "  
                + jp.getSignature().getName() +" 方法执行结束 ***End***";   
        logger.info(logStr);  
    }  
    
    /** 
     * 异常通知 
     */  
    public void doExec(JoinPoint jp, Exception ex){
        logger.error("异常处理"+ex.toString());  
    }  
    
}

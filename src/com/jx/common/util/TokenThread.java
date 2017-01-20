package com.superTrader.weixin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.js.util.ListView;

/**
 * 用于控制access_token休眠的线程
 * @author pc
 *
 */
public class TokenThread implements Runnable{


	  public static String appId = "wx299fe62ac73f8a2f"; 
	   
	  public static String appSecret= "b0df3568229b51e8bb0c177f10126a64"; 
	  //注意是静态的 
	  public static AccessToken accessToken = null; 
	  //jsapi_ticket
	  public static String jsapi_ticket = null;
	   
	  public void run(){ 
		while (true){ 
	      try{ 
	    	String accessTokenStr = getAccessTokenStr();
//	        accessToken = this.getAccessToken(); 
	        if(null!=accessTokenStr&&!"".equals(accessTokenStr)){ 
	          String access_token = accessTokenStr;
	          ListView lv = new ListView();
	          List list = null;
	          List list1 = null;
	          //保存access_token(先查询是否有数据)
	          String sql = "select * from w_takencode";
	          //查询jsapi（先查询是否有数据）
	          String sql1 = "select * from w_jsapi";
	          //查询是否有数据
	          list = lv.resultSetToList(sql);
	          list1 = lv.resultSetToList(sql1);
	          //更新或者新增sql
	          String sqlU = "";
	          String sqlU1 = "";
	          //当前时间
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	          String dateStr =sdf.format(new Date());
	          //存在数据则更新 不存在则保存
	          if(null!=list&&!list.isEmpty()){
	        	  Map map = (Map) list.get(0);
	        	  //主键id
	        	  String token_id = (String) map.get("token_id");
	        	  //更新sql
	        	  sqlU = "update w_takencode set code='"+access_token+"',time='"+dateStr+"' where token_id='"+token_id+"'";
	          }else{
	        	  //保存sql
	        	  sqlU = "insert into w_takencode(time,code) values('"+dateStr+"','"+access_token+"')";
	          }
	          boolean bool = false;
	          bool = lv.resultReturnUpdate(sqlU);
	          //更新微信公众号表
	          if(bool){
	        	  //获取jsapi
			      String jsapi = getJsapi_ticket();
		          //存在数据则更新 不存在则保存
		          if(null!=list1&&!list1.isEmpty()){
		        	  Map map = (Map) list1.get(0);
		        	  //主键id
		        	  String id = (String) map.get("id");
		        	  //更新sql
		        	  sqlU1 = "update w_jsapi set code='"+jsapi+"',time='"+dateStr+"' where id='"+id+"'";
		          }else{
		        	  //保存sql
		        	  sqlU1 = "insert into w_jsapi(time,code) values('"+dateStr+"','"+jsapi+"')";
		          }
	        	  bool = lv.resultReturnUpdate(sqlU1);
	        	  if(bool){
	        		//酷礼Cooler微信号jiangxiang233(更新到主表中)
		        	  String sqlWx_user = "update wx_user set wxAccessToken='"+access_token+"' where account='jiangxiang233'";
		        	  bool = lv.resultReturnUpdate(sqlWx_user);
		        	  if(bool){
 		        		  Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒  
		        	  }
	        	  }
	          }
	        }else{ 
	          Thread.sleep(1000*3); //获取的access_token为空 休眠3秒 
	        } 
	        
	      }catch(Exception e){ 
	        System.out.println("发生异常："+e.getMessage()); 
	        e.printStackTrace(); 
	        try{ 
	          Thread.sleep(1000*10); //发生异常休眠1秒 
	        }catch (Exception e1){ 
	   
	        } 
	      } 
	    }
	  } 
	   
	   
	  /** 
	   * 获取access_token 
	   * @return 
	   */
	  private AccessToken getAccessToken(){ 
	    NetWorkHelper netHelper = new NetWorkHelper(); 
	    String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret); 
	    String result = netHelper.getHttpsResponse(Url,""); 
	    AccessToken token = new AccessToken(); 
	    //response.getWriter().println(result); 
    	JSONObject json = JSONObject.fromObject(result); 
//	 	    System.out.println("json23="+json);
//	 	    System.out.println("123123123="+json.getString("access_token"));
 	    token.setAccess_token(json.getString("access_token")); 
 	    token.setExpires_in(json.getString("expires_in")); 
	    return token; 
	  } 
	  
	  public static String getAccessTokenStr(){
		  String accessTokenStr = "";
		  NetWorkHelper netHelper = new NetWorkHelper(); 
	      String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret); 
	      String result = netHelper.getHttpsResponse(Url,""); 
    	  JSONObject json = JSONObject.fromObject(result); 
    	  accessTokenStr = json.getString("access_token");
		  return accessTokenStr;
	  }
	  /**
	   * 获取jsapi
	   * @return
	   */
  public static String getJsapi_ticket(){
		String jsapi_ticket = "";
		//获取access_token
		String access_token = AccestokenUtil.getAccessToken();
		// 拼装请求地址  
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		NetWorkHelper netHelper = new NetWorkHelper(); 
	    String result = netHelper.getHttpsResponse(uploadMediaUrl,""); 
	    JSONObject jsonBack = JSONObject.fromObject(result);
	    //获取返回码
	    int errcode = (Integer) jsonBack.get("errcode");
	    if(0==errcode){
	    	jsapi_ticket = (String) jsonBack.get("ticket");
	    }
		return jsapi_ticket;
	}


}

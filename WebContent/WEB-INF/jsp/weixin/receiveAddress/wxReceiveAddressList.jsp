<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>格陌汽配</title>
	    <meta name="keywords" content="">
    <meta name="description" content="">
    <script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	function toAdd(){
		window.location.href = "<%=basePath%>weixin/receiveAddress/toAdd.do";
	}
	
	function toEdit(receiveAddressId){
		window.location.href = "<%=basePath%>weixin/receiveAddress/toEdit.do?receiveAddressId="+receiveAddressId;
	}
	
</script>
</head>
<!--loading页开始-->
<div class="loading">
	<div class="loader">
        <div class="loader-inner pacman">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
	</div>
</div>
<!--loading页结束-->
	<body>
		
	    <div id="main" class="mui-clearfix contaniner">
	    	<div class="addlist clearfloat">
	    		<div class="top clearfloat box-s">
	    			<ul>
	    				<li>
	    					<span class="fl">孙瑾晨</span>
	    					<span class="fr">1303505****</span>
	    				</li>
	    				<li>
	    					安徽省合肥市蜀山区高新技术产业开发区合肥市高新区长江西路拓基城市广场金座A幢2002
	    				</li>
	    			</ul>
	    		</div>
	    		<div class="bottom clearfloat box-s">
	    			<section class="shopcar clearfloat">
						<div class="radio fl"> 
						    <label>
						        <input type="radio" name="sex" value="">
						        <div class="option"></div>
						        <span class="mradd smradd fl">设为默认</span>
						    </label>
						</div>
						
						<div class="right fr clearfloat">
							<a href="#" class="fr">
								<i class="iconfont icon-lajixiang"></i>
								删除
							</a>
							<a href="#" class="fr">
								<i class="iconfont icon-shouji"></i>
								编辑
							</a>							
						</div>
					</section>
	    		</div>
	    	</div>
	    	
	    	<div class="addlist clearfloat">
	    		<div class="top clearfloat box-s">
	    			<ul>
	    				<li>
	    					<span class="fl">孙瑾晨</span>
	    					<span class="fr">1303505****</span>
	    				</li>
	    				<li>
	    					安徽省合肥市蜀山区高新技术产业开发区合肥市高新区长江西路拓基城市广场金座A幢2002
	    				</li>
	    			</ul>
	    		</div>
	    		<div class="bottom clearfloat box-s">
	    			<section class="shopcar clearfloat">
						<div class="radio fl"> 
						    <label>
						        <input type="radio" name="sex" value="">
						        <div class="option"></div>
						        <span class="mradd smradd fl">设为默认</span>
						    </label>
						</div>
						
						<div class="right fr clearfloat">
							<a href="#" class="fr">
								<i class="iconfont icon-lajixiang"></i>
								删除
							</a>
							<a href="#" class="fr">
								<i class="iconfont icon-shouji"></i>
								编辑
							</a>							
						</div>
					</section>
	    		</div>
	    	</div>
	    	
	    </div>
	    
     	<a onclick="toAdd();" class="address-add fl">
     		添加新地址
     	</a>
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/fastclick.js"></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
</html>
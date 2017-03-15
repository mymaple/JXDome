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
    <script src="js/rem.js"></script> 
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/page.css"/>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="css/mui.picker.min.css" />
	<link href="css/mui.picker.css" rel="stylesheet" />
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
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
	    <div class="warpthree clearfloat">
	      <div class="recharge clearfloat">
    		  <div class="czhi clearfloat box-s">
	    			<div class="fl">登录账号：</div>
	    			<div class="fr f-col">139617767774</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">姓名：</div>
	    			<div class="fr f-col">总裁</div>
	    		</div>
                
                 <div class="czhi clearfloat box-s">
	    			<div class="fl">大区：</div>
	    			<div class="fr f-col">江苏省无锡市锡山区</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">小区：</div>
	    			<div class="fr f-col">江苏省无锡市锡山区</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">4S店：</div>
	    			<div class="fr f-col">江苏省无锡市锡山区</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">电话：</div>
	    			<div class="fr"><input type="text" id="" value="13961776665" placeholder="请输入金额"/></div>
	    		</div>

	    		<a href="" class="address-add fl">确认修改</a>
	    		
	    	</div>  	
	    </div>
	</body>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
	<script src="js/mui.min.js"></script>
	<script src="js/mui.picker.min.js"></script>
	<script src="js/others.js"></script>
	<script type="text/javascript" src="js/hmt.js" ></script>
	<script src="slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="css/swiper.min.css">
	<script src="js/swiper.jquery.min.js"></script>
</html>

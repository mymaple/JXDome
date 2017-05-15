<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
<%@ page import="com.jx.common.config.Const"  %>
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
     <script src="weui/gemo/js/rem.js?${resultInfo.version}"></script> 
    <script src="weui/gemo/js/jquery.min.js?${resultInfo.version}" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.min.css?${resultInfo.version}" />
    <link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css?${resultInfo.version}"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js?${resultInfo.version}"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
	
	$(function(){
		ignoreBack();
	});
	
	function ignoreBack(){
		if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}
	
	function transfer(){
		
		var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
		if($("#integralCount").val()==""){
			$("#integralCount").tips({
				side:3,
	            msg:'有效积分数量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#integralCount").focus();
		return false;
		}
		layer.open({
		    type: 2
		    ,shadeClose: false
		    ,content: '正在处理，请稍候...'
		  });
		$("#transferForm").submit();
	}
	
	function demical(obj){
		obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
		obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
		obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
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
	<form action="weixin/mine/transfer.do" name="transferForm" id="transferForm" method="post">	
		<input type="hidden" id="toAppUserId" name="toAppUserId" value="${comAppUser.appUserId}">
		<div class=" warpthree clearfloat">
	    	<div class="h-top h-toptwo clearfloat box-s">
	    		<p class="tu"><img src="<%=Const.BG_WEBSITE %>/${comAppUser.headImgSrc }"/></p>
	    		<p class="nr">${comAppUser.appUserName }
	    			<span>
					<param:display type="com_appUserRoleEffectiveP" value="${comAppUser.roleId}"/>  
	    			</span></p>
	    	</div>
	    	<div class="recharge clearfloat">
	    		<div class="czhi clearfloat box-s">
	    			<p>转赠积分</p>
	    			<input type="number" id="integralCount" name="integralCount" 
	    			value="" placeholder="请输入转赠积分" class="b-input" onkeydown="demical(this);" onkeyup="demical(this);" onchange="demical(this);"/>积分
	    		</div>
	    		<!-- <div class="czhi clearfloat box-s" id='showUserPickerthree'>
	    			<p>支付方式</p>
	    			<i class="iconfont icon-xiala fr"></i>
	    		</div> -->
	    		<!-- <div class="czhi clearfloat box-s">
	    			<p>备注</p>
	    			<input type="text" id="" value="" placeholder="请输入对方账户" class="b-input"/>
	    		</div> -->
                
	    		<div class="bottom clearfloat" style="margin-top:3%;">
	    			<a  href="javascript:history.go(-1);" class="db fl btn ra3">取消</a>
	    			<a onclick="transfer();" class="db fr btn ra3">确定</a>
	    		</div>
	    	</div>	    	
	    </div>
	    </form>
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/js/mui.min.js?${resultInfo.version}"></script>
	<script src="weui/gemo/js/mui.picker.min.js?${resultInfo.version}"></script>
	<script src="weui/gemo/js/others.js?${resultInfo.version}"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/slick/slick.js?${resultInfo.version}" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css?${resultInfo.version}">
	<script src="weui/gemo/js/swiper.jquery.min.js?${resultInfo.version}"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js?${resultInfo.version}"></script>
</html>


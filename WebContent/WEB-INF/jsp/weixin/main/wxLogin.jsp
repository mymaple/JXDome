<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<link rel="stylesheet" href="weui/dist/style/weui.min.css"/>
	<script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css"/>
    <link rel="stylesheet" href="weui/dist/style/layer.css"/>
	<script type="text/javascript" src="weui/dist/js/layer.js"></script>
<script type="text/javascript">
var phoneExg = /^0?(13|15|17|18|14)[0-9]{9}$/; //手机


	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	$(function(){
		ignoreBack();
	});
	
	function ignoreBack(){
		if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}

	function login(elem){
		$(this).attr("disabled",true);
		var phone = $('#phone').val();
		if(!phoneExg.test(phone)){
			layer.open({
		    	content: '请输入正确手机号码'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			$('#phone').focus();
		}
		var captcha = $('#captcha').val();
		if(captcha==null||captcha==''){
			layer.open({
		    	content: '请输入验证码'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			$('#captcha').focus();
		}
		var flag = $('flag').val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>weixin/main/login.do?tm='+new Date().getTime(),
	    	data: {"phone":phone,"captcha":captcha,"flag":flag},
			dataType:'json',
			//beforeSend: validateData,
			cache: false,
			success: function(data){
				if(data.resultCode == "success"){
					window.location.href = data.resultContent;
				}else{
					layer.open({
				    	content: data.resultContent
				    	,skin: 'msg'
				    	,time: 2 //2秒后自动关闭
				 	});
				}
				$(this).attr("disabled",false);
			}
		});
	}
	
	function getCaptcha(btn){
		var phone = $('#phone').val();
		if(!phoneExg.test(phone)){
			layer.open({
		    	content: '请输入正确手机号码'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			$('#phone').focus();
			return false;
		}
		var url = '<%=basePath %>weixin/main/getCaptcha.do?phone='+phone+'&tm='+new Date().getTime();
		$.get(url,function(data){
			if(data.resultCode == 'success'){
				time(btn);
			}else{
				layer.open({
			    	content: data.resultContent
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
			}
		});
	}
	
	var wait = 30;
	function time(btn){
		if (wait===0) {
			btn.removeAttribute('disabled');
			btn.value='重新获取';
			wait = 30;
		}else{
			btn.setAttribute('disabled',true);
			btn.value = wait + '秒后重试';
			wait--;
			setTimeout(function(){
				time(btn);
			},1000);
		}
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
	<form action="weixin/main/login.do" name="loginForm" id="loginForm" method="post">	
	<input type="hidden" name="flag" id="flag" value="${flag }">
	
		<div class="login clearfloat box-s">
			<h3>会员登录</h3>
			<div class="list clearfloat">
				<ul>
					<li class="ra3">
						<p class="fl clearfloat">
							<span class="opa5"></span>
							<i class="iconfont icon-yonghuming"></i>
						</p>
						<div class="nr clearfloat fl">
							<span class="opa3"></span>
							<input type="number" name="phone" id="phone" value="" placeholder="手机号码" />
						</div>
					</li>
					<li class="ra3">
						<p class="fl clearfloat">
							<span class="opa5"></span>
							<i class="iconfont icon-yanzhengma"></i>
						</p>
						<div class="nr nrtwo clearfloat fl">
							<span class="opa3"></span>
							<input type="number" name="captcha" id="captcha" value="" placeholder="验证码" />
							<samp class="db fr">
							<input name="input" type="button" value="获取验证码" class="log-btn captchaText" onclick="getCaptcha(this);">
							</samp>
						</div>
					</li>
				</ul>
			</div>
			<div class="login-btn">
				<a onclick="login(this);">
					登录
				</a>
			</div>
		</div>
	</form>
	</body>
</html>
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
	<title>toLogin</title>
	<link rel="stylesheet" href="weui/dist/style/weui.min.css"/>
	<link rel="stylesheet" href="weui/dist/style/layer.css"/>
	<script type="text/javascript" src="weui/dist/js/layer.js"></script>
</head>
<body>

	<form action="weixin/main/login.do" name="loginForm" id="loginForm" method="post">
	
	<input type="hidden" name="flag" id="flag" value="${flag }">
	
	<div class="weui-cells__title">登录页面</div>
        <div class="weui-cells weui-cells_form">
            
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
                <div class="weui-cell__bd">
                   <input class="weui-input" type="tel" name="phone" id="phone" placeholder="输入手机号">
                </div>
            </div>
            
            <div class="weui-cell weui-cell_vcode">
                <div class="weui-cell__hd">
                    <label class="weui-label">验证码</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" name="captcha" id="captcha" pattern="[0-9]*" placeholder="输入验证码"/>
                </div>
                <div class="weui-cell__ft">
                    <a class="weui-vcode-btn" href="javascript:getCaptcha();">获取验证码</a>
                </div>
            </div>
            
	</div>
            
            <div class="weui-btn-area">
            	<a class="weui-btn weui-btn_primary" href="javascript:login();" id="showTooltips">确定</a>
        	</div>
	
	</form>
</body>

<script type="text/javascript" src="weui/dist/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
	if(window.history.replaceState){
		window.history.replaceState(null,'',document.referrer);
	}
});

function login(){
	$("#loginForm").submit();
}

function getCaptcha(){
	var phone = $("#phone").val();
	var url = "<%=basePath%>weixin/main/getCaptcha.do?phone="+phone+"&tm="+new Date().getTime();
	$.get(url,function(data){
		if(data.resultCode != "success"){
			layer.open({
		    	content: data.resultContent
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
		}
	});
}




//匿名函数
<%-- $(function(){
   getHistory();
    var flag=false;
	setTimeout(function(){
    	flag=true
	},1000)
   window.addEventListener('popstate',function(e){
   //监听到返回事件
    if(flag){
    	//alert(document.referrer);
   //自己想要做的事情
      }
    	getHistory();
	},false);      
function getHistory(){
   var state={
   title:'1111111111111',
   url:'<%=basePath%>weixin/main/toShopCar.do'  
} ;
window.history.pushState(state,'title',document.referrer);
}
}) --%>

</script>

</html>
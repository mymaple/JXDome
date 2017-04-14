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
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.min.css" />
	<link href="weui/gemo/css/mui.picker.css" rel="stylesheet" />
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
	
	function toEdit(){
		$("#display").hide();
		$("#edit").show();
	}
	
	function edit(){
		var sex = $("input[name='sex']:checked").val();
		$.post("<%=basePath%>weixin/mine/changeMyInfo",
				{
			sex:$("input[name='sex']:checked").val(),
			brithdayStr:$("#brithdayStr").val(),
			remarks:$("#remarks").val()
				},
				function(data){
					if(data.resultCode=='success'){
						window.location.reload();
					}else{
						layer.open({
					    	content: data.resultContent
					    	,skin: 'msg'
					    	,time: 2 //2秒后自动关闭
					 	});
					}
		});
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
	    <div class="warpthree clearfloat" id="display">
	      <div class="recharge clearfloat">
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">姓名：</div>
	    			<div class="fr f-col">${comAppUser.appUserName}</div>
	    		</div>
                
                 <div class="czhi clearfloat box-s">
	    			<div class="fl">角色：</div>
	    			<div class="fr f-col"><param:display type="com_appUserRole" value="${comAppUser.roleId}"/></div>
	    		</div>
	    		
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl">性别：</div>
	    			<div class="fr f-col"><param:display type="com_sex" value="${comAppUser.sex}"/></div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">生日：</div>
	    			<div class="fr f-col">${comAppUser.brithdayStr}</div>
	    		</div>
                
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl">备注信息:</div>
	    		</div>
	    		<textarea disabled="disabled" rows="4" cols="" placeholder="请填写备注信息，不少于5个字" class="textare box-s" onchange="this.value=this.value.substring(0, 100)" 
										onkeydown="this.value=this.value.substring(0, 100)" onkeyup="this.value=this.value.substring(0, 100)">${comAppUser.remarks}</textarea>
	    		<a onclick="toEdit();" class="address-add fl">编辑</a>
	    	</div>  	
	    </div>
	    
	    <div class="warpthree clearfloat" id="edit" style="display: none;">
	      <div class="recharge clearfloat">
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">姓名：</div>
	    			<div class="fr f-col">${comAppUser.appUserName}</div>
	    		</div>
                
                 <div class="czhi clearfloat box-s">
	    			<div class="fl">角色：</div>
	    			<div class="fr f-col"><param:display type="com_appUserRole" value="${comAppUser.roleId}"/></div>
	    		</div>
	    		
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl">性别：${comAppUser.sex}</div>
	    			
	    			<div class="fr f-col">
	    				<div class="xuan clearfloat">
			     				<div class="radiotwo" > 
								    <label>
								        <input type="radio" name="sex" value="01" <c:if test="${comAppUser.sex == '01'}">checked="checked"</c:if>/>
								        <div class="option"></div>
								        <span class="opt-text">男</span>
								    </label>
								</div>
			     			</div>
		    				<div class="xuan clearfloat">
			     				<div class="radiotwo" > 
								    <label>
								        <input type="radio" name="sex" value="02" <c:if test="${comAppUser.sex == '02'}">checked="checked"</c:if>/>
								        <div class="option"></div>
								        <span class="opt-text">女</span>
								    </label>
								</div>
			     			</div>
					</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">生日：</div>
	    			<div class="fr f-col"><input class="fr shuru" name="brithdayStr" id="brithdayStr" value="${comAppUser.brithdayStr}" type="date" title="生日"/></div>
	    		</div>
                
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl">备注信息:</div>
	    		</div>
	    		<textarea name="remarks" id="remarks" rows="4" cols="" placeholder="请填写备注信息，不少于5个字" class="textare box-s" onchange="this.value=this.value.substring(0, 100)" 
										onkeydown="this.value=this.value.substring(0, 100)" onkeyup="this.value=this.value.substring(0, 100)">${comAppUser.remarks}</textarea>
	    		<a onclick="edit();" class="address-add fl">确认修改</a>
	    	</div>  	
	    </div>
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script src="weui/gemo/js/mui.picker.min.js"></script>
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
<base href="<%=basePath%>">
<title>${pd.systemName}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="static/login/bootstrap.min.css" />
<link rel="stylesheet" href="static/login/css/camera.css" />
<link rel="stylesheet" href="static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/login/matrix-login.css" />
<link href="static/login/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="static/login/js/jquery-1.5.1.min.js"></script>
<!-- 软键盘控件start -->
<link href="static/login/keypad/css/framework/form.css" rel="stylesheet" type="text/css"/>
<!-- 软键盘控件end -->
 <style type="text/css">
    /*
   body{
    -webkit-transform: rotate(-3deg);
    -moz-transform: rotate(-3deg);
    -o-transform: rotate(-3deg);
	padding-top:20px;
    }
    */
      .cavs{
    	z-index:1;
    	position: fixed;
    	width:95%;
    	margin-left: 20px;
    	margin-right: 20px;
    }
  </style>
  <script>
  		//window.setTimeout(showShake,3000); 
  		var timer;
		function showShake(){
			shake_i = 1;
			//关闭提示晃动屏幕，注释掉这句话即可
			timer = setInterval(shake2, 10); 
		};
		var current = 0;
		function shake(){
			current = (current)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current>360){current = 0;}
		};
		var shake_i = 1;
		var current2 = 1;
		function shake2(){
			if(shake_i>50){
				document.body.style.transform = 'rotate(0deg)';
				clearInterval(timer);
				return;
			}
			current = (current2)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current2 == 1){current2 = -1;}else{current2 = 1;}
			shake_i++;
		};
	</script>
</head>
<body>

	<c:if test="${pd.hasMusic == 'yes' }">
	<div style="display: none">
	    <audio src="static/login/music/fh1.mp3" autoplay=""></audio>
	</div>	
	</c:if>
	<canvas class="cavs"></canvas>
	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<!-- 登录 -->
		<div id="windows1">
		<div id="loginbox" >
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="static/login/user.png" /></i>
							</span><input type="text" name="loginName" id="loginName" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="static/login/suo.png" /></i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveId" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;padding-top:2px;">
							<i><img src="static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:4px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换" title="点击更换" src="" /></i>
						</div>
						<c:if test="${pd.hasRegister == 'yes' }">
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(1);" class="btn btn-success">注册</a></span>
						</c:if>
						<span class="pull-right"><a onclick="severCheck();" class="flip-link btn btn-info" id="to-recover">登录</a></span>
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © ${pd.systemName} 2016</span></font>
				</div>
			</div>
		</div>
		</div>
		<!-- 注册 -->
		<div id="windows2" style="display: none;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>用户</i>
							</span><input type="text" name="userName" id="userName" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>密码</i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>重输</i>
							</span><input type="password" name="chkpwd" id="chkpwd" placeholder="请重新输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>姓名</i>
							</span><input type="text" name="NAME" id="name" value="" placeholder="请输入姓名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>邮箱</i>
							</span><input type="text" name="email" id="email" value="" placeholder="请输入邮箱" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;padding-top:2px;">
							<i><img src="static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="rcode" id="rcode" class="login_code"
								style="height:16px; padding-top:4px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="zcodeImg" alt="点击更换" title="点击更换" src="" /></i>
						</div>
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(2);" class="btn btn-success">取消</a></span>
						<span class="pull-right"><a onclick="register();" class="flip-link btn btn-info" id="to-recover">提交</a></span>
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © ${pd.systemName} 2016</span></font>
				</div>
			</div>
		</div>
		</div>
		
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<!-- 背景图片 -->
			<c:choose>
				<c:when test="${not empty pd.listImg}">
					<c:forEach items="${pd.listImg}" var="var" varStatus="vs">
						<div data-src="static/login/images/${var.FILEPATH }"></div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div data-src="static/login/images/banner_slide_01.jpg"></div>
					<div data-src="static/login/images/banner_slide_02.jpg"></div>
					<div data-src="static/login/images/banner_slide_03.jpg"></div>
					<div data-src="static/login/images/banner_slide_04.jpg"></div>
					<div data-src="static/login/images/banner_slide_05.jpg"></div>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
	
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});
		
		jQuery(function() {
			var loginName = $.cookie('loginName');
			var password = $.cookie('password');
			if (typeof(loginName) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginName").val(loginName);
				$("#password").val(password);
				$("#saveId").attr("checked", true);
				$("#code").focus();
			}else{
				$("#loginName").focus();
			}
		});
		
		$(document).ready(function() {
			changeCode1();
			$("#codeImg").bind("click", changeCode1);
			$("#zcodeImg").bind("click", changeCode2);
		});
	
		function changeCode1() {
			var time = new Date();
			$("#codeImg").attr("src",
					"background/main/getCaptcha.do?t=" + time.getTime());
		}
		
		function changeCode2() {
			var time = new Date();
			$("#zcodeImg").attr("src",
					"background/main/getCaptcha.do?t=" + time.getTime());
		}
	
		//服务器校验
		function severCheck(){
			if(check()){
				var loginName = $("#loginName").val();
				var password = $("#password").val();
				var code = "ndknsdkfjksdfj"+loginName+",jx,"+password+"kgnlkfsl"+",jx,"+$("#code").val();
				$.ajax({
					type: "POST",
					url : 'background/main/login',
					data : {
						keyData : code,
						tm : new Date().getTime()
					},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							saveCookie();
							window.location.href="background/main/index";
						}else if("usererror" == data.result){
							$("#loginName").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							showShake();
							$("#loginName").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 15
							});
							showShake();
							$("#code").focus();
						}else{
							$("#loginName").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							showShake();
							$("#loginName").focus();
						}
					}
				});
			}
		}

		//客户端校验
		function check() {
			if ($("#loginName").val() == "") {
				$("#loginName").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showShake();
				$("#loginName").focus();
				return false;
			} else {
				$("#loginName").val(jQuery.trim($('#loginName').val()));
			}
			if ($("#password").val() == "") {
				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showShake();
				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {
				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showShake();
				$("#code").focus();
				return false;
			}
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveId").attr("checked")) {
				$.cookie('loginName', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginName").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveId").attr("checked")) {
				$.cookie('loginName', $("#loginName").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		
		
		
		//登录注册页面切换
		function changepage(value) {
			if(value == 1){
				$("#windows1").hide();
				$("#windows2").show();
				changeCode2();
			}else{
				$("#windows2").hide();
				$("#windows1").show();
				changeCode1();
			}
		}
		
	//注册
	function rcheck(){
		if($("#userName").val()==""){
			$("#userName").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#userName").focus();
			$("#userName").val('');
			return false;
		}else{
			$("#userName").val(jQuery.trim($('#userName').val()));
		}
		if($("#password").val()==""){
			$("#password").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#password").focus();
			return false;
		}
		if($("#password").val()!=$("#chkpwd").val()){
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#chkpwd").focus();
			return false;
		}
		if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		if($("#email").val()==""){
			$("#email").tips({
				side:3,
	            msg:'输入邮箱',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}else if(!ismail($("#email").val())){
			$("#email").tips({
				side:3,
	            msg:'邮箱格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}
		if ($("#rcode").val() == "") {
			$("#rcode").tips({
				side : 1,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});
			$("#rcode").focus();
			return false;
		}
		return true;
	}
	
	//提交注册
	function register(){
		if(rcheck()){
			var nowtime = date2str(new Date(),"yyyyMMdd");
			$.ajax({
				type: "POST",
				url: 'appSysUser/registerSysUser.do',
		    	data: {userName:$("#userName").val(),password:$("#password").val(),NAME:$("#name").val(),email:$("#email").val(),rcode:$("#rcode").val(),FKEY:$.md5('userName'+nowtime+',fh,'),tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					if("00" == data.result){
						$("#windows2").hide();
						$("#windows1").show();
						$("#loginbox").tips({
							side : 1,
							msg : '注册成功,请登录',
							bg : '#68B500',
							time : 3
						});
						changeCode1();
					}else if("04" == data.result){
						$("#userName").tips({
							side : 1,
							msg : "用户名已存在",
							bg : '#FF5080',
							time : 15
						});
						showShake();
						$("#userName").focus();
					}else if("06" == data.result){
						$("#rcode").tips({
							side : 1,
							msg : "验证码输入有误",
							bg : '#FF5080',
							time : 15
						});
						showShake();
						$("#rcode").focus();
					}
				}
			});
		}
	}
	
	//邮箱格式校验
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
	}
	//js  日期格式
	function date2str(x,y) {
	     var z ={y:x.getFullYear(),M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
	     return y.replace(/(y+|M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-(v.length>2?v.length:2))});
	 	};
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	<c:if test="${'1' == pd.msg}">
		<script type="text/javascript">
		$(tsMsg());
		function tsMsg(){
			alert('此用户在其它终端已经早于您登录,您暂时无法登录');
		}
		</script>
	</c:if>
	<c:if test="${'2' == pd.msg}">
		<script type="text/javascript">
			$(tsMsg());
			function tsMsg(){
				alert('您被系统管理员强制下线');
			}
		</script>
	</c:if>
	<script src="static/login/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-1.7.2.js"></script>
	<script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="static/login/js/camera.min.js"></script>
	<script src="static/login/js/templatemo_script.js"></script>
	<script src="static/login/js/ban.js"></script>
	<script type="text/javascript" src="static/js/jQuery.md5.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	
	<!-- 软键盘控件start -->
	<script type="text/javascript" src="static/login/keypad/js/form/keypad.js"></script>
	<script type="text/javascript" src="static/login/keypad/js/framework.js"></script>
	<!-- 软键盘控件end -->
</body>

</html>
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
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../main/bgIndexTop.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="background/user/${methodPath }.do" name="userForm" id="userForm" method="post">
						<input type="hidden" name="userId" id="userId" value="${bgUser.userId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">角色:</td>
								<td><param:select type="bg_roleEffective" name="roleId" id="roleId" value="${bgUser.roleId}" placeholder="这里请选择 角色" title="角色" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">后台用户代号:</td>
								<td><input type="text" name="userCode" id="userCode" value="${bgUser.userCode}" maxlength="100" placeholder="这里输入 后台用户代号" title="后台用户代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">密码:</td>
								<td><input type="password" name="password" id="password" maxlength="255" placeholder="这里输入 密码" title="密码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">确认密码:</td>
								<td><input type="password" id="chkpwd" maxlength="255" placeholder="这里再次输入 密码" title="确认密码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">后台用户名称:</td>
								<td><input type="text" name="userName" id="userName" value="${bgUser.userName}" maxlength="100" placeholder="这里输入 后台用户名称" title="后台用户名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">后台用户类型:</td>
								<td><param:select type="bg_userType" name="userType" id="userType" value="${bgUser.userType}" placeholder="这里请选择 后台用户类型" title="后台用户类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">电子邮箱:</td>
								<td><input type="text" name="email" id="email" value="${bgUser.email}" maxlength="100" placeholder="这里输入 电子邮箱" title="电子邮箱" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">手机号码:</td>
								<td><input type="text" name="phone" id="phone" value="${bgUser.phone}" maxlength="100" placeholder="这里输入 手机号码" title="手机号码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">备注信息:</td>
								<td><input type="text" name="remarks" id="remarks" value="${bgUser.remarks}" maxlength="255" placeholder="这里输入 备注信息" title="备注信息" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${bgUser.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../main/bgIndexFoot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		
		//判断userCode是否存在
		function otherNotCode(){
			var userCode = $("#userCode").val();
			if(userCode == "") return false;
			var userId = $("#userId").val();
			var url = "<%=basePath%>background/user/otherNotCode.do?userId="+userId+"&userCode="+userCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#userCode").tips({
						side:3,
			            msg:'后台用户代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#userCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			var emailExp = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
			var phoneExp = /^1[34578]\d{9}$/;
			if($("#roleId").val()==""){
				$("#roleId").next().tips({
					side:3,
		            msg:'请选择 角色',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!codeExp.test($("#userCode").val())){
				$("#userCode").tips({
					side:3,
		            msg:'请输入后台用户代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#userCode").focus();
			return false;
			}
			if($("#userId").val()=="" && $("#password").val()==""){
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
			if($("#userName").val()==""){
				$("#userName").tips({
					side:3,
		            msg:'请输入后台用户名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#userName").focus();
			return false;
			}
			if($("#userType").val()==""){
				$("#userType").next().tips({
					side:3,
		            msg:'请选择 后台用户类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!emailExp.test($("#email").val())){
				$("#email").tips({
					side:3,
		            msg:'请输入正确的电子邮箱',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#email").focus();
			return false;
			}
			if(!phoneExp.test($("#phone").val())){
				$("#phone").tips({
					side:3,
		            msg:'请输入正确的手机号码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#phone").focus();
			return false;
			}
			if(!intExp.test($("#orderNum").val())){
				$("#orderNum").tips({
					side:3,
		            msg:'排序编号 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#orderNum").focus();
			return false;
			}
			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
		//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		//下拉框
		if(!ace.vars['touch']) {
			$('.chosen-select').chosen({allow_single_deselect:true}); 
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			}).trigger('resize.chosen');
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if(event_name != 'sidebar_collapsed') return;
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			});
			$('#chosen-multiple-style .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
				 else $('#form-field-select-4').removeClass('tag-input-style');
			});
		}
	});
	</script>
</body>
</html>
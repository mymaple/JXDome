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
					
					<form action="background/appUser/${methodPath }.do" name="appUserForm" id="appUserForm" method="post">
						<input type="hidden" name="appUserId" id="appUserId" value="${comAppUser.appUserId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户代号:</td>
								<td><input type="text" name="appUserCode" id="appUserCode" value="${comAppUser.appUserCode}" maxlength="100" placeholder="这里输入 平台用户代号" title="平台用户代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户名称:</td>
								<td><input type="text" name="appUserName" id="appUserName" value="${comAppUser.appUserName}" maxlength="100" placeholder="这里输入 平台用户名称" title="平台用户名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户类型:</td>
								<td><param:select type="com_appUserType" name="appUserType" id="appUserType" value="${comAppUser.appUserType}" placeholder="这里请选择 平台用户类型" title="平台用户类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户编号:</td>
								<td><input type="text" name="appUserNum" id="appUserNum" value="${comAppUser.appUserNum}" maxlength="100" placeholder="这里输入 平台用户编号" title="平台用户编号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">电话号码:</td>
								<td><input type="text" name="phone" id="phone" value="${comAppUser.phone}" maxlength="100" placeholder="这里输入 电话号码" title="电话号码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">电子邮箱:</td>
								<td><input type="text" name="email" id="email" value="${comAppUser.email}" maxlength="100" placeholder="这里输入 电子邮箱" title="电子邮箱" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">密码:</td>
								<td><input type="text" name="password" id="password" value="${comAppUser.password}" maxlength="100" placeholder="这里输入 密码" title="密码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">用户的标识:</td>
								<td><input type="text" name="openId" id="openId" value="${comAppUser.openId}" maxlength="100" placeholder="这里输入 用户的标识" title="用户的标识" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">性别:</td>
								<td><input type="text" name="sex" id="sex" value="${comAppUser.sex}" maxlength="100" placeholder="这里输入 性别" title="性别" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">用户头像路径:</td>
								<td><input type="text" name="headImgUrl" id="headImgUrl" value="${comAppUser.headImgUrl}" maxlength="100" placeholder="这里输入 用户头像路径" title="用户头像路径" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">生日:</td>
								<td><input class="span10 date-picker" name="brithday" id="brithday" value="${comAppUser.brithday}" type="text" data-date-format="yyyy-mm-dd 00:00:00" readonly="readonly" placeholder="这里请选择 生日" title="生日" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级id:</td>
								<td><input type="text" name="parentId" id="parentId" value="${comAppUser.parentId}" maxlength="100" placeholder="这里输入 上级id" title="上级id" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信二维码地址:</td>
								<td><input type="text" name="wxQRcodeUrl" id="wxQRcodeUrl" value="${comAppUser.wxQRcodeUrl}" maxlength="100" placeholder="这里输入 微信二维码地址" title="微信二维码地址" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信二维码有效期:</td>
								<td><input class="span10 date-picker" name="wxQRcodeExpiry" id="wxQRcodeExpiry" value="${comAppUser.wxQRcodeExpiry}" type="text" data-date-format="yyyy-mm-dd 00:00:00" readonly="readonly" placeholder="这里请选择 微信二维码有效期" title="微信二维码有效期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">媒体文件id:</td>
								<td><input type="text" name="mediaId" id="mediaId" value="${comAppUser.mediaId}" maxlength="100" placeholder="这里输入 媒体文件id" title="媒体文件id" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">媒体文件有效时间:</td>
								<td><input class="span10 date-picker" name="mediaExpiry" id="mediaExpiry" value="${comAppUser.mediaExpiry}" type="text" data-date-format="yyyy-mm-dd 00:00:00" readonly="readonly" placeholder="这里请选择 媒体文件有效时间" title="媒体文件有效时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comAppUser.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断appUserCode是否存在
		function otherNotCode(){
			var appUserCode = $("#appUserCode").val();
			if(appUserCode == "") return false;
			var appUserId = $("#appUserId").val();
			var url = "<%=basePath%>background/appUser/otherNotCode.do?appUserId="+appUserId+"&appUserCode="+appUserCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#appUserCode").tips({
						side:3,
			            msg:'平台用户代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#appUserCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if(!codeExp.test($("#appUserCode").val())){
				$("#appUserCode").tips({
					side:3,
		            msg:'请输入平台用户代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#appUserCode").focus();
			return false;
			}
			if($("#appUserName").val()==""){
				$("#appUserName").tips({
					side:3,
		            msg:'请输入平台用户名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#appUserName").focus();
			return false;
			}
			if($("#appUserType").val()==""){
				$("#appUserType").next().tips({
					side:3,
		            msg:'请选择 平台用户类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#appUserNum").val()==""){
				$("#appUserNum").tips({
					side:3,
		            msg:'请输入平台用户编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#appUserNum").focus();
			return false;
			}
			if($("#phone").val()==""){
				$("#phone").tips({
					side:3,
		            msg:'请输入电话号码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#phone").focus();
			return false;
			}
			if($("#email").val()==""){
				$("#email").tips({
					side:3,
		            msg:'请输入电子邮箱',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#email").focus();
			return false;
			}
			if($("#password").val()==""){
				$("#password").tips({
					side:3,
		            msg:'请输入密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#password").focus();
			return false;
			}
			if($("#openId").val()==""){
				$("#openId").tips({
					side:3,
		            msg:'请输入用户的标识',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#openId").focus();
			return false;
			}
			if($("#sex").val()==""){
				$("#sex").tips({
					side:3,
		            msg:'请输入性别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#sex").focus();
			return false;
			}
			if($("#headImgUrl").val()==""){
				$("#headImgUrl").tips({
					side:3,
		            msg:'请输入用户头像路径',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#headImgUrl").focus();
			return false;
			}
			if($("#brithday").val()==""){
				$("#brithday").next().tips({
					side:3,
		            msg:'请选择 生日',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#parentId").val()==""){
				$("#parentId").tips({
					side:3,
		            msg:'请输入上级id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#parentId").focus();
			return false;
			}
			if($("#wxQRcodeUrl").val()==""){
				$("#wxQRcodeUrl").tips({
					side:3,
		            msg:'请输入微信二维码地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#wxQRcodeUrl").focus();
			return false;
			}
			if($("#wxQRcodeExpiry").val()==""){
				$("#wxQRcodeExpiry").next().tips({
					side:3,
		            msg:'请选择 微信二维码有效期',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#mediaId").val()==""){
				$("#mediaId").tips({
					side:3,
		            msg:'请输入媒体文件id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#mediaId").focus();
			return false;
			}
			if($("#mediaExpiry").val()==""){
				$("#mediaExpiry").next().tips({
					side:3,
		            msg:'请选择 媒体文件有效时间',
		            bg:'#AE81FF',
		            time:2
		        });
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
			$("#appUserForm").submit();
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
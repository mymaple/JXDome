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
					
					<form action="background/wxAccount/${methodPath }.do" name="wxAccountForm" id="wxAccountForm" method="post">
						<input type="hidden" name="wxAccountId" id="wxAccountId" value="${comWxAccount.wxAccountId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信账户代号:</td>
								<td><input type="text" name="wxAccountCode" id="wxAccountCode" value="${comWxAccount.wxAccountCode}" maxlength="100" placeholder="这里输入 微信账户代号" title="微信账户代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信账户名称:</td>
								<td><input type="text" name="wxAccountName" id="wxAccountName" value="${comWxAccount.wxAccountName}" maxlength="100" placeholder="这里输入 微信账户名称" title="微信账户名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信账户类型:</td>
								<td><param:select type="com_wxAccountType" name="wxAccountType" id="wxAccountType" value="${comWxAccount.wxAccountType}" placeholder="这里请选择 微信账户类型" title="微信账户类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">AppID(应用ID):</td>
								<td><input type="text" name="appId" id="appId" value="${comWxAccount.appId}" maxlength="100" placeholder="这里输入 AppID(应用ID)" title="AppID(应用ID)" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">AppSecret(应用密钥):</td>
								<td><input type="text" name="appSecret" id="appSecret" value="${comWxAccount.appSecret}" maxlength="100" placeholder="这里输入 AppSecret(应用密钥)" title="AppSecret(应用密钥)" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">Token(令牌):</td>
								<td><input type="text" name="token" id="token" value="${comWxAccount.token}" maxlength="100" placeholder="这里输入 Token(令牌)" title="Token(令牌)" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">微信支付商户号:</td>
								<td><input type="text" name="mchId" id="mchId" value="${comWxAccount.mchId}" maxlength="100" placeholder="这里输入 微信支付商户号" title="微信支付商户号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">API密钥:</td>
								<td><input type="text" name="apiKey" id="apiKey" value="${comWxAccount.apiKey}" maxlength="100" placeholder="这里输入 API密钥" title="API密钥" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comWxAccount.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断wxAccountCode是否存在
		function otherNotCode(){
			var wxAccountCode = $("#wxAccountCode").val();
			if(wxAccountCode == "") return false;
			var wxAccountId = $("#wxAccountId").val();
			var url = "<%=basePath%>background/wxAccount/otherNotCode.do?wxAccountId="+wxAccountId+"&wxAccountCode="+wxAccountCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#wxAccountCode").tips({
						side:3,
			            msg:'微信账户代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#wxAccountCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if(!codeExp.test($("#wxAccountCode").val())){
				$("#wxAccountCode").tips({
					side:3,
		            msg:'请输入微信账户代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#wxAccountCode").focus();
			return false;
			}
			if($("#wxAccountName").val()==""){
				$("#wxAccountName").tips({
					side:3,
		            msg:'请输入微信账户名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#wxAccountName").focus();
			return false;
			}
			if($("#wxAccountType").val()==""){
				$("#wxAccountType").next().tips({
					side:3,
		            msg:'请选择 微信账户类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#appId").val()==""){
				$("#appId").tips({
					side:3,
		            msg:'请输入AppID(应用ID)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#appId").focus();
			return false;
			}
			if($("#appSecret").val()==""){
				$("#appSecret").tips({
					side:3,
		            msg:'请输入AppSecret(应用密钥)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#appSecret").focus();
			return false;
			}
			if($("#token").val()==""){
				$("#token").tips({
					side:3,
		            msg:'请输入Token(令牌)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#token").focus();
			return false;
			}
			if($("#mchId").val()==""){
				$("#mchId").tips({
					side:3,
		            msg:'请输入微信支付商户号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#mchId").focus();
			return false;
			}
			if($("#apiKey").val()==""){
				$("#apiKey").tips({
					side:3,
		            msg:'请输入API密钥',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#apiKey").focus();
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
			$("#wxAccountForm").submit();
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
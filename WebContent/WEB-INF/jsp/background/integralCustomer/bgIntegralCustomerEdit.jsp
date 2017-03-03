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
					
					<form action="background/integralCustomer/${methodPath }.do" name="integralCustomerForm" id="integralCustomerForm" method="post">
						<input type="hidden" name="integralCustomerId" id="integralCustomerId" value="${comIntegralCustomer.integralCustomerId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级名称:</td>
								<td align="center"><param:display type="bg_mapleDetailType" name="parentId" id="parentId" value="${comIntegralCustomer.parentId }" hidden="true"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">角色:</td>
								<td><param:select type="com_integralCustomerRole" name="roleId" id="roleId" value="${comIntegralCustomer.roleId}" placeholder="这里请选择 角色" title="角色" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">积分客户代号:</td>
								<td><input type="text" name="integralCustomerCode" id="integralCustomerCode" value="${comIntegralCustomer.integralCustomerCode}" maxlength="100" placeholder="这里输入 积分客户代号" title="积分客户代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">积分客户名称:</td>
								<td><input type="text" name="integralCustomerName" id="integralCustomerName" value="${comIntegralCustomer.integralCustomerName}" maxlength="100" placeholder="这里输入 积分客户名称" title="积分客户名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">积分客户类型:</td>
								<td><param:select type="com_integralCustomerType" name="integralCustomerType" id="integralCustomerType" value="${comIntegralCustomer.integralCustomerType}" placeholder="这里请选择 积分客户类型" title="积分客户类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">手机号码:</td>
								<td><input type="text" name="phone" id="phone" value="${comIntegralCustomer.phone}" maxlength="100" placeholder="这里输入 手机号码" title="手机号码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">密码:</td>
								<td><input type="text" name="password" id="password" value="${comIntegralCustomer.password}" maxlength="100" placeholder="这里输入 密码" title="密码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">备注信息:</td>
								<td><input type="text" name="remarks" id="remarks" value="${comIntegralCustomer.remarks}" maxlength="100" placeholder="这里输入 备注信息" title="备注信息" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comIntegralCustomer.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断integralCustomerCode是否存在
		function otherNotCode(){
			var integralCustomerCode = $("#integralCustomerCode").val();
			if(integralCustomerCode == "") return false;
			var integralCustomerId = $("#integralCustomerId").val();
			var url = "<%=basePath%>background/integralCustomer/otherNotCode.do?integralCustomerId="+integralCustomerId+"&integralCustomerCode="+integralCustomerCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#integralCustomerCode").tips({
						side:3,
			            msg:'积分客户代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#integralCustomerCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#roleId").val()==""){
				$("#roleId").next().tips({
					side:3,
		            msg:'请选择 角色',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!codeExp.test($("#integralCustomerCode").val())){
				$("#integralCustomerCode").tips({
					side:3,
		            msg:'请输入积分客户代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#integralCustomerCode").focus();
			return false;
			}
			if($("#integralCustomerName").val()==""){
				$("#integralCustomerName").tips({
					side:3,
		            msg:'请输入积分客户名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#integralCustomerName").focus();
			return false;
			}
			if($("#integralCustomerType").val()==""){
				$("#integralCustomerType").next().tips({
					side:3,
		            msg:'请选择 积分客户类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#phone").val()==""){
				$("#phone").tips({
					side:3,
		            msg:'请输入手机号码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#phone").focus();
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
			if($("#remarks").val()==""){
				$("#remarks").tips({
					side:3,
		            msg:'请输入备注信息',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#remarks").focus();
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
			$("#integralCustomerForm").submit();
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
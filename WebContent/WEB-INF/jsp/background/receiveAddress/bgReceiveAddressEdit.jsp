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
					
					<form action="background/receiveAddress/${methodPath }.do" name="receiveAddressForm" id="receiveAddressForm" method="post">
						<input type="hidden" name="receiveAddressId" id="receiveAddressId" value="${comReceiveAddress.receiveAddressId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户:</td>
								<td><param:select type="com_appUserEffective" name="appUserId" id="appUserId" value="${comReceiveAddress.appUserId}" placeholder="这里请选择 平台用户" title="平台用户" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">收货人:</td>
								<td><input type="text" name="receicerName" id="receicerName" value="${comReceiveAddress.receicerName}" maxlength="100" placeholder="这里输入 收货人" title="收货人" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">手机号码:</td>
								<td><input type="text" name="phone" id="phone" value="${comReceiveAddress.phone}" maxlength="100" placeholder="这里输入 手机号码" title="手机号码" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">省:</td>
								<td><input type="text" name="province" id="province" value="${comReceiveAddress.province}" maxlength="100" placeholder="这里输入 省" title="省" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">城市:</td>
								<td><input type="text" name="city" id="city" value="${comReceiveAddress.city}" maxlength="100" placeholder="这里输入 城市" title="城市" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">区:</td>
								<td><input type="text" name="district" id="district" value="${comReceiveAddress.district}" maxlength="100" placeholder="这里输入 区" title="区" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">街道:</td>
								<td><input type="text" name="street" id="street" value="${comReceiveAddress.street}" maxlength="100" placeholder="这里输入 街道" title="街道" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">详细地址:</td>
								<td><input type="text" name="detail" id="detail" value="${comReceiveAddress.detail}" maxlength="100" placeholder="这里输入 详细地址" title="详细地址" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">默认状态:</td>
								<td><param:select type="com_defaultStatus" name="defaultStatus" id="defaultStatus" value="${comReceiveAddress.defaultStatus}" placeholder="这里请选择 默认状态" title="默认状态" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comReceiveAddress.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断receiveAddressCode是否存在
		function otherNotCode(){
			var receiveAddressCode = $("#receiveAddressCode").val();
			if(receiveAddressCode == "") return false;
			var receiveAddressId = $("#receiveAddressId").val();
			var url = "<%=basePath%>background/receiveAddress/otherNotCode.do?receiveAddressId="+receiveAddressId+"&receiveAddressCode="+receiveAddressCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#receiveAddressCode").tips({
						side:3,
			            msg:'收货地址代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#receiveAddressCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#appUserId").val()==""){
				$("#appUserId").next().tips({
					side:3,
		            msg:'请选择 平台用户',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#receicerName").val()==""){
				$("#receicerName").tips({
					side:3,
		            msg:'请输入收货人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#receicerName").focus();
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
			if($("#province").val()==""){
				$("#province").tips({
					side:3,
		            msg:'请输入省',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#province").focus();
			return false;
			}
			if($("#city").val()==""){
				$("#city").tips({
					side:3,
		            msg:'请输入城市',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#city").focus();
			return false;
			}
			if($("#district").val()==""){
				$("#district").tips({
					side:3,
		            msg:'请输入区',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#district").focus();
			return false;
			}
			if($("#street").val()==""){
				$("#street").tips({
					side:3,
		            msg:'请输入街道',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#street").focus();
			return false;
			}
			if($("#detail").val()==""){
				$("#detail").tips({
					side:3,
		            msg:'请输入详细地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#detail").focus();
			return false;
			}
			if($("#defaultStatus").val()==""){
				$("#defaultStatus").next().tips({
					side:3,
		            msg:'请选择 默认状态',
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
			$("#receiveAddressForm").submit();
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
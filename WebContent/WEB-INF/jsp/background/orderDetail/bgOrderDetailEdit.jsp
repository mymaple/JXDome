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
					
					<form action="background/orderDetail/${methodPath }.do" name="orderDetailForm" id="orderDetailForm" method="post">
						<input type="hidden" name="orderDetailId" id="orderDetailId" value="${comOrderDetail.orderDetailId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级名称:</td>
								<td align="center"><param:display type="bg_mapleDetailType" name="orderId" id="orderId" value="${comOrderDetail.orderId }" hidden="true"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品Id:</td>
								<td><input type="text" name="productId" id="productId" value="${comOrderDetail.productId}" maxlength="100" placeholder="这里输入 商品Id" title="商品Id" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品名称:</td>
								<td><input type="text" name="productName" id="productName" value="${comOrderDetail.productName}" maxlength="100" placeholder="这里输入 商品名称" title="商品名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">摘要:</td>
								<td><input type="text" name="summary" id="summary" value="${comOrderDetail.summary}" maxlength="100" placeholder="这里输入 摘要" title="摘要" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品类型名称:</td>
								<td><input type="text" name="productStyleName" id="productStyleName" value="${comOrderDetail.productStyleName}" maxlength="100" placeholder="这里输入 商品类型名称" title="商品类型名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品头像:</td>
								<td><input type="text" name="headImgSrc" id="headImgSrc" value="${comOrderDetail.headImgSrc}" maxlength="100" placeholder="这里输入 产品头像" title="产品头像" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">原价:</td>
								<td><input type="number" name="originalPrice" id="originalPrice" value="${comOrderDetail.originalPrice}" maxlength="100" placeholder="这里输入 原价" title="原价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">现价:</td>
								<td><input type="number" name="currentPrice" id="currentPrice" value="${comOrderDetail.currentPrice}" maxlength="100" placeholder="这里输入 现价" title="现价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">购买数量:</td>
								<td><input type="number" name="count" id="count" value="${comOrderDetail.count}" maxlength="100" placeholder="这里输入 购买数量" title="购买数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comOrderDetail.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断orderDetailCode是否存在
		function otherNotCode(){
			var orderDetailCode = $("#orderDetailCode").val();
			if(orderDetailCode == "") return false;
			var orderDetailId = $("#orderDetailId").val();
			var url = "<%=basePath%>background/orderDetail/otherNotCode.do?orderDetailId="+orderDetailId+"&orderDetailCode="+orderDetailCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#orderDetailCode").tips({
						side:3,
			            msg:'订单商品代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#orderDetailCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#productId").val()==""){
				$("#productId").tips({
					side:3,
		            msg:'请输入商品Id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productId").focus();
			return false;
			}
			if($("#productName").val()==""){
				$("#productName").tips({
					side:3,
		            msg:'请输入商品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productName").focus();
			return false;
			}
			if($("#summary").val()==""){
				$("#summary").tips({
					side:3,
		            msg:'请输入摘要',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#summary").focus();
			return false;
			}
			if($("#productStyleName").val()==""){
				$("#productStyleName").tips({
					side:3,
		            msg:'请输入商品类型名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productStyleName").focus();
			return false;
			}
			if($("#headImgSrc").val()==""){
				$("#headImgSrc").tips({
					side:3,
		            msg:'请输入产品头像',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#headImgSrc").focus();
			return false;
			}
			if(!intExp.test($("#originalPrice").val())){
				$("#originalPrice").tips({
					side:3,
		            msg:'请输入原价 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#originalPrice").focus();
			return false;
			}
			if(!intExp.test($("#currentPrice").val())){
				$("#currentPrice").tips({
					side:3,
		            msg:'请输入现价 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#currentPrice").focus();
			return false;
			}
			if(!intExp.test($("#count").val())){
				$("#count").tips({
					side:3,
		            msg:'请输入购买数量 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#count").focus();
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
			$("#orderDetailForm").submit();
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
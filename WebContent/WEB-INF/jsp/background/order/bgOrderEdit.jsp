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
					
					<form action="background/order/${methodPath }.do" name="orderForm" id="orderForm" method="post">
						<input type="hidden" name="orderId" id="orderId" value="${comOrder.orderId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单代号:</td>
								<td><input type="text" name="orderCode" id="orderCode" value="${comOrder.orderCode}" maxlength="100" placeholder="这里输入 订单代号" title="订单代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单名称:</td>
								<td><input type="text" name="orderName" id="orderName" value="${comOrder.orderName}" maxlength="100" placeholder="这里输入 订单名称" title="订单名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单类型:</td>
								<td><param:select type="com_orderType" name="orderType" id="orderType" value="${comOrder.orderType}" placeholder="这里请选择 订单类型" title="订单类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单状态:</td>
								<td><input type="text" name="orderStatus" id="orderStatus" value="${comOrder.orderStatus}" maxlength="100" placeholder="这里输入 订单状态" title="订单状态" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户:</td>
								<td><param:select type="com_appUserEffective" name="appUserId" id="appUserId" value="${comOrder.appUserId}" placeholder="这里请选择 平台用户" title="平台用户" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单商品总数:</td>
								<td><input type="number" name="orderProductCount" id="orderProductCount" value="${comOrder.orderProductCount}" maxlength="100" placeholder="这里输入 订单商品总数" title="订单商品总数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品总价:</td>
								<td><input type="number" name="allPrice" id="allPrice" value="${comOrder.allPrice}" maxlength="100" placeholder="这里输入 商品总价" title="商品总价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">运费:</td>
								<td><input type="number" name="freight" id="freight" value="${comOrder.freight}" maxlength="100" placeholder="这里输入 运费" title="运费" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">总优惠:</td>
								<td><input type="number" name="allDisPrice" id="allDisPrice" value="${comOrder.allDisPrice}" maxlength="100" placeholder="这里输入 总优惠" title="总优惠" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">钱包支付:</td>
								<td><input type="number" name="walletPay" id="walletPay" value="${comOrder.walletPay}" maxlength="100" placeholder="这里输入 钱包支付" title="钱包支付" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">实付款:</td>
								<td><input type="number" name="allActPrice" id="allActPrice" value="${comOrder.allActPrice}" maxlength="100" placeholder="这里输入 实付款" title="实付款" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">供应商id:</td>
								<td><param:select type="com_supplierEffective" name="supplierId" id="supplierId" value="${comOrder.supplierId}" placeholder="这里请选择 供应商id" title="供应商id" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">收货地址:</td>
								<td><param:select type="com_receiveAddressEffective" name="receiveAddressId" id="receiveAddressId" value="${comOrder.receiveAddressId}" placeholder="这里请选择 收货地址" title="收货地址" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">交易号:</td>
								<td><input type="text" name="tradeNum" id="tradeNum" value="${comOrder.tradeNum}" maxlength="100" placeholder="这里输入 交易号" title="交易号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">付款方式:</td>
								<td><param:select type="com_payMethod" name="payMethod" id="payMethod" value="${comOrder.payMethod}" placeholder="这里请选择 付款方式" title="付款方式" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">物流公司:</td>
								<td><input type="text" name="wlgs" id="wlgs" value="${comOrder.wlgs}" maxlength="100" placeholder="这里输入 物流公司" title="物流公司" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">运单编号:</td>
								<td><input type="text" name="wlNum" id="wlNum" value="${comOrder.wlNum}" maxlength="100" placeholder="这里输入 运单编号" title="运单编号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单备注:</td>
								<td><input type="text" name="remark" id="remark" value="${comOrder.remark}" maxlength="1000" placeholder="这里输入 订单备注" title="订单备注" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comOrder.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断orderCode是否存在
		function otherNotCode(){
			var orderCode = $("#orderCode").val();
			if(orderCode == "") return false;
			var orderId = $("#orderId").val();
			var url = "<%=basePath%>background/order/otherNotCode.do?orderId="+orderId+"&orderCode="+orderCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#orderCode").tips({
						side:3,
			            msg:'订单代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#orderCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if(!codeExp.test($("#orderCode").val())){
				$("#orderCode").tips({
					side:3,
		            msg:'请输入订单代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#orderCode").focus();
			return false;
			}
			if($("#orderName").val()==""){
				$("#orderName").tips({
					side:3,
		            msg:'请输入订单名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#orderName").focus();
			return false;
			}
			if($("#orderType").val()==""){
				$("#orderType").next().tips({
					side:3,
		            msg:'请选择 订单类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#orderStatus").val()==""){
				$("#orderStatus").tips({
					side:3,
		            msg:'请输入订单状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#orderStatus").focus();
			return false;
			}
			if($("#appUserId").val()==""){
				$("#appUserId").next().tips({
					side:3,
		            msg:'请选择 平台用户',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#orderProductCount").val())){
				$("#orderProductCount").tips({
					side:3,
		            msg:'请输入订单商品总数 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#orderProductCount").focus();
			return false;
			}
			if(!intExp.test($("#allPrice").val())){
				$("#allPrice").tips({
					side:3,
		            msg:'请输入商品总价 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#allPrice").focus();
			return false;
			}
			if(!intExp.test($("#freight").val())){
				$("#freight").tips({
					side:3,
		            msg:'请输入运费 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#freight").focus();
			return false;
			}
			if(!intExp.test($("#allDisPrice").val())){
				$("#allDisPrice").tips({
					side:3,
		            msg:'请输入总优惠 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#allDisPrice").focus();
			return false;
			}
			if(!intExp.test($("#walletPay").val())){
				$("#walletPay").tips({
					side:3,
		            msg:'请输入钱包支付 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#walletPay").focus();
			return false;
			}
			if(!intExp.test($("#allActPrice").val())){
				$("#allActPrice").tips({
					side:3,
		            msg:'请输入实付款 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#allActPrice").focus();
			return false;
			}
			if($("#supplierId").val()==""){
				$("#supplierId").next().tips({
					side:3,
		            msg:'请选择 供应商id',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#receiveAddressId").val()==""){
				$("#receiveAddressId").next().tips({
					side:3,
		            msg:'请选择 收货地址',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#tradeNum").val()==""){
				$("#tradeNum").tips({
					side:3,
		            msg:'请输入交易号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#tradeNum").focus();
			return false;
			}
			if($("#payMethod").val()==""){
				$("#payMethod").next().tips({
					side:3,
		            msg:'请选择 付款方式',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#wlgs").val()==""){
				$("#wlgs").tips({
					side:3,
		            msg:'请输入物流公司',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#wlgs").focus();
			return false;
			}
			if($("#wlNum").val()==""){
				$("#wlNum").tips({
					side:3,
		            msg:'请输入运单编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#wlNum").focus();
			return false;
			}
			if($("#remark").val()==""){
				$("#remark").tips({
					side:3,
		            msg:'请输入订单备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#remark").focus();
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
			$("#orderForm").submit();
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
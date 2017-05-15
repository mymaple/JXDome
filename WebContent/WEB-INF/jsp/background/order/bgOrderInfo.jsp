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
								<td style="text-align: left;" colspan="10">
									订单基本信息
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单代号:</td>
								<td>${comOrder.orderCode}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">平台用户:</td>
								<td><param:display type="com_appUserEffective" value="${comOrder.appUserId}"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单类型:</td>
								<td><param:display type="com_orderType" value="${comOrder.orderType}"/></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单状态:</td>
								<td><param:display type="com_orderStatus" value="${comOrder.orderStatus}"/></td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="10">
									收货人信息
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">收货人:</td>
								<td>${comReceiveAddress.receicerName}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">手机号码:</td>
								<td>${comReceiveAddress.phone}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">省:</td>
								<td>${comReceiveAddress.province}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">城市:</td>
								<td>${comReceiveAddress.city}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">区:</td>
								<td>${comReceiveAddress.district}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">街道:</td>
								<td>${comReceiveAddress.street}</td></tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;" >详细地址:</td>
								<td colspan="3">${comReceiveAddress.detail}</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="10">
									购买商品信息
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">供应商:</td>
								<td colspan="3"><param:display type="com_supplierEffective" value="${comOrder.supplierId}" /></td>
							</tr>
							
							<c:choose>
					        <c:when test="${not empty comOrderDetailList}">
							<c:forEach items="${comOrderDetailList}" var="comOrderDetail" varStatus="vs">
							<tr>
								<td></td>
								<td>${comOrderDetail.productName }</td>
								<td>${comOrderDetail.productStyleName }</td>
								<td>*${comOrderDetail.count}</td>
							</tr>
							</c:forEach>
							</c:when>
							</c:choose>
							
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单商品总数:</td>
								<td>${comOrder.orderProductCount}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品总价:</td>
								<td>${comOrder.allPrice}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">钱包支付:</td>
								<td>${comOrder.walletPay}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">运费:</td>
								<td>${comOrder.freight}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">总优惠:</td>
								<td>${comOrder.allDisPrice}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">实付款:</td>
								<td>${comOrder.allActPrice}</td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="10">
									三方信息
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">交易号:</td>
								<td>${comOrder.tradeNum}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">付款方式:</td>
								<td><param:display type="com_payMethod" value="${comOrder.payMethod}"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">物流公司:</td>
								<td><param:display type="com_wlgs" value="${comOrder.wlgs}" /></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">运单编号:</td>
								<td>${comOrder.wlNum}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单备注:</td>
								<td>${comOrder.remark}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td>${comOrder.orderNum}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td>${comOrder.createTimeStr}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">付款时间:</td>
								<td>${comOrder.payTimeStr}</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">发货时间:</td>
								<td>${comOrder.sendTimeStr}</td>
								<td style="width:100px;text-align: right;padding-top: 13px;">完成时间:</td>
								<td>${comOrder.sendTimeStr}</td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
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
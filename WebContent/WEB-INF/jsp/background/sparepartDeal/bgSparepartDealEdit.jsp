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
					
					<form action="background/sparepartDeal/${methodPath }.do" name="sparepartDealForm" id="sparepartDealForm" method="post">
						<input type="hidden" name="sparepartDealId" id="sparepartDealId" value="${comSparepartDeal.sparepartDealId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零部件交易订单号:</td>
								<td><input type="text" name="sparepartDealCode" id="sparepartDealCode" value="${comSparepartDeal.sparepartDealCode}" maxlength="100" placeholder="这里输入 零部件交易订单号" title="零部件交易订单号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零部件销售客户:</td>
								<td><param:select type="" name="appUserId" id="appUserId" value="${comSparepartDeal.appUserId}" placeholder="这里请选择 零部件销售客户" title="零部件销售客户" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零部件:</td>
								<td><param:select type="com_sparepartEffective" name="sparepartId" id="sparepartId" value="${comSparepartDeal.sparepartId}" placeholder="这里请选择 零部件" title="零部件" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">出售数量:</td>
								<td><input type="number" name="count" id="count" value="${comSparepartDeal.count}" maxlength="100" placeholder="这里输入 出售数量" title="出售数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">交易金额:</td>
								<td><input type="number" name="dealAmt" id="dealAmt" value="${comSparepartDeal.dealAmt}" maxlength="100" placeholder="这里输入 交易金额" title="交易金额" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">订单日期:</td>
								<td><input class="span10 date-picker" name="orderTimeStr" id="orderTime" value="${comSparepartDeal.orderTimeStr}" type="text" data-date-format="yyyy-mm-dd 00:00:00" readonly="readonly" placeholder="这里请选择 订单日期" title="订单日期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comSparepartDeal.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断sparepartDealCode是否存在
		function otherNotCode(){
			var sparepartDealCode = $("#sparepartDealCode").val();
			if(sparepartDealCode == "") return false;
			var sparepartDealId = $("#sparepartDealId").val();
			var url = "<%=basePath%>background/sparepartDeal/otherNotCode.do?sparepartDealId="+sparepartDealId+"&sparepartDealCode="+sparepartDealCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#sparepartDealCode").tips({
						side:3,
			            msg:'零部件交易代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#sparepartDealCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#sparepartDealCode").val()==""){
				$("#sparepartDealCode").tips({
					side:3,
		            msg:'请输入 零部件交易订单号',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			
			if($("#appUserId").val()==""){
				$("#appUserId").next().tips({
					side:3,
		            msg:'请选择 零部件销售客户',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#sparepartId").val()==""){
				$("#sparepartId").next().tips({
					side:3,
		            msg:'请选择 零部件',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#count").val())){
				$("#count").tips({
					side:3,
		            msg:'请输入出售数量 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#count").focus();
			return false;
			}
			if(!intExp.test($("#dealAmt").val())){
				$("#dealAmt").tips({
					side:3,
		            msg:'请输入交易金额 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#dealAmt").focus();
			return false;
			}
			if($("#orderTime").val()==""){
				$("#orderTime").next().tips({
					side:3,
		            msg:'请选择 订单日期',
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
			$("#sparepartDealForm").submit();
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
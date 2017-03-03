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
					
					<form action="background/sparepart/${methodPath }.do" name="sparepartForm" id="sparepartForm" method="post">
						<input type="hidden" name="sparepartId" id="sparepartId" value="${comSparepart.sparepartId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零配件代号:</td>
								<td><input type="text" name="sparepartCode" id="sparepartCode" value="${comSparepart.sparepartCode}" maxlength="100" placeholder="这里输入 零配件代号" title="零配件代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零配件名称:</td>
								<td><input type="text" name="sparepartName" id="sparepartName" value="${comSparepart.sparepartName}" maxlength="100" placeholder="这里输入 零配件名称" title="零配件名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">零配件类型:</td>
								<td><param:select type="com_sparepartType" name="sparepartType" id="sparepartType" value="${comSparepart.sparepartType}" placeholder="这里请选择 零配件类型" title="零配件类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">总积分:</td>
								<td><input type="number" name="allIntegral" id="allIntegral" value="${comSparepart.allIntegral}" maxlength="100" placeholder="这里输入 总积分" title="总积分" style="width:98%;" onkeyup="integralChange();"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">大区经理可获积分:</td>
								<td><input type="number" name="integral1" id="integral1" value="${comSparepart.integral1}" maxlength="100" placeholder="这里输入 大区经理可获积分" title="大区经理可获积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">小区经理可获积分:</td>
								<td><input type="number" name="integral2" id="integral2" value="${comSparepart.integral2}" maxlength="100" placeholder="这里输入 小区经理可获积分" title="小区经理可获积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">4S店可获积分:</td>
								<td><input type="number" name="integral3" id="integral3" value="${comSparepart.integral3}" maxlength="100" placeholder="这里输入 4S店可获积分" title="4S店可获积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comSparepart.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		
		function integralChange(){
			var allIntegral = $("#allIntegral").val();
			$("#integral1").val(allIntegral*0.05);
			$("#integral2").val(allIntegral*0.15);
			$("#integral3").val(allIntegral*0.8);
		}
		
		
		//判断sparepartCode是否存在
		function otherNotCode(){
			var sparepartCode = $("#sparepartCode").val();
			if(sparepartCode == "") return false;
			var sparepartId = $("#sparepartId").val();
			var url = "<%=basePath%>background/sparepart/otherNotCode.do?sparepartId="+sparepartId+"&sparepartCode="+sparepartCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#sparepartCode").tips({
						side:3,
			            msg:'零配件代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#sparepartCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#sparepartCode").val()==""){
				$("#sparepartCode").tips({
					side:3,
		            msg:'请输入零配件代号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#sparepartCode").focus();
			return false;
			}
			if($("#sparepartName").val()==""){
				$("#sparepartName").tips({
					side:3,
		            msg:'请输入零配件名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#sparepartName").focus();
			return false;
			}
			if($("#sparepartType").val()==""){
				$("#sparepartType").next().tips({
					side:3,
		            msg:'请选择 零配件类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#allIntegral").val())){
				$("#allIntegral").tips({
					side:3,
		            msg:'请输入总积分 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#allIntegral").focus();
			return false;
			}
			if(!intExp.test($("#integral1").val())){
				$("#integral1").tips({
					side:3,
		            msg:'请输入大区经理可获积分 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#integral1").focus();
			return false;
			}
			if(!intExp.test($("#integral2").val())){
				$("#integral2").tips({
					side:3,
		            msg:'请输入小区经理可获积分 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#integral2").focus();
			return false;
			}
			if(!intExp.test($("#integral3").val())){
				$("#integral3").tips({
					side:3,
		            msg:'请输入4S店可获积分 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#integral3").focus();
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
			$("#sparepartForm").submit();
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
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
					
					<form action="background/mapleDetail/${methodPath }.do" name="mapleDetailForm" id="mapleDetailForm" method="post">
						<input type="hidden" name="mapleDetailId" id="mapleDetailId" value="${bgMapleDetail.mapleDetailId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级名称:</td>
								<td align="center"><param:display type="bg_mapleEffective" name="mapleId" id="mapleId" value="${bgMapleDetail.mapleId }" hidden="true"/></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">代码生成详情代号:</td>
								<td><input type="text" name="mapleDetailCode" id="mapleDetailCode" value="${bgMapleDetail.mapleDetailCode}" maxlength="100" placeholder="这里输入 代码生成详情代号" title="代码生成详情代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">代码生成详情名称:</td>
								<td><input type="text" name="mapleDetailName" id="mapleDetailName" value="${bgMapleDetail.mapleDetailName}" maxlength="100" placeholder="这里输入 代码生成详情名称" title="代码生成详情名称" style="width:98%;" /></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">代码生成详情类型:</td>
								<td><param:select type="bg_mapleDetailType" name="mapleDetailType" id="mapleDetailType" value="${bgMapleDetail.mapleDetailType}" placeholder="这里请选择 代码生成详情类型" title="代码生成详情类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">总长度:</td>
								<td><input type="number" name="totalLength" id="totalLength" value="${bgMapleDetail.totalLength}" placeholder="这里输入 总长度" title="总长度" style="width:98%;"/></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">小数长度:</td>
								<td><input type="number" name="decimalLength" id="decimalLength" value="${bgMapleDetail.decimalLength}" placeholder="这里输入 小数长度" title="小数长度" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">类型代号:</td>
								<td><input type="text" name="typeCode" id="typeCode" value="${bgMapleDetail.typeCode}" maxlength="100" placeholder="这里输入 类型代号" title="类型代号" style="width:98%;" /></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">是否主键:</td>
								<td><param:select type="com_sf" name="isKey" id="isKey" value="${bgMapleDetail.isKey}" placeholder="这里输入 是否主键" title="是否主键" cssClass="chosen-select form-control" styleClass="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">是否录入:</td>
								<td><param:select type="com_sf" name="isEdit" id="isEdit" value="${bgMapleDetail.isEdit}" placeholder="这里输入 是否录入" title="是否录入" cssClass="chosen-select form-control" styleClass="width:98%;" /></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">是否null:</td>
								<td><param:select type="com_sf" name="isNull" id="isNull" value="${bgMapleDetail.isNull}" placeholder="这里输入 是否null" title="是否null" cssClass="chosen-select form-control" styleClass="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">默认值:</td>
								<td><input type="text" name="defaultValue" id="defaultValue" value="${bgMapleDetail.defaultValue}" maxlength="100" placeholder="这里输入 默认值" title="默认值" style="width:98%;" /></td>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${bgMapleDetail.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断mapleDetailCode是否存在
		function otherNotCode(){
			var mapleDetailCode = $("#mapleDetailCode").val();
			if(mapleDetailCode == "") return false;
			var mapleId = $("#mapleId").val();
			var mapleDetailId = $("#mapleDetailId").val();
			var url = "<%=basePath%>background/mapleDetail/otherNotCode.do?mapleId="+mapleId+"&mapleDetailId="+mapleDetailId+"&mapleDetailCode="+mapleDetailCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#mapleDetailCode").tips({
						side:3,
			            msg:'代码生成详情代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#mapleDetailCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if(!codeExp.test($("#mapleDetailCode").val())){
				$("#mapleDetailCode").tips({
					side:3,
		            msg:'请输入代码生成详情代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#mapleDetailCode").focus();
			return false;
			}
			if($("#mapleDetailName").val()==""){
				$("#mapleDetailName").tips({
					side:3,
		            msg:'请输入代码生成详情名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#mapleDetailName").focus();
			return false;
			}
			if($("#mapleDetailType").val()==""){
				$("#mapleDetailType").next().tips({
					side:3,
		            msg:'请选择 代码生成详情类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#totalLength").val())){
				$("#totalLength").tips({
					side:3,
		            msg:'请输入总长度 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#totalLength").focus();
			return false;
			}
			if(!intExp.test($("#decimalLength").val())){
				$("#decimalLength").tips({
					side:3,
		            msg:'请输入小数长度 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#decimalLength").focus();
			return false;
			}
			/**
			if($("#typeCode").val()==""){
				$("#typeCode").tips({
					side:3,
		            msg:'请输入类型代号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#typeCode").focus();
			return false;
			}
			if($("#isKey").val()==""){
				$("#isKey").tips({
					side:3,
		            msg:'请输入是否主键',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#isKey").focus();
			return false;
			}
			if($("#isEdit").val()==""){
				$("#isEdit").tips({
					side:3,
		            msg:'请输入是否录入',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#isEdit").focus();
			return false;
			}
			if($("#isNull").val()==""){
				$("#isNull").tips({
					side:3,
		            msg:'请输入是否null',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#isNull").focus();
			return false;
			}
			if($("#defaultValue").val()==""){
				$("#defaultValue").tips({
					side:3,
		            msg:'请输入默认值',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#defaultValue").focus();
			return false;
			} */
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
			$("#mapleDetailForm").submit();
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
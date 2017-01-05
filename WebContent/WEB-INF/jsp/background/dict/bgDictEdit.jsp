<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					
					<form action="background/dict/${pathMsg }.do" name="dictForm" id="dictForm" method="post">
						<input type="hidden" name="dictId" id="dictId" value="${comDict.dictId}"/>
						<input type="hidden" name="parentId" id="parentId" value="${comDict.parentId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据字典代号:</td>
								<td><input type="text" name="dictCode" id="dictCode" value="${comDict.dictCode}" maxlength="100" placeholder="这里输入数据字典代号" title="数据字典代号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据字典名称:</td>
								<td><input type="text" name="dictName" id="dictName" value="${comDict.dictName}" maxlength="100" placeholder="这里输入数据字典名称" title="数据字典名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据字典类型:</td>
								<td><input type="text" name="dictType" id="dictType" value="${comDict.dictType}" maxlength="100" placeholder="这里输入数据字典类型" title="数据字典类型" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数据字典值:</td>
								<td><input type="text" name="dictValue" id="dictValue" value="${comDict.dictValue}" maxlength="100" placeholder="这里输入数据字典值" title="数据字典值" style="width:98%;"/></td>
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
		//保存
		function save(){
			if($("#dictCode").val()==""){
				$("#dictCode").tips({
					side:3,
		            msg:'请输入数据字典代号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#dictCode").focus();
			return false;
			}
			if($("#dictName").val()==""){
				$("#dictName").tips({
					side:3,
		            msg:'请输入数据字典名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#dictName").focus();
			return false;
			}
			if($("#dictType").val()==""){
				$("#dictType").tips({
					side:3,
		            msg:'请输入数据字典类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#dictType").focus();
			return false;
			}
			if($("#dictValue").val()==""){
				$("#dictValue").tips({
					side:3,
		            msg:'请输入数据字典值',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#dictValue").focus();
			return false;
			}
			$("#dictForm").submit();
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
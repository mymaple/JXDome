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
					
					<form action="background/crontab/${methodPath }.do" name="crontabForm" id="crontabForm" method="post">
						<input type="hidden" name="crontabId" id="crontabId" value="${bgCrontab.crontabId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">定时任务代号:</td>
								<td><input type="text" name="crontabCode" id="crontabCode" value="${bgCrontab.crontabCode}" maxlength="100" placeholder="这里输入 定时任务代号" title="定时任务代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">定时任务名称:</td>
								<td><input type="text" name="crontabName" id="crontabName" value="${bgCrontab.crontabName}" maxlength="100" placeholder="这里输入 定时任务名称" title="定时任务名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">定时任务类型:</td>
								<td><param:select type="bg_crontabType" name="crontabType" id="crontabType" value="${bgCrontab.crontabType}" placeholder="这里请选择 定时任务类型" title="定时任务类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">任务名:</td>
								<td><input type="text" name="jobName" id="jobName" value="${bgCrontab.jobName}" maxlength="100" placeholder="这里输入 任务名" title="任务名" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">任务组名:</td>
								<td><input type="text" name="jobGroupName" id="jobGroupName" value="${bgCrontab.jobGroupName}" maxlength="100" placeholder="这里输入 任务组名" title="任务组名" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">任务:</td>
								<td><input type="text" name="jobClass" id="jobClass" value="${bgCrontab.jobClass}" maxlength="100" placeholder="这里输入 任务" title="任务" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">触发器名:</td>
								<td><input type="text" name="triggerName" id="triggerName" value="${bgCrontab.triggerName}" maxlength="100" placeholder="这里输入 触发器名" title="触发器名" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">触发器组名:</td>
								<td><input type="text" name="triggerGroupName" id="triggerGroupName" value="${bgCrontab.triggerGroupName}" maxlength="100" placeholder="这里输入 触发器组名" title="触发器组名" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">时间表达式:</td>
								<td><input type="text" name="timeExp" id="timeExp" value="${bgCrontab.timeExp}" maxlength="100" placeholder="这里输入 时间表达式" title="时间表达式" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${bgCrontab.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断crontabCode是否存在
		function otherNotCode(){
			var crontabCode = $("#crontabCode").val();
			if(crontabCode == "") return false;
			var crontabId = $("#crontabId").val();
			var url = "<%=basePath%>background/crontab/otherNotCode.do?crontabId="+crontabId+"&crontabCode="+crontabCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#crontabCode").tips({
						side:3,
			            msg:'定时任务代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#crontabCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if(!codeExp.test($("#crontabCode").val())){
				$("#crontabCode").tips({
					side:3,
		            msg:'请输入定时任务代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#crontabCode").focus();
			return false;
			}
			if($("#crontabName").val()==""){
				$("#crontabName").tips({
					side:3,
		            msg:'请输入定时任务名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#crontabName").focus();
			return false;
			}
			if($("#crontabType").val()==""){
				$("#crontabType").next().tips({
					side:3,
		            msg:'请选择 定时任务类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#jobName").val()==""){
				$("#jobName").tips({
					side:3,
		            msg:'请输入任务名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#jobName").focus();
			return false;
			}
			if($("#jobGroupName").val()==""){
				$("#jobGroupName").tips({
					side:3,
		            msg:'请输入任务组名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#jobGroupName").focus();
			return false;
			}
			if($("#jobClass").val()==""){
				$("#jobClass").tips({
					side:3,
		            msg:'请输入任务',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#jobClass").focus();
			return false;
			}
			if($("#triggerName").val()==""){
				$("#triggerName").tips({
					side:3,
		            msg:'请输入触发器名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#triggerName").focus();
			return false;
			}
			if($("#triggerGroupName").val()==""){
				$("#triggerGroupName").tips({
					side:3,
		            msg:'请输入触发器组名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#triggerGroupName").focus();
			return false;
			}
			if($("#timeExp").val()==""){
				$("#timeExp").tips({
					side:3,
		            msg:'请输入时间表达式',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#timeExp").focus();
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
			$("#crontabForm").submit();
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
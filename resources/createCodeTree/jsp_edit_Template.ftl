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
					
					<form action="${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${r"${methodPath }"}.do" name="${bgMaple.mapleCode}Form" id="${bgMaple.mapleCode}Form" method="post">
						<input type="hidden" name="${bgMaple.mapleCode}Id" id="${bgMaple.mapleCode}Id" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}"/>
						<input type="hidden" name="parentId" id="parentId" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}parentId${r"}"}"/>
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isKey == "01" >
						<input type="hidden" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}"/>
					</#if>
				</#list>	
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">上级:</td>
								<td>
									<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
										<b>${r"${"}${bgMaple.mapleEntityLower}${r"."}parentId${r"}"}</b>
									</div>
								</td>
							</tr>
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isEdit == "01" >
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">${bgMapleDetail.mapleDetailName}:</td>
						<#if bgMapleDetail.mapleDetailType == '01'>
								<td><input type="text" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" maxlength="${bgMapleDetail.length}" placeholder="这里输入${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" style="width:98%;"/></td>
						<#elseif bgMapleDetail.mapleDetailType == '02' || bgMapleDetail.mapleDetailType == '04'>
								<td><input type="number" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" maxlength="${bgMapleDetail.length}" placeholder="这里输入${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" style="width:98%;"/></td>
						<#elseif bgMapleDetail.mapleDetailType == '03'>
								<td><input class="span10 date-picker" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="${var[2] }" title="${bgMapleDetail.mapleDetailName}" style="width:98%;"/></td>
						</#if>
							</tr>
					</#if>
				</#list>
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
		
		//判断${bgMaple.mapleCode}Code是否存在
		function hasCode(){
			var ${bgMaple.mapleCode}Code = $("#${bgMaple.mapleCode}Code").val();
			var url = "<%=basePath%>background/${bgMaple.mapleCode}/hasCode.do?${bgMaple.mapleCode}Code="+${bgMaple.mapleCode}Code+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#${bgMaple.mapleCode}Code").tips({
						side:3,
			            msg:'${bgMaple.mapleName}代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#${bgMaple.mapleCode}Code").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
		<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailCode = "orderNum">
		if("${r"${methodPath }"}" == "edit"){
		</#if>
		<#if bgMapleDetail.mapleDetailCode = bgMaple.mapleCode+"Code">
			var codeExp = /^[a-zA-Z][a-zA-Z0-9_]*$/;
			if(!codeExp.test($("#${bgMapleDetail.mapleDetailCode }").val())){
				$("#${bgMapleDetail.mapleDetailCode }").tips({
					side:3,
		            msg:'请输入${bgMapleDetail.mapleDetailName } 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#${bgMapleDetail.mapleDetailCode }").focus();
			return false;
			}
		<#if bgMapleDetail.isEdit == "01" >
			if($("#${bgMapleDetail.mapleDetailCode }").val()==""){
				$("#${bgMapleDetail.mapleDetailCode }").tips({
					side:3,
		            msg:'请输入${bgMapleDetail.mapleDetailName }',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#${bgMapleDetail.mapleDetailCode }").focus();
			return false;
			}
		</#if>
		<#if bgMapleDetail.mapleDetailCode = "orderNum">
		}
		</#if>
		</#list>
			$("#${bgMaple.mapleCode}Form").submit();
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
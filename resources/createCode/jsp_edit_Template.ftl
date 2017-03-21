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
					
					<form action="${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${r"${methodPath }"}.do" name="${bgMaple.mapleCode}Form" id="${bgMaple.mapleCode}Form" method="post">
						<input type="hidden" name="${bgMaple.mapleCode}Id" id="${bgMaple.mapleCode}Id" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}"/>
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isKey == "01" >
						<input type="hidden" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}"/>
					</#if>
				</#list>	
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
						<#if bgMaple.mapleType == "02">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级名称:</td>
								<td align="center"><param:display type="bg_mapleDetailType" name="parentId" id="parentId" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}parentId ${r"}"}" hidden="true"/></td>
							</tr>
						<#elseif bgMaple.mapleType == "04">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级名称:</td>
								<td align="center"><param:display type="bg_mapleDetailType" name="${bgMaple.mapleCode ?replace('Detail','')}Id" id="${bgMaple.mapleCode ?replace('Detail','')}Id" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode ?replace('Detail','')}Id ${r"}"}" hidden="true"/></td>
							</tr>
						</#if>
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isEdit == "01" >
							<tr>
						<#if bgMapleDetail.mapleDetailType == '01'>
								<td style="width:100px;text-align: right;padding-top: 13px;">${bgMapleDetail.mapleDetailName}:</td>
								<td><input type="text" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" maxlength="${bgMapleDetail.totalLength}" placeholder="这里输入 ${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" style="width:98%;" <#if bgMapleDetail.mapleDetailCode == '${bgMaple.mapleCode }Code'>onblur="otherNotCode()"</#if>/></td>
						<#elseif bgMapleDetail.mapleDetailType == '02' || bgMapleDetail.mapleDetailType == '04'>
								<td style="width:100px;text-align: right;padding-top: 13px;">${bgMapleDetail.mapleDetailName}:</td>
								<td><input type="number" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" maxlength="${bgMapleDetail.totalLength}" placeholder="这里输入 ${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" style="width:98%;"/></td>
						<#elseif bgMapleDetail.mapleDetailType == '03'>
								<td style="width:100px;text-align: right;padding-top: 13px;">${bgMapleDetail.mapleDetailName}:</td>
								<td><input class="span10 date-picker" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" type="text" data-date-format="yyyy-mm-dd 00:00:00" readonly="readonly" placeholder="这里请选择 ${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" style="width:98%;"/></td>
						<#elseif bgMapleDetail.mapleDetailType == '05'>
								<td style="width:100px;text-align: right;padding-top: 13px;">${bgMapleDetail.mapleDetailName}:</td>
								<td><param:select type="${bgMapleDetail.typeCode}" name="${bgMapleDetail.mapleDetailCode}" id="${bgMapleDetail.mapleDetailCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}" placeholder="这里请选择 ${bgMapleDetail.mapleDetailName}" title="${bgMapleDetail.mapleDetailName}" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
						<#elseif bgMapleDetail.mapleDetailType == '06'>
								<td style="width:100px;text-align: left;padding-top: 13px;" colspan="2">${bgMapleDetail.mapleDetailName}:</td>
							</tr>
							<tr>
								<td colspan="2">
									<file:upimg name="${bgMapleDetail.mapleDetailCode}" count="1" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}"/>
								</td>
						</#if>
							</tr>
					</#if>
				</#list>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${r"${"}${bgMaple.mapleEntityLower}.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断${bgMaple.mapleCode}Code是否存在
		function otherNotCode(){
			var ${bgMaple.mapleCode}Code = $("#${bgMaple.mapleCode}Code").val();
			if(${bgMaple.mapleCode}Code == "") return false;
			var ${bgMaple.mapleCode}Id = $("#${bgMaple.mapleCode}Id").val();
			<#list bgMapleDetailList as bgMapleDetail>
				<#if bgMapleDetail.isKey == "01" >
					var ${bgMapleDetail.mapleDetailCode} = $("#${bgMapleDetail.mapleDetailCode}").val();
				</#if>
			</#list>
			var url = "<%=basePath%>background/${bgMaple.mapleCode}/otherNotCode.do?${bgMaple.mapleCode}Id="<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01" >+"&${bgMapleDetail.mapleDetailCode}="+${bgMapleDetail.mapleDetailCode}</#if></#list>+${bgMaple.mapleCode}Id+"&${bgMaple.mapleCode}Code="+${bgMaple.mapleCode}Code+"&tm="+new Date().getTime();
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
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
		<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailCode = bgMaple.mapleCode+"Code">
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
		<#elseif bgMapleDetail.isEdit == "01" >	
		<#if bgMapleDetail.mapleDetailType == '01'> 
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
		<#elseif bgMapleDetail.mapleDetailType == '02'>
			if(!intExp.test($("#${bgMapleDetail.mapleDetailCode }").val())){
				$("#${bgMapleDetail.mapleDetailCode }").tips({
					side:3,
		            msg:'请输入${bgMapleDetail.mapleDetailName } 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#${bgMapleDetail.mapleDetailCode }").focus();
			return false;
			}
		<#elseif bgMapleDetail.mapleDetailType == '04'>
			if(!intExp.test($("#${bgMapleDetail.mapleDetailCode }").val())){
				$("#${bgMapleDetail.mapleDetailCode }").tips({
					side:3,
		            msg:'请输入${bgMapleDetail.mapleDetailName } 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#${bgMapleDetail.mapleDetailCode }").focus();
			return false;
			}
		<#elseif bgMapleDetail.mapleDetailType == "05" || bgMapleDetail.mapleDetailType == "03">
			if($("#${bgMapleDetail.mapleDetailCode }").val()==""){
				$("#${bgMapleDetail.mapleDetailCode }").next().tips({
					side:3,
		            msg:'请选择 ${bgMapleDetail.mapleDetailName }',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
		<#elseif bgMapleDetail.mapleDetailType == '06'> 
			if($("#${bgMapleDetail.mapleDetailCode }").val()==""){
				$("#${bgMapleDetail.mapleDetailCode }").tips({
					side:3,
		            msg:'请上传${bgMapleDetail.mapleDetailName }',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#${bgMapleDetail.mapleDetailCode }").focus();
			return false;
			}
		</#if>
		</#if>
		</#list>
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
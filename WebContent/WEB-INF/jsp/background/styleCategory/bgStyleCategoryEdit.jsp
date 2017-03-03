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
					
					<form action="background/styleCategory/${methodPath }.do" name="styleCategoryForm" id="styleCategoryForm" method="post">
						<input type="hidden" name="styleCategoryId" id="styleCategoryId" value="${comStyleCategory.styleCategoryId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品编号:</td>
								<td><input type="text" name="productId" id="productId" value="${comStyleCategory.productId}" maxlength="100" placeholder="这里输入 产品编号" title="产品编号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">规格分类代号:</td>
								<td><input type="text" name="styleCategoryCode" id="styleCategoryCode" value="${comStyleCategory.styleCategoryCode}" maxlength="100" placeholder="这里输入 规格分类代号" title="规格分类代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">规格分类名称:</td>
								<td><input type="text" name="styleCategoryName" id="styleCategoryName" value="${comStyleCategory.styleCategoryName}" maxlength="100" placeholder="这里输入 规格分类名称" title="规格分类名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comStyleCategory.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断styleCategoryCode是否存在
		function otherNotCode(){
			var styleCategoryCode = $("#styleCategoryCode").val();
			if(styleCategoryCode == "") return false;
			var styleCategoryId = $("#styleCategoryId").val();
			var url = "<%=basePath%>background/styleCategory/otherNotCode.do?styleCategoryId="+styleCategoryId+"&styleCategoryCode="+styleCategoryCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#styleCategoryCode").tips({
						side:3,
			            msg:'规格分类代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#styleCategoryCode").focus();
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
		            msg:'请输入产品编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productId").focus();
			return false;
			}
			if(!codeExp.test($("#styleCategoryCode").val())){
				$("#styleCategoryCode").tips({
					side:3,
		            msg:'请输入规格分类代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#styleCategoryCode").focus();
			return false;
			}
			if($("#styleCategoryName").val()==""){
				$("#styleCategoryName").tips({
					side:3,
		            msg:'请输入规格分类名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#styleCategoryName").focus();
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
			$("#styleCategoryForm").submit();
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
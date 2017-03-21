<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
<%@ taglib prefix="file" uri="http://www.maple_file_tld.com"%>
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
	<link rel="stylesheet" type="text/css" href="plugins/myupimg/css/upimg.css" />
	<link rel="stylesheet" type="text/css" href="plugins/myupimg/font/iconfont.css" />
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
					
					<form action="background/product/${methodPath }.do" name="productForm" id="productForm" method="post">
						<input type="hidden" name="productId" id="productId" value="${comProduct.productId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">供应商:</td>
								<td><param:select type="com_supplierEffective" name="supplierId" id="supplierId" value="${comProduct.supplierId}" placeholder="这里请选择 供应商" title="供应商" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品代号:</td>
								<td><input type="text" name="productCode" id="productCode" value="${comProduct.productCode}" maxlength="100" placeholder="这里输入 产品代号" title="产品代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品名称:</td>
								<td><input type="text" name="productName" id="productName" value="${comProduct.productName}" maxlength="100" placeholder="这里输入 产品名称" title="产品名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品类型:</td>
								<td><param:select type="com_productTypeEffective" name="productType" id="productType" value="${comProduct.productType}" placeholder="这里请选择 产品类型" title="产品类型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品模型:</td>
								<td><param:select type="com_productModelEffective" name="productModel" id="productModel" value="${comProduct.productModel}" placeholder="这里请选择 产品模型" title="产品模型" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">摘要:</td>
								<td><input type="text" name="summary" id="summary" value="${comProduct.summary}" maxlength="1000" placeholder="这里输入 摘要" title="摘要" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">简介:</td>
								<td><input type="text" name="introduction" id="introduction" value="${comProduct.introduction}" maxlength="100" placeholder="这里输入 简介" title="简介" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;" colspan="2">产品头像:</td>
							</tr>
							<tr>
								<td colspan="2">
									<file:upimg name="headImgSrc" count="1" value="${comProduct.headImgSrc}"/>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;" colspan="2">长框图:</td>
							</tr>
							<tr>
								<td colspan="2">
									<file:upimg name="imgSrc1" count="3" value="${comProduct.imgSrc1}"/>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;" colspan="2">滚播图:</td>
							</tr>
							<tr>
								<td colspan="2">
									<file:upimg name="imgSrc2" count="4" value="${comProduct.imgSrc2}"/>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;" colspan="2">详情图:</td>
							</tr>
							<tr>
								<td colspan="2">
									<file:upimg name="imgSrc3" count="4" value="${comProduct.imgSrc3}"/>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comProduct.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
	
	<script type="text/javascript" src="plugins/myupimg/js/jquery.lazyload.min.js"></script>
	<script type="text/javascript" src="plugins/myupimg/js/upimg.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		
		//判断productCode是否存在
		function otherNotCode(){
			var productCode = $("#productCode").val();
			if(productCode == "") return false;
			var productId = $("#productId").val();
			var url = "<%=basePath%>background/product/otherNotCode.do?productId="+productId+"&productCode="+productCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#productCode").tips({
						side:3,
			            msg:'产品代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#productCode").focus();
					return false;
				}
			});
		}
		
		//保存
		function save(){
			var codeExp = /^[a-z][a-zA-Z0-9_]*$/;
			var intExp = /^[1-9]\d*$|^0$/;
			var deciExp = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$|^0$/;
			if($("#supplierId").val()==""){
				$("#supplierId").next().tips({
					side:3,
		            msg:'请选择 供应商',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!codeExp.test($("#productCode").val())){
				$("#productCode").tips({
					side:3,
		            msg:'请输入产品代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productCode").focus();
			return false;
			}
			if($("#productName").val()==""){
				$("#productName").tips({
					side:3,
		            msg:'请输入产品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productName").focus();
			return false;
			}
			if($("#productType").val()==""){
				$("#productType").next().tips({
					side:3,
		            msg:'请选择 产品类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#productModel").val()==""){
				$("#productModel").next().tips({
					side:3,
		            msg:'请选择 产品模型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#summary").val()==""){
				$("#summary").tips({
					side:3,
		            msg:'请输入摘要',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#summary").focus();
			return false;
			}
			if($("#introduction").val()==""){
				$("#introduction").tips({
					side:3,
		            msg:'请输入简介',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#introduction").focus();
			return false;
			}
			if($("#headImgSrc").val()==""){
				$("#headImgSrc").tips({
					side:3,
		            msg:'请上传产品头像',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#headImgSrc").focus();
			return false;
			}
			if($("#imgSrc1").val()==""){
				$("#imgSrc1").tips({
					side:3,
		            msg:'请上传长框图',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#imgSrc1").focus();
			return false;
			}
			if($("#imgSrc2").val()==""){
				$("#imgSrc2").tips({
					side:3,
		            msg:'请上传滚播图',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#imgSrc2").focus();
			return false;
			}
			if($("#imgSrc3").val()==""){
				$("#imgSrc3").tips({
					side:3,
		            msg:'请上传详情图',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#imgSrc3").focus();
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
			$("#productForm").submit();
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
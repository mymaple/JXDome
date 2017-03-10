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
					
					<form action="background/productStyle/${methodPath }.do" name="productStyleForm" id="productStyleForm" method="post">
						<input type="hidden" name="productStyleId" id="productStyleId" value="${comProductStyle.productStyleId}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">商品id:</td>
								<td>
									<c:if test="${not empty comProductStyle.productId }">
									<param:display type="com_productEffective" value="${comProductStyle.productId}" name="productId" id="productId" hidden="true"/>
									</c:if>
									<c:if test="${empty comProductStyle.productId }">
										<param:select type="com_productEffective" name="productId" id="productId" value="${comProductStyle.productId}" placeholder="这里请选择 商品" title="商品" cssClass="chosen-select form-control" styleClass="width:98%;"/>
									</c:if>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品规格代号:</td>
								<td><input type="text" name="productStyleCode" id="productStyleCode" value="${comProductStyle.productStyleCode}" maxlength="100" placeholder="这里输入 产品规格代号" title="产品规格代号" style="width:98%;" onblur="otherNotCode()"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品规格名称:</td>
								<td><input type="text" name="productStyleName" id="productStyleName" value="${comProductStyle.productStyleName}" maxlength="100" placeholder="这里输入 产品规格名称" title="产品规格名称" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">产品规格类型:</td>
								<td>
									<c:choose>
										<c:when test="${not empty comStyleCategoryList}">
										<c:forEach items="${comStyleCategoryList}" var="comStyleCategory" varStatus="vs">
									${comStyleCategory.styleCategoryName}:<param:select type="com_styleCategoryEffectiveP" name="productStyleType" id="productStyleType" value="${comStyleCategory.styleCategoryStatus}" 
										target="${comStyleCategory.styleCategoryId}" placeholder="这里请选择 ${comStyleCategory.styleCategoryName}" title="${comStyleCategory.styleCategoryName}" cssClass="chosen-select form-control" styleClass="width:98%;"/>
										</c:forEach>
										</c:when>
									</c:choose>
								</td>
							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">库存数量:</td>
								<td><input type="number" name="stockNum" id="stockNum" value="${comProductStyle.stockNum}" maxlength="100" placeholder="这里输入 库存数量" title="库存数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">货币种类:</td>
								<td><param:select type="com_curType" name="curType" id="curType" value="${comProductStyle.curType}" placeholder="这里请选择 货币种类" title="货币种类" cssClass="chosen-select form-control" styleClass="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">原价:</td>
								<td><input type="number" name="originalPrice" id="originalPrice" value="${comProductStyle.originalPrice}" maxlength="100" placeholder="这里输入 原价" title="原价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">折扣:</td>
								<td><input type="number" name="discountRate" id="discountRate" value="${comProductStyle.discountRate}" maxlength="100" placeholder="这里输入 折扣" title="折扣" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">折扣优惠:</td>
								<td><input type="number" name="discountPrice" id="discountPrice" value="${comProductStyle.discountPrice}" maxlength="100" placeholder="这里输入 折扣优惠" title="折扣优惠" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">现价:</td>
								<td><input type="number" name="currentPrice" id="currentPrice" value="${comProductStyle.currentPrice}" maxlength="100" placeholder="这里输入 现价" title="现价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">排序编号:</td>
								<td><input type="text" name="orderNum" id="orderNum" value="${comProductStyle.orderNum}" maxlength="100" placeholder="这里输入 排序编号" title="排序编号" style="width:98%;" /></td>
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
		
		//判断productStyleCode是否存在
		function otherNotCode(){
			var productStyleCode = $("#productStyleCode").val();
			if(productStyleCode == "") return false;
			var productStyleId = $("#productStyleId").val();
			var url = "<%=basePath%>background/productStyle/otherNotCode.do?productStyleId="+productStyleId+"&productStyleCode="+productStyleCode+"&tm="+new Date().getTime();
			$.get(url,function(data){
				if(data.resultCode != "success"){
					$("#productStyleCode").tips({
						side:3,
			            msg:'产品规格代号 已存在',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#productStyleCode").focus();
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
				$("#productId").next().tips({
					side:3,
		            msg:'请选择 商品id',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!codeExp.test($("#productStyleCode").val())){
				$("#productStyleCode").tips({
					side:3,
		            msg:'请输入产品规格代号 需以小写字母开头的字母数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productStyleCode").focus();
			return false;
			}
			if($("#productStyleName").val()==""){
				$("#productStyleName").tips({
					side:3,
		            msg:'请输入产品规格名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productStyleName").focus();
			return false;
			}
			if($("#productStyleType").val()==""){
				$("#productStyleType").next().tips({
					side:3,
		            msg:'请选择 产品规格类型',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#stockNum").val())){
				$("#stockNum").tips({
					side:3,
		            msg:'请输入库存数量 需是数字',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#stockNum").focus();
			return false;
			}
			if(!intExp.test($("#originalPrice").val())){
				$("#originalPrice").tips({
					side:3,
		            msg:'请输入原价 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#originalPrice").focus();
			return false;
			}
			if($("#curType").val()==""){
				$("#curType").next().tips({
					side:3,
		            msg:'请选择 货币种类',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if(!intExp.test($("#discountRate").val())){
				$("#discountRate").tips({
					side:3,
		            msg:'请输入折扣 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discountRate").focus();
			return false;
			}
			if(!intExp.test($("#discountPrice").val())){
				$("#discountPrice").tips({
					side:3,
		            msg:'请输入折扣优惠 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discountPrice").focus();
			return false;
			}
			if(!intExp.test($("#currentPrice").val())){
				$("#currentPrice").tips({
					side:3,
		            msg:'请输入现价 最多为两位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#currentPrice").focus();
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
			$("#productStyleForm").submit();
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
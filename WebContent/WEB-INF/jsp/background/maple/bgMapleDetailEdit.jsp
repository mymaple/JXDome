<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../main/bgIndexTop.jsp"%>

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
						<form  action="background/mapleDetail/${msg }.do" name="mapleDetailForm" id="mapleDetailForm" method="post">
							<input type="hidden" name="mapleDetailId" id="mapleDetailId" value="${pd.mapleDetailId }"/>
							<input type="hidden" name="mapleId" id="mapleId" value="${pd.mapleId }"/>
							
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">属性代号 :</td>
									<td><input type="text" name="mapleDetailCode" id="mapleDetailCode" value="${pd.mapleDetailCode }" maxlength="32" placeholder="这里输入菜单名称" title="菜单名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">属性名称 :</td>
									<td><input type="text" name="mapleDetailName" id="mapleDetailName" value="${pd.mapleDetailName }" maxlength="32" placeholder="这里输入菜单名称" title="菜单名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">属性类型 :</td>
									<td><input type="text" name="mapleDetailType" id="mapleDetailType" value="${pd.mapleDetailType }" maxlength="32"  placeholder="这里输入菜单标记名称" title="菜单标记名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">长度 :</td>
									<td><input type="text" name="length" id="length" value="${pd.length }" maxlength="32" placeholder="这里输入菜单标识" title="菜单标识" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">小数长度 :</td>
									<td><input type="text" name="decimalLength" id="decimalLength" value="${pd.decimalLength }" maxlength="32" placeholder="这里输入菜单链接" title="菜单链接" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">是否录入 :</td>
									<td><input type="text" name="isEdit" id="isEdit" value="${pd.isEdit }" maxlength="32" placeholder="这里输入菜单链接" title="菜单链接" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">是否null :</td>
									<td><input type="text" name="isNull" id="isNull" value="${pd.isNull }" maxlength="32" placeholder="这里输入菜单链接" title="菜单链接" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">默认值 :</td>
									<td><input type="text" name="defaultValue" id="defaultValue" value="${pd.defaultValue }" maxlength="32" placeholder="这里输入菜单链接" title="菜单链接" style="width:98%;"/></td>
								</tr>
								<%-- <tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">排序编号 :</td>
									<td><input type="number" name="orderNum" id="orderNum" value="${pd.orderNum}" maxlength="32" placeholder="这里输入菜单序号" title="菜单序号" style="width:98%;"/></td>
								</tr> --%>
								
								<tr>
									<td style="text-align: center;" colspan="10">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
									</td>
								</tr>
							</table>
							</div>
							<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
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


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../main/bgIndexFoot.jsp"%>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
		
		//保存
		function save(){
			$("#mapleDetailForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	</script>


</body>
</html>
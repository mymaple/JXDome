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
						<form  action="background/menu/${msg }.do" name="menuForm" id="menuForm" method="post">
							<input type="hidden" name="menuId" id="menuId" value="${pd.menuId }"/>
							<input type="hidden" name="menuIcon" id="menuIcon" value="${pd.menuIcon }"/>
							<input type="hidden" name="menuType" id="menuType" value="${pd.menuType }"/>
							<input type="hidden" name="status" id="status" value="${pd.status }"/>
							<input type="hidden" name="parentId" id="parentId" value="${null == pd.parentId ? menuId:pd.parentId}"/>
							
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">上级 :</td>
									<td>${null == parentBgMenu.menuName ?'(无) 此项为顶级菜单':parentBgMenu.menuName}</td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">菜单名称 :</td>
									<td><input type="text" name="menuName" id="menuName" value="${pd.menuName }" maxlength="32" placeholder="这里输入菜单名称" title="菜单名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">标记名称 :</td>
									<td><input type="text" name="menuCode" id="menuCode" value="${pd.menuCode }" maxlength="32"  placeholder="这里输入菜单标记名称" title="菜单标记名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">菜单标识 :</td>
									<td><input type="number" name="menuTag" id="menuTag" value="${pd.menuTag }" maxlength="32" placeholder="这里输入菜单标识" title="菜单标识" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">菜单链接 :</td>
									<c:if test="${null != parentBgMenu.menuName}">
									<td><input type="text" name="menuUrl" id="menuUrl" value="${pd.menuUrl }" maxlength="32" placeholder="这里输入菜单链接" title="菜单链接" style="width:98%;"/></td>
									</c:if>
									<c:if test="${null == parentBgMenu.menuName}">
									<td><input type="text" name="menuUrl" id="menuUrl" value="" maxlength="32" placeholder="顶级菜单禁止输入" title="菜单链接" style="width:98%;" readonly="readonly"/></td>
									</c:if>
								</tr>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">序号 :</td>
									<td><input type="number" name="menuOrder" id="menuOrder" value="${pd.menuOrder}" maxlength="32" placeholder="这里输入菜单序号" title="菜单序号" style="width:98%;"/></td>
								</tr>
								<c:if test="${'0' == menuId}">
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">类型 :</td>
									<td>
									<div class="col-sm-9">
										<label style="float:left;padding-left: 8px;padding-top:7px;">
											<input name="form-field-radio" type="radio" class="ace" id="form-field-radio1" <c:if test="${pd.menuType == '1' }">checked="checked"</c:if> onclick="setType('1','1');"/>
											<span class="lbl"> 系统菜单</span>
										</label>
										<label style="float:left;padding-left: 5px;padding-top:7px;">
											<input name="form-field-radio" type="radio" class="ace" id="form-field-radio2" <c:if test="${pd.menuType == '2' }">checked="checked"</c:if> onclick="setType('1','2');"/>
											<span class="lbl"> 业务菜单</span>
										</label>
									</div>
									</td>
								</tr>
								</c:if>
								<tr>
									<td style="width:79px;text-align: right;padding-top: 13px;">状态 :</td>
									<td>
										<div class="col-sm-9">
											<label style="float:left;padding-left: 8px;padding-top:7px;">
												<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio3" <c:if test="${pd.status == 1 }">checked="checked"</c:if> onclick="setType('2',1);"/>
												<span class="lbl"> 显示</span>
											</label>
											<label style="float:left;padding-left: 5px;padding-top:7px;">
												<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio4" <c:if test="${pd.status == 0 }">checked="checked"</c:if> onclick="setType('2',0);"/>
												<span class="lbl"> 隐藏</span>
											</label>
										</div>
									</td>
								</tr>
								
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
			if($("#menuName").val()==""){
				$("#menuName").tips({
					side:3,
		            msg:'请输入菜单名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#menuName").focus();
				return false;
			}
			if($("#menuUrl").val()==""){
				$("#menuUrl").val('#');
			}
			//类型为空默认为业务类型
			if($("#menuType").val()==""){
				$("#menuType").val('1');
			}
			//状态值为空默认为隐藏
			if($("#status").val()==""){
				$("#status").val(0);
			}
			if($("#menuOrder").val()==""){
				$("#menuOrder").tips({
					side:1,
		            msg:'请输入菜单序号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#menuOrder").focus();
				return false;
			}
			if(isNaN(Number($("#menuOrder").val()))){
				$("#menuOrder").tips({
					side:1,
		            msg:'请输入菜单序号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#menuOrder").focus();
				$("#menuOrder").val(1);
				return false;
			}
			$("#menuForm").submit();
		}
		
		//设置菜单类型or状态
		function setType(type,value){
			if(type == '1'){
				$("#menuType").val(value);
			}else{
				$("#status").val(value);
			}
		}
	</script>


</body>
</html>
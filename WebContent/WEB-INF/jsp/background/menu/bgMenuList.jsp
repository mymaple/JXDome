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
	<script type="text/javascript">
		//刷新ztree
		function parentReload(){
			if(null != '${msg}' && '' != '${msg}' && 'change' == '${msg}'){
				parent.location.href="<%=basePath%>background/menu/main.do";
			}else{
				//什么也不干
			}
		}
		parentReload();
	</script>
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

							<table id="dynamic-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center" style="width: 50px;">序号</th>
										<th class='center'>菜单名称</th>
										<th class='center'>菜单标记名称</th>
										<th class='center'>资源路径</th>
										<th class='center' style="width: 50px;">状态</th>
										<th class='center' style="width: 120px;">操作</th>
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty subBgMenuList}">
									<c:forEach items="${subBgMenuList}" var="menu" varStatus="vs">
									<tr>
										<td class='center'>${menu.menuOrder }</td>
										<td class='center'><i class="${menu.menuIcon }">&nbsp;</i>
											<a href="javascript:toSubMenu('${menu.menuId }')">${menu.menuName }</a>
											&nbsp;
											<c:if test="${menu.parentId == '0' }">
											<c:if test="${menu.menuType == '1' }">
											<span class="label label-success arrowed">系统</span>
											</c:if>
											<c:if test="${menu.menuType != '1' }">
											<span class="label label-important arrowed-in">业务</span>
											</c:if>
											</c:if>
										</td>
										<td>${menu.menuCode}</td>
										<td>${menu.menuUrl == '#'? '': menu.menuUrl}</td>
										<td class='center'><i class="ace-icon fa ${menu.status == 1? 'fa-eye': 'fa-eye-slash'}"></i></td>
										<td class='center'>
											<c:if test="${!RIGHTS.edit && !RIGHTS.del}">
											<span class="label label-large label-grey arrowed-in-right arrowed-in">
												<i class="ace-icon fa fa-lock" title="无权限"></i>
											</span>
											</c:if>
											<div class="hidden-sm hidden-xs action-buttons">
												<c:if test="${RIGHTS.edit}">
												<a class="blue" href="javascript:toChangeMenuIcon('${menu.menuId }');"> 
													<i class="ace-icon glyphicon glyphicon-picture bigger-130" title="编辑图标"></i>
												</a> 
												<a class="green" href="javascript:toEdit('${menu.menuId }');">
													<i class="ace-icon fa fa-pencil-square-o bigger-130" title="修改"></i>
												</a>
												</c:if>
												<c:if test="${RIGHTS.del}">
												<a class="red" href="javascript:toDelete('${menu.menuId }');">
													<i class="ace-icon fa fa-trash-o bigger-130" title="删除"></i>
												</a>
												</c:if>
											</div>
										</td>
									</tr>
									</c:forEach>
									</c:when>
										<c:otherwise>
											<tr>
											<td colspan="100" class='center'>没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							
							
							<div class="page-header position-relative">
								<table style="width:100%;">
									<tr>
										<td style="vertical-align:top;">
										<c:if test="${RIGHTS.add}">
											<a class="btn btn-mini btn-success" onclick="toAdd('${menuId}');">新增</a>
										</c:if>
										<c:if test="${null != pd.menuId && pd.menuId != '0' && pd.menuId != ''}">
											<a class="btn btn-mini btn-primary" onclick="toSubMenu('${pd.parentId}');">返回</a>
										</c:if>
										</td>
										<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${bgPage.pageStr}</div></td>
									</tr>
								</table>
							</div>
							
							
							<%-- <div>
								&nbsp;&nbsp;
								<c:if test="${RIGHTS.add}">
									<a class="btn btn-sm btn-success" onclick="toAdd('${menuId}');">新增</a>
								</c:if>
								<c:if test="${null != pd.menuId && pd.menuId != '0' && pd.menuId != ''}">
									<a class="btn btn-sm btn-success" onclick="toSubMenu('${pd.parentId}');">返回</a>
								</c:if>
							</div> --%>
							
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
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(document).ready(function(){
			top.hangge();
		});	
		
		//去此ID下子菜单列表
		function toSubMenu(menuId){
			top.jzts();
			window.location.href="<%=basePath%>background/menu/list.do?menuId="+menuId;
		};
		
		//新增
		function toAdd(menuId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>background/menu/toAdd.do?menuId='+menuId;
			 diag.Width = 469;
			 diag.Height = 510;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${bgPage.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage('${bgPage.currentPage}');
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function toEdit(userId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改";
			 diag.URL = '<%=basePath%>background/menu/toEdit.do?menuId='+userId;
			 diag.Width = 469;
			 diag.Height = 510;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${bgPage.currentPage}');
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function toDelete(menuId){
			bootbox.confirm("确定要删除此菜单吗?", function(result) {
				if(result) {
					var url = "<%=basePath%>background/menu/toDelete.do?menuId="+menuId+"&guid="+new Date().getTime();
					top.jzts();
					$.get(url,function(data){
						if("success" == data.result){
							nextPage('${bgPage.currentPage}');
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>删除失败,请先删除子菜单!</span>",
								buttons: 			
								{
									"button" :
									{
										"label" : "确定",
										"className" : "btn-sm btn-success"
									}
								}
							});
						}
					});
				}
			});
		}
		
		//编辑菜单图标
		function toChangeMenuIcon(menuId){
			 top.jzts();
		   	 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑图标";
			 diag.URL = '<%=basePath%>background/menu/toChangeMenuIcon.do?menuId='+menuId;
			 diag.Width = 800;
			 diag.Height = 380;
			 diag.CancelEvent = function(){ //关闭事件
				if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					top.jzts(); 
					setTimeout("location.reload()",100);
				}
				diag.close();
			 };
			 diag.show();
		}
	</script>


</body>
</html>
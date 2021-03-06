﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../main/bgIndexTop.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />

<script type="text/javascript">
	//刷新ztree
	function parentReload(returnMsg,currentPage,showCount){
		if('change' == returnMsg){
			var url = "<%=basePath%>background/styleCategory/main.do?pId="+'${pd.pId}'+"&currentPage="+currentPage+"&showCount="+showCount;
			var productId=$("#productId").val();
			if(productId!=null && productId!=''){
				url=url+"&productId="+productId;
			}
			parent.location.href=url;
		}
	}
</script>
</head>
<body class="no-skin">
	
	<div class="page-header">
	<h1>
		<c:if test="${empty pd.productId }">
			<a href="<%=basePath%>background/styleCategory/list.do"> 
			类型分类管理
			</a>
		</c:if>
		<c:if test="${not empty pd.productId }">
			<a href="<%=basePath%>background/product/list.do"> 
			商品管理
			</a>
			——
			<a href="<%=basePath%>background/styleCategory/list.do?productId=${pd.productId}"> 
			类型分类管理——<param:display type="com_productEffective" value="${pd.productId}" id="productId" hidden="true"/>
			</a>
		</c:if>
		<c:choose>
			<c:when test="${not empty parentList}">
				<c:if test="${RIGHTS.sele}">
				<c:forEach items="${parentList}" var="comStyleCategory" varStatus="vs">
					<small>
						<i class="ace-icon fa fa-angle-double-right"></i>
						<a href="${comStyleCategory.subComStyleCategoryPath}">${comStyleCategory.styleCategoryName}</a>
					</small>
				</c:forEach>
				</c:if>
			</c:when>
		</c:choose>			
	</h1>
	</div><!-- /.page-header -->
	
	
	
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							
						<!-- 检索  -->
						<form action="background/styleCategory/list.do" method="post" name="styleCategoryForm" id="styleCategoryForm">
						<input type="hidden" name="pId" id="pId" value="${pd.pId}"/>
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<c:if test="${RIGHTS.sele}">
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toSearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								<%-- <c:if test="${RIGHTS.toExcel}"> --%>
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExportExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td>
								<%-- <c:if test="${RIGHTS.fromExcel}"> --%>
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toUploadExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="ace-icon fa fa-cloud-upload bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">产品编号</th>
									<th class="center">规格分类名称</th>
									<th class="center">是否最终分类</th>
									<th class="center">排序编号</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty comStyleCategoryList}">
									<c:if test="${RIGHTS.sele}">
									<c:forEach items="${comStyleCategoryList}" var="comStyleCategory" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${comStyleCategory.styleCategoryId}" class="ace" /><span class="lbl"></span></label>
											</td>
											<c:if test="${comStyleCategory.effective == '00'}">
											<td class='center' style="background-color: red;width: 30px;">${vs.index+1}</td>
											</c:if>
											<c:if test="${comStyleCategory.effective != '00'}">
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											</c:if>
											<td class='center'><param:display type="com_productEffective" value="${comStyleCategory.productId}"/></td>
											<td class='center'><a href="javascript:toSub('${comStyleCategory.styleCategoryId}')">${comStyleCategory.styleCategoryName}</a></td>
											<td class='center'><param:display type="com_sf" value="${comStyleCategory.isFinal}"/></td>
											<td class='center'>${comStyleCategory.orderNum}</td>
											<td class="center">
												<c:if test="${!RIGHTS.edit && !RIGHTS.del }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${RIGHTS.edit}">
													
													<c:if test="${comStyleCategory.effective == '00'}">
													<a class="btn btn-xs btn-info" onclick="changeEffective('01','${comStyleCategory.styleCategoryId}');">使生效</a>
													</c:if>
													<c:if test="${comStyleCategory.effective != '00'}">
													<a class="btn btn-xs btn-grey" onclick="changeEffective('00','${comStyleCategory.styleCategoryId}');">使失效</a>
													</c:if>
													
													<a class="btn btn-xs btn-success" title="编辑" onclick="toEdit('${comStyleCategory.styleCategoryId}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${RIGHTS.del }">
													<a class="btn btn-xs btn-danger" onclick="toDelete('${comStyleCategory.styleCategoryId}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${!RIGHTS.sele}">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<c:if test="${RIGHTS.add }">
									<a class="btn btn-mini btn-success" onclick="toAdd();">新增</a>
									</c:if>
									<c:if test="${RIGHTS.del }">
									<a class="btn btn-mini btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${bgPage.pageStr}</div></td>
							</tr>
						</table>
						</div>
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
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
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
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function toSearch(){
			top.jzts();
			$("#styleCategoryForm").submit();
		}
		$(function() {
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
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
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//去此ID下子菜单列表
		function toSub(pId){
			top.jzts();
			var url = "<%=basePath%>background/styleCategory/list.do?pId="+pId;
			var productId=$("#productId").val();
			if(productId!=null && productId!=''){
				url=url+"&productId="+productId;
			}
			window.location.href=url;
		};

	function changeEffective(flag,styleCategoryId){
			var firm = "确定要生效吗?";
			if("00"==flag){
				firm = "确定要失效吗?"
			}
			bootbox.confirm(firm, function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>background/styleCategory/changeEffective.do?flag="+flag+"&styleCategoryId="+styleCategoryId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data.resultCode == "success"){
							nextPage('${bgPage.currentPage}');
						}
					});
				}
			});
		}
		
		function changeStatus(flag,styleCategoryId){
			var firm = "确定要生效吗?";
			if("00"==flag){
				firm = "确定要失效吗?"
			}
			bootbox.confirm(firm, function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>background/styleCategory/changeStatus.do?flag="+flag+"&styleCategoryId="+styleCategoryId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data.resultCode == "success"){
							nextPage('${bgPage.currentPage}');
						}
					});
				}
			});
		}
		
		//新增
		function toAdd(){
			top.jzts();
			var url = "<%=basePath%>background/styleCategory/toAdd.do?pId="+'${pd.pId}';
			var productId=$("#productId").val();
			if(productId!=null && productId!=''){
				url=url+"&productId="+productId;
			}
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = url;
			 diag.Width = 450;
			 diag.Height = 500;
			 diag.Modal = true;				//有无遮罩窗口
			 diag.ShowMaxButton = true;		//最大化按钮 
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){	
					parentReload('change','${bgPage.currentPage}','${bgPage.showCount}');
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function toDelete(styleCategoryId){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>background/styleCategory/toDelete.do?styleCategoryId="+styleCategoryId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data.resultCode == "success"){
							parentReload('change','${bgPage.currentPage}','${bgPage.showCount}');
						}
					});
				}
			});
		}
		
		//修改
		function toEdit(styleCategoryId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = "<%=basePath%>background/styleCategory/toEdit.do?styleCategoryId="+styleCategoryId+"&tm="+new Date().getTime();
			 diag.Width = 450;
			 diag.Height = 500;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     	 diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					parentReload('change','${bgPage.currentPage}','${bgPage.showCount}');
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>background/styleCategory/toBatchDelete.do?tm='+new Date().getTime(),
						    	data: {ids:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									if(data.resultCode == "success"){
										parentReload('change','${bgPage.currentPage}','${bgPage.showCount}');
									}
								}
							});
						}
					}
				}
			});
		};
		
		//导出excel
		function toExportExcel(){
			window.location.href='<%=basePath%>background/styleCategory/toExportExcel.do';
		}
		
		//打开上传excel页面
		function toUploadExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>background/styleCategory/toUploadExcel.do?pId='+'${pd.pId}';
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					parentReload('change','${bgPage.currentPage}','${bgPage.showCount}');
				}
				diag.close();
			 };
			 diag.show();
		}
	</script>

</body>
</html>
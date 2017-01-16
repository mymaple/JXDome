<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<#if bgMaple.mapleType == "02">
<script type="text/javascript">
	//刷新ztree
	function parentReload(returnMsg,currentPage,showCount){
		if('change' == returnMsg){
			parent.location.href="<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/main.do?pId="+'${r"${pd.pId}"}'+"&currentPage="+currentPage+"&showCount="+showCount;
		}
	}
</script>
</#if>
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
							
						<!-- 检索  -->
						<form action="${bgMaple.controllerPackage}/${bgMaple.mapleCode}/list.do" method="post" name="${bgMaple.mapleCode}Form" id="${bgMaple.mapleCode}Form">
						<#if bgMaple.mapleType == "02">
						<input type="hidden" name="parentId" id="parentId" value="${r"pd."}parentId${r"}"}"/>
						<#elseif bgMaple.mapleType == "04">
						<input type="hidden" name="${bgMaple.mapleCode ?replace('Detail','')}Id" id="${bgMaple.mapleCode ?replace('Detail','')}Id" value="${r"${pd."}${bgMaple.mapleCode ?replace('Detail','')}Id ${r"}"}"/>
						</#if>
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${r"${pd.keywords }"}" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<c:if test="${r"${RIGHTS.sele}"}">
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toSearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								<%-- <c:if test="${r"${RIGHTS.toExcel}"}"> --%>
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExportExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td>
								<%-- <c:if test="${r"${RIGHTS.fromExcel}"}"> --%>
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
								<#list bgMapleDetailList as bgMapleDetail>
								<#if bgMapleDetail.isEdit = "01">
									<th class="center">${bgMapleDetail.mapleDetailName}</th>
								</#if>
								</#list>
									<th class="center">排序编号</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${r"${not empty "}${bgMaple.mapleEntityLower}List${r"}"}">
									<c:if test="${r"${RIGHTS.sele}"}">
									<c:forEach items="${r"${"}${bgMaple.mapleEntityLower}List${r"}"}" var="${bgMaple.mapleEntityLower}" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">-${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}</#if></#list>" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${r"${vs.index+1}"}</td>
										<#list bgMapleDetailList as bgMapleDetail>
										<#if bgMapleDetail.isEdit == "01" >
										<#if bgMapleDetail.mapleDetailType == "05">
											<td class='center'><param:display type="${bgMapleDetail.typeCode}" value="${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}"/></td>
										<#elseif bgMapleDetail.mapleDetailCode == bgMaple.mapleCode+"Name" >
											<#if bgMaple.mapleType == "02">
											<td class='center'><a href="javascript:toSub('${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}')">${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}</a></td>
											<#elseif bgMaple.mapleType == "03">
											<td class='center'><a href="javascript:toDetail('${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}')">${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}</a></td>
											<#else>
											<td class='center'>${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}</td>
											</#if>
										<#else>
											<td class='center'>${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}</td>
										</#if>
										</#if>
										</#list>
											<td class='center'>${r"${"}${bgMaple.mapleEntityLower}.orderNum${r"}"}</td>
											<td class="center">
												<c:if test="${r"${!RIGHTS.edit && !RIGHTS.del }"}">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${r"${RIGHTS.edit}" }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="toEdit('${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}'<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, '${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}'</#if></#list>);">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${r"${RIGHTS.del }"}">
													<a class="btn btn-xs btn-danger" onclick="toDelete('${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMaple.mapleCode}Id${r"}"}'<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, '${r"${"}${bgMaple.mapleEntityLower}${r"."}${bgMapleDetail.mapleDetailCode}${r"}"}'</#if></#list>);">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${r"${!RIGHTS.sele}"}">
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
									<c:if test="${r"${RIGHTS.add }"}">
									<a class="btn btn-mini btn-success" onclick="toAdd();">新增</a>
									</c:if>
									<c:if test="${r"${RIGHTS.del }"}">
									<a class="btn btn-mini btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${r"${bgPage.pageStr}"}</div></td>
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
			$("#${bgMaple.mapleCode}Form").submit();
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
		
		<#if bgMaple.mapleType == "02">
		//去此ID下子菜单列表
		function toSub(parentId){
			top.jzts();
			window.location.href="<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/list.do?pId="+parentId;
		};
		
		<#elseif bgMaple.mapleType == "03">
		//去此ID下详情页面
		function toDetail(${bgMaple.mapleCode}Id){
			top.jzts();
			window.location.href="<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}Detail/list.do?${bgMaple.mapleCode}Id="+${bgMaple.mapleCode}Id;
		}
		
		</#if>
		//新增
		function toAdd(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 <#if bgMaple.mapleType == "02">
			 diag.URL = "<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toAdd.do?pId="+'${r"${pd.pId}"}';
			 <#elseif bgMaple.mapleType == "04">
			 diag.URL = "<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toAdd.do?${bgMaple.mapleCode ?replace('Detail','')}Id=${r"${pd."}${bgMaple.mapleCode ?replace('Detail','')}Id ${r"}"}";
			 <#else>
			 diag.URL = "<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toAdd.do";
			 </#if>
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag.ShowMaxButton = true;	//最大化按钮
		     	 diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){	
					<#if bgMaple.mapleType == "02">
					parentReload('change','${r"${bgPage.currentPage}"}','${r"${bgPage.showCount}"}');
					<#else>
					if('${r"${bgPage.currentPage}"}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage('${r"${bgPage.currentPage}"}');
					 }
					</#if>
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function toDelete(${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toDelete.do?${bgMaple.mapleCode}Id="+${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">+"&${bgMapleDetail.mapleDetailCode}="+${bgMapleDetail.mapleDetailCode}</#if></#list>+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data.resultCode == "success"){
							<#if bgMaple.mapleType == "02">
							parentReload('change','${r"${bgPage.currentPage}"}','${r"${bgPage.showCount}"}');
							<#else>
							nextPage('${r"${bgPage.currentPage}"}');
							</#if>
						}
					});
				}
			});
		}
		
		//修改
		function toEdit(${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = "<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toEdit.do?${bgMaple.mapleCode}Id="+${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">+"&${bgMapleDetail.mapleDetailCode}="+${bgMapleDetail.mapleDetailCode}</#if></#list>+"&tm="+new Date().getTime();
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     	 diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 <#if bgMaple.mapleType == "02">
					parentReload('change','${r"${bgPage.currentPage}"}','${r"${bgPage.showCount}"}');
					<#else>
					nextPage('${r"${bgPage.currentPage}"}');
					</#if>
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
								url: '<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toBatchDelete.do?tm='+new Date().getTime(),
						    	data: {ids:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									if(data.resultCode == "success"){
										<#if bgMaple.mapleType == "02">
										parentReload('change','${r"${bgPage.currentPage}"}','${r"${bgPage.showCount}"}');
										<#else>
										nextPage('${r"${bgPage.currentPage}"}');
										</#if>
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
			window.location.href='<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toExportExcel.do';
		}
		
		//打开上传excel页面
		function toUploadExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 <#if bgMaple.mapleType == "02">
			 diag.URL = '<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toUploadExcel.do?pId='+'${r"${pd.pId}"}';
			 <#elseif bgMaple.mapleType == "04">
			 diag.URL = '<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toUploadExcel.do?pId=${r"${pd."}${bgMaple.mapleCode ?replace('Detail','')}Id ${r"}"}';
			 <#else>
			 diag.URL = '<%=basePath%>${bgMaple.controllerPackage}/${bgMaple.mapleCode}/toUploadExcel.do';
			 </#if>
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 <#if bgMaple.mapleType == "02">
					parentReload('change','${r"${bgPage.currentPage}"}','${r"${bgPage.showCount}"}');
					<#else>
					nextPage('${r"${bgPage.currentPage}"}');
					</#if>
				}
				diag.close();
			 };
			 diag.show();
		}
	</script>

</body>
</html>
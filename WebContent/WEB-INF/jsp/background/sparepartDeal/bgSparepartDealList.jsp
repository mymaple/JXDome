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
						<form action="background/sparepartDeal/list.do" method="post" name="sparepartDealForm" id="sparepartDealForm">
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
									<th class="center">零部件交易订单号</th>
									<th class="center">零部件销售客户</th>
									<th class="center">零部件</th>
									<th class="center">出售数量</th>
									<th class="center">交易金额</th>
									<th class="center">订单日期</th>
									<th class="center">状态</th>
									<th class="center">申请人</th>
									<th class="center">申请时间</th>
									<th class="center">审核结果</th>
									<th class="center">审核人</th>
									<th class="center">审核时间</th>
									<th class="center">排序编号</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty comSparepartDealList}">
									<c:if test="${RIGHTS.sele}">
									<c:forEach items="${comSparepartDealList}" var="comSparepartDeal" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${comSparepartDeal.sparepartDealId}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${comSparepartDeal.sparepartDealCode}</td>
											<td class='center'><param:display type="com_appUserEffective" value="${comSparepartDeal.appUserId}"/></td>
											<td class='center'><param:display type="com_sparepartEffective" value="${comSparepartDeal.sparepartId}"/></td>
											<td class='center'>${comSparepartDeal.count}</td>
											<td class='center'>${comSparepartDeal.dealAmt}</td>
											<td class='center'>${comSparepartDeal.orderTime}</td>
											<td class='center'><param:display type="com_sparepartDealStatus" value="${comSparepartDeal.sparepartDealStatus}"/></td>
											<td class='center'><param:display type="bg_userEffective" value="${comSparepartDeal.createUserId}"/></td>
											<td class='center'>${comSparepartDeal.createTime}</td>
											<td class='center'>${comSparepartDeal.remarks}</td>
											<td class='center'><param:display type="bg_userEffective" value="${comSparepartDeal.checkId}"/></td>
											<td class='center'>${comSparepartDeal.checkTime}</td>
											<td class='center'>${comSparepartDeal.orderNum}</td>
											<td class="center">
											
												<c:if test="${!RIGHTS.edit && !RIGHTS.del }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${comSparepartDeal.sparepartDealStatus == '00'}">
													<a class="btn btn-xs btn-info" title="申请" onclick="toChangeStatus('01','${comSparepartDeal.sparepartDealId}');">申请
													</a>
													</c:if>
													<c:if test="${comSparepartDeal.sparepartDealStatus == '01'}">
													<a class="btn btn-xs btn-success" title="审核通过" onclick="toChangeStatus('02','${comSparepartDeal.sparepartDealId}');">通过审核
													</a>
													<a class="btn btn-xs btn-danger" title="审核失败" onclick="toChangeStatus('03','${comSparepartDeal.sparepartDealId}');">不通过审核
													</a>
													</c:if>
													<c:if test="${comSparepartDeal.sparepartDealStatus == '03'}">
													<a class="btn btn-xs btn-info" title="重新申请" onclick="toChangeStatus('04','${comSparepartDeal.sparepartDealId}');">重新申请
													</a>
													</c:if>
													
													<c:if test="${comSparepartDeal.sparepartDealStatus == '00' || comSparepartDeal.sparepartDealStatus == '03'}">
													<c:if test="${RIGHTS.edit}">
													<a class="btn btn-xs btn-success" title="编辑" onclick="toEdit('${comSparepartDeal.sparepartDealId}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${RIGHTS.del }">
													<a class="btn btn-xs btn-danger" onclick="toDelete('${comSparepartDeal.sparepartDealId}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
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
									
									<a class="btn btn-xs btn-info" title="申请" onclick="toBatchChangeStatus('01');">申请</a>
									<a class="btn btn-xs btn-success" title="审核通过" onclick="toBatchChangeStatus('02');">通过审核</a>
									<a class="btn btn-xs btn-danger" title="审核失败" onclick="toBatchChangeStatus('03');">不通过审核</a>
									<a class="btn btn-xs btn-info" title="重新申请" onclick="toBatchChangeStatus('04');">重新申请</a>
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
			$("#sparepartDealForm").submit();
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
		
		//新增
		function toAdd(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = "<%=basePath%>background/sparepartDeal/toAdd.do";
			 diag.Width = 450;
			 diag.Height = 450;
			 diag.Modal = true;				//有无遮罩窗口
			 diag.ShowMaxButton = true;	//最大化按钮
		     	 diag.ShowMinButton = true;		//最小化按钮
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
		
		//删除
		function toDelete(sparepartDealId){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>background/sparepartDeal/toDelete.do?sparepartDealId="+sparepartDealId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data.resultCode == "success"){
							nextPage('${bgPage.currentPage}');
						}
					});
				}
			});
		}
		
		//修改
		function toEdit(sparepartDealId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = "<%=basePath%>background/sparepartDeal/toEdit.do?sparepartDealId="+sparepartDealId+"&tm="+new Date().getTime();
			 diag.Width = 450;
			 diag.Height = 450;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     	 diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${bgPage.currentPage}');
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
								url: '<%=basePath%>background/sparepartDeal/toBatchDelete.do?tm='+new Date().getTime(),
						    	data: {ids:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									if(data.resultCode == "success"){
										nextPage('${bgPage.currentPage}');
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
			window.location.href='<%=basePath%>background/sparepartDeal/toExportExcel.do';
		}
		
		//打开上传excel页面
		function toUploadExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>background/sparepartDeal/toUploadExcel.do';
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 if('${bgPage.currentPage}' == '0'){
					 top.jzts();
					 setTimeout("self.location=self.location",100);
				 }else if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${bgPage.currentPage}');
				}
				diag.close();
			 };
			 diag.show();
		}
		
		/*change 01:申请，02成功，03失败，04再申请  */
		function toChangeStatus(change,sparepartDealId){
			console.log(change);
			top.jzts();
			$.ajax({
				type: "POST",
				url: '<%=basePath%>background/sparepartDeal/toChangeStatus.do?tm='+new Date().getTime(),
		    	data: {"change":change,"sparepartDealId":sparepartDealId},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					alert(data.resultCode);
					console.log(data.resultCode);
					if(data.resultCode == "success"){
						nextPage('${bgPage.currentPage}');
					}
				}
			});
		}
		
		/*change 01:申请，02成功，03失败，04再申请  */
		function toBatchChangeStatus(change){
			bootbox.confirm(change, function(result) {
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
						top.jzts();
						$.ajax({
							type: "POST",
							url: '<%=basePath%>background/sparepartDeal/toBatchChangeStatus.do?tm='+new Date().getTime(),
					    	data: {"change":change,ids:str},
							dataType:'json',
							//beforeSend: validateData,
							cache: false,
							success: function(data){
								if(data.resultCode == "success"){
									nextPage('${bgPage.currentPage}');
								}
							}
						});
					}
				}
			});
		};
	</script>

</body>
</html>
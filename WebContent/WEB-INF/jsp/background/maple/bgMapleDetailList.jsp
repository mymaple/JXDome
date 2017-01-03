<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<div class="page-header">
							<h1>
								Dashboard
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									overview &amp; stats
								</small>
							</h1>
					</div>
					<!-- /.page-header -->
					
					<div class="row">
						<div class="col-xs-12">
						<div class="table-header center">asdasdas</div>
						<input type="hidden" name="mapleId" id="mapleId" value="${bgMaple.mapleId }"/>
						<table class="table table-striped table-bordered table-hover" style="margin-top:5px;">
							<tbody>
							<tr>
								<td>代号</td>
								<td>${bgMaple.mapleCode}</td>
								<td>名称</td>
								<td>${bgMaple.mapleName}</td>
							</tr>
							<tr>
								<td>类型</td>
								<td>${bgMaple.mapleType}</td>
								<td>控制包名</td>
								<td>${bgMaple.controllerPackage}</td>
							</tr>
							<tr>
								<td>实体类包名</td>
								<td>${bgMaple.entityPackage}</td>
								<td>上次修改时间</td>
								<td>${bgMaple.modifyTime}</td>
							</tr>
							</tbody>
						</table>
						
						<div class="hr hr-18 dotted hr-double"></div>
						
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">属性代号</th>
									<th class="center">属性名称</th>
									<th class="center">属性类型</th>
									<th class="center">属性长度</th>
									<th class="center">是否录入</th>
									<th class="center">是否null</th>
									<th class="center">默认值</th>
									<th class="center">上次修改时间</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty bgMapleDetailList}">
									<c:if test="${RIGHTS.sele }">
									<c:forEach items="${bgMapleDetailList}" var="bgMapleDetail" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${bgMapleDetail.mapleDetailId}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class="center">${bgMapleDetail.mapleDetailCode}</td>
											<td class="center">${bgMapleDetail.mapleDetailName}</td>
											<td class="center">${bgMapleDetail.mapleDetailType}</td>
											<td class="center">${bgMapleDetail.length}</td>
											<td class="center">${bgMapleDetail.isEdit}</td>
											<td class="center">${bgMapleDetail.isNull}</td>
											<td class="center">${bgMapleDetail.defaultValue}</td>
											<td class="center">${bgMapleDetail.modifyTime}</td>
											<td class="center">
												<c:if test="${!RIGHTS.edit && !RIGHTS.del }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${RIGHTS.edit }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="toEdit('${bgMapleDetail.mapleDetailId}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${RIGHTS.del }">
													<a class="btn btn-xs btn-danger" onclick="toDelete('${bgMapleDetail.mapleDetailId}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${RIGHTS.sele}">
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
									<a class="btn btn-mini btn-success" onclick="toCreateCode();">生成代码</a>
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
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function searchs(){
			top.jzts();
			$("#createCodeFrom").submit();
		}
		$(function() {
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
		
		//删除
		function toDelete(mapleDetailId,msg){
			var mapleId = $('#mapleId').val();
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					var url = '<%=basePath%>background/mapleDetail/toDelete.do?mapleId='+mapleId+'&mapleDetailId='+mapleDetailId+'&tm='+new Date().getTime();
					$.get(url,function(data){
						if("success" == data.result){
							nextPage('${bgPage.currentPage}');
						}
					});
				};
			});
		}

		//新增
		function toAdd(){
			var mapleId = $('#mapleId').val();
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>background/mapleDetail/toAdd.do?mapleId='+mapleId;
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
		
		//启动代码生成器
		function toEdit(mapleDetailId){
			var mapleId = $('#mapleId').val();
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="代码生成器";
			 diag.URL = '<%=basePath%>background/mapleDetail/toEdit.do?mapleId='+mapleId+'&mapleDetailId='+mapleDetailId;
			 diag.Width = 800;
			 diag.Height = 500;
			 diag.Modal = true;				//有无遮罩窗口
			 diag.ShowMaxButton = true;		//最大化按钮
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
								type:"POST",
								url:'<%=basePath%>background/maple/deleteAll.do?tm='+new Date().getTime(),
						    	data:{DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache:false,
								success:function(data){
									 $.each(data.list, function(i, list){
											nextPage('${bgPage.currentPage}');
									 });
								}
							});
						}
					}
				}
			});
		};
		
		function toCreateCode(){
			var mapleId = $('#mapleId').val();
			var url = '<%=basePath%>background/mapleDetail/toCreateCode.do?mapleId='+mapleId+'&tm='+new Date().getTime();
			window.location.href=url;
		}
		
	</script>
</body>
</html>
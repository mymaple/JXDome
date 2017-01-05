<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>保存结果</title>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="main/bgIndexTop.jsp"%>
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
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr><th style="text-align: center;">保存失败</th></tr>
							<tr>
								<td style="text-align: left;">失败原因:${resultInfo.resultContent}</td>
							</tr>
							<tr>
								<td style="text-align: center;">
									<a class="btn btn-mini btn-primary" href="javascript:history.back(-1)">返 回</a> 
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">关闭</a>
								</td>
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
</div>
<!-- /.main-container -->

<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="main/bgIndexFoot.jsp"%>
		<script type="text/javascript">
		$(top.hangge());
		var resultCode = '${resultInfo.resultCode}';
		if(resultCode=="success"){
			document.getElementById('zhongxin').style.display = 'none';
			top.Dialog.close();
		}
		</script>
</body>
</html>
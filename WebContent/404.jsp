<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/static/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>

<meta name="description" content="404 Error Page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link href="ace/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="ace/css/font-awesome.css" />
<!-- page specific plugin styles -->
<!-- ace styles -->
<link rel="stylesheet" href="ace/css/ace.css" />
<link rel="stylesheet" href="ace/css/ace-skins.css" />
<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
<body>
	<div class="container-fluid" id="main-container">

		<div id="main-content" class="clearfix">

			<div id="page-content" class="clearfix">

				<div class="row-fluid">
					<!-- PAGE CONTENT BEGINS HERE -->
					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125"><i class="icon-sitemap"></i> 404</span> 没有找到此页面
							</h1>
							<hr />
							<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

							<div>

								<div class="space"></div>

								<h4 class="smaller">Try one of the following:</h4>
								<ul class="unstyled spaced inline bigger-110">
									<li><i class="icon-hand-right blue"></i> 检查路径是不是有误</li>
									<li><i class="icon-hand-right blue"></i> 检查代码是不是有误</li>
									<li><i class="icon-hand-right blue"></i> 检查环境配置是不是有误</li>
								</ul>
							</div>

							<hr />
							<div class="space"></div>

							<div class="row-fluid">
								<div id="zhongxin"></div>
							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS HERE -->
				</div>
				<!--/row-->

			</div>
			<!--/#page-content-->

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<!-- basic scripts -->
	<script src="ace/js/jquery.js"></script>

	<script src="ace/js/bootstrap.js"></script>
	<!-- page specific plugin scripts -->

	<!-- ace scripts -->
	<script src="ace/js/ace-elements.js"></script>
	<script src="ace/js/ace.js"></script>
	<!-- inline scripts related to this page -->

</body>
</html>
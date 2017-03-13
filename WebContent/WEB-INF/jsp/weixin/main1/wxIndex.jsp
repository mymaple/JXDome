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
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>toIndex</title>
	<link rel="stylesheet" href="weui/dist/style/weui.min.css"/>
</head>
<body>

     <div class="weui-btn-area">
       	<a class="weui-btn weui-btn_primary" href="<%=basePath%>weixin/mine/toMyWallet.do" id="showTooltips">我的钱包</a>
   	</div>
	
</body>

<script type="text/javascript" src="weui/dist/js/jquery-3.1.1.min.js"></script>


</html>
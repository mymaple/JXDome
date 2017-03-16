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
	<title>toLogin</title>
	<link rel="stylesheet" href="weui/dist/style/weui.min.css"/>
</head>
<body>
	<div class="weui_msg">
	    <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
	    <div class="weui_text_area">
	        <h2 class="weui_msg_title">操作成功</h2>
	        <p class="weui_msg_desc">内容详情，可根据实际需要安排</p>
	    </div>
	    <div class="weui_opr_area">
	        <p class="weui_btn_area">
	            <a href="#" class="weui_btn weui_btn_primary">确定</a>
	            <a href="#" class="weui_btn weui_btn_default">取消</a>
	        </p>
	    </div>
	    <div class="weui_extra_area">
	        <a href="">查看详情</a>
	    </div>
	</div>
	
	<div class="weui_msg">
	    <div class="weui_icon_area"><i class="weui_icon_warn weui_icon_msg"></i></div>
	    <div class="weui_text_area">
	        <h2 class="weui_msg_title">操作失败</h2>
	        <p class="weui_msg_desc">内容详情，可根据实际需要安排</p>
	    </div>
	    <div class="weui_opr_area">
	        <p class="weui_btn_area">
	            <a href="#" class="weui_btn weui_btn_primary">确定</a>
	            <a href="#" class="weui_btn weui_btn_default">取消</a>
	        </p>
	    </div>
	    <div class="weui_extra_area">
	        <a href="">查看详情</a>
	    </div>
	</div>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
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
	<title>格陌汽配</title>
	<script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css"/>
  	<link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	function toMonth(){
		var yearMonth = $("#yearMonth").val();
		location.replace("<%=basePath %>weixin/note/toIntegralNoteList.do?yearMonth="+yearMonth);
	}
	
</script>
</head>
<!--loading页开始-->
<div class="loading">
	<div class="loader">
        <div class="loader-inner pacman">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
	</div>
</div>
<!--loading页结束-->
<body>
       <header class="mui-bar2 mui-bar-nav box-s" id="header">
			<input type="month" id="yearMonth" class="m-txt" onchange="toMonth();" value="${yearMonth }">
	   </header>
	    
	    <div id="main" class=" warpthree clearfloat">
            <div class="check-c">
            <c:choose>
            	<c:when test="${not empty comIntegralNoteList}">
            	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="c-tab">
					<c:forEach items="${comIntegralNoteList}" var="comIntegralNote" varStatus="vs">
            			 <tr>
						    <td class="col1" width="20%"><p>${comIntegralNote.createTimeStr }</p></td>
						    <td class="col2" width="10%"><!-- <img src="upload/26.jpg"> --></td>
						    <td class="col3" width="50%">
						    	<p class="num">
						    		<c:if test="${comIntegralNote.integralDealStatus == '01'}">+</c:if>
						    		<c:if test="${comIntegralNote.integralDealStatus == '00'}">-</c:if>
						    		${comIntegralNote.integralDealCount }</p>
						    	<p class="over2">${comIntegralNote.integralNoteName }</p></td>
						    <td class="col4"><param:display type="com_integralNoteType" value="${comIntegralNote.integralNoteType }"/></td>
						 </tr>
            		</c:forEach>
            	</table>
            	</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </div>
	    </div>
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js" type="text/javascript"></script>
	<script src="weui/gemo/js/others.js" type="text/javascript"></script>
	<script src="weui/gemo/js/hmt.js" type="text/javascript"></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>
</html>
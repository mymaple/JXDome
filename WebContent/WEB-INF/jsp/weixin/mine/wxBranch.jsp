<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
<%@ page import="com.jx.common.config.Const"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>格陌汽配</title>
<script src="weui/gemo/js/rem.js?${resultInfo.version}"></script>
<script src="weui/gemo/js/jquery.min.js?${resultInfo.version}" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css?${resultInfo.version}" />
<link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.min.css?${resultInfo.version}" />
<script type="text/javascript" src="plugins/layer/js/layer.js?${resultInfo.version}"></script>
<script type="text/javascript">
	$(window).load(function() {
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
	
	function toTransfer(toAppUserId){
		window.location.href = "<%=basePath%>weixin/mine/toTransfer.do?toAppUserId="+toAppUserId;
	}
	
	function toBranch(toAppUserId,appUserRoleId){
		window.location.href = "<%=basePath%>weixin/mine/toBranch.do?toAppUserId="+toAppUserId+"&appUserRoleId="+appUserRoleId;
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
	<div class=" warpthree clearfloat">
		<c:choose>
					<c:when test="${not empty comAppUserRoleList}">
					<div class="h-tit h-tit-c clearfloat box-s">
		    		<p><param:display type="com_appUserRoleType" value="${comAppUserRoleList[0].appUserRoleType}"/></p>
		    		</div>	
				    		<div class="h-tab">
							<table width="100%" border="0">
					<c:forEach items="${comAppUserRoleList}" var="subComAppUserRole" varStatus="vs4">	
						<c:choose>
						<c:when test="${not empty subComAppUserRole.comAppUserList}">
						<c:forEach items="${subComAppUserRole.comAppUserList}" var="subComAppUser" varStatus="vs5">
							<tr>
						    <td width="15%"><img src="<%=Const.BG_WEBSITE %>/${subComAppUser.headImgSrc}"></td>
						    <td width="35%"><div class="qu-l">${subComAppUser.appUserName}
						    <c:if test="${subComAppUser.appUserId == userId}">(我)</c:if>
							    <p class="col1"><param:display type="com_appUserRoleEffectiveP" value="${subComAppUserRole.appUserRoleId }"/></p>
							    <p class="col2">${subComAppUser.phone}</p></div></td>
						    <td width="50%">
						    	<c:if test="${subComAppUserRole.appUserRoleType != '04'}">
						    	<a class="qu-btn1 qu-bd2" onclick="toBranch('${subComAppUser.appUserId}','${subComAppUserRole.appUserRoleId}')">查看</a>
						    	</c:if>
						    	<c:if test="${subComAppUser.appUserId != userId}">
						    	<a href="tel:${subComAppUser.phone}" class="qu-btn1 qu-bd1">拨打</a>
						    	<a class="qu-btn1 qu-bd1" onclick="toTransfer('${subComAppUser.appUserId}');">转赠</a>
						    	</c:if>
							    </td>
						  	</tr>
						</c:forEach>
						</c:when>
						</c:choose>
					</c:forEach>
							</table>
							</div>
					</c:when>
					</c:choose>
					
					
		<div class="warp"></div>
	     <!--footer star-->
		<footer class="page-footer fixed-footer" id="footer">
			<ul>
				<li>
					<a href="<%=basePath%>weixin/index/toIndex.do">
						<i class="iconfont icon-shouye"></i>
						<p>首页</p>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>weixin/product/toCategory.do">
						<i class="iconfont icon-icon04"></i>
						<p>分类</p>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>weixin/shopCar/list.do">
						<i class="iconfont icon-gouwuche"></i>
						<p>购物车</p>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>weixin/mine/toMyCenter.do">
						<i class="iconfont icon-yonghuming"></i>
						<p>个人中心</p>
					</a>
				</li>
			</ul>
		</footer>
		<!--footer end-->   
</body>
<script src="weui/gemo/js/mui.min.js?${resultInfo.version}"></script>
<script src="weui/gemo/js/others.js"?${resultInfo.version}></script>
<script type="text/javascript" src="weui/gemo/js/hmt.js?${resultInfo.version}"></script>
<script src="weui/gemo/slick/slick.js?${resultInfo.version}" type="text/javascript"></script>
<!--插件-->
<link rel="stylesheet" href="weui/gemo/css/swiper.min.css?${resultInfo.version}">
<script src="weui/gemo/js/swiper.jquery.min.js?${resultInfo.version}"></script>
</html>


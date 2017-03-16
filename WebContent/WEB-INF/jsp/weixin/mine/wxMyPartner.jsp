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
    <meta name="keywords" content="">
    <meta name="description" content="">
	<script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css"/>
    <link rel="stylesheet" href="weui/dist/style/layer.css"/>
	<script type="text/javascript" src="weui/dist/js/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.min.css" />
	<link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.css" />
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
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
        	<c:if test="${not empty comAppUserParent}">
           	<div class="h-tit clearfloat box-s">
	    		<p><param:display type="com_appUserRole" value="${comAppUserParent.roleId}"/><span></span></p>
	    	</div>
	    	<div class="account clearfloat">
	    		<div class="t-bt clearfloat">
	    			<ul>
	    				<li class="box-s l1"></li>
	    				<li class="l2">姓名</li>
	    				<li class="l3">电话</li>
	    			</ul>
	    		</div>
	    		<div class="t-list clearfloat box-s">
	    			<div class="shang clearfloat">
	    				<ul>
		    				<li class="l4"><img src="${comAppUserParent.headImgSrc}"></li>
                            <li class="l5">${comAppUserParent.appUserName}</li>
		    				<li class="l6">${comAppUserParent.phone}</li>
		    			</ul>
	    			</div>
	    		</div>
	    	</div> 
	   		</c:if>
	    	
	    	<c:choose>
				<c:when test="${not empty comAppUserSubList}">
					<div class="h-tit clearfloat box-s">
			    		<p>下级<span></span></p>
			    	</div>
			    	<div class="account clearfloat">
			    		<div class="t-bt clearfloat">
			    			<ul>
			    				<li class="box-s l1"></li>
			    				<li class="l2">姓名</li>
			    				<li class="l3">电话</li>
			    			</ul>
			    		</div>
			    		<div class="t-list clearfloat box-s">
			    			<div class="shang clearfloat">
			    				<ul>
					<c:forEach items="${comAppUserSubList}" var="comAppUser" varStatus="vs">
									<li class="l4"><img src="${comAppUser.headImgSrc}"></li>
		                            <li class="l5">${comAppUser.appUserName}</li>
				    				<li class="l6">${comAppUser.phone}</li>
					</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			
	    </div>	    
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script src="weui/gemo/js/mui.picker.min.js"></script>
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>
</html>

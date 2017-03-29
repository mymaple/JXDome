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
	<title>格陌汽配</title>
	<link rel="stylesheet" href="weui/dist/style/weui.min.css"/>
	<script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	function toOrderDetail(orderId){
		window.location.href = "<%=basePath%>weixin/order/toOrderDetail.do?orderId="+orderId;
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

	    <div id="main" class="mui-clearfix">
	    
		    <div class="order-top clearfloat">
	    		<ul>
	    			<li class="clearfloat"><a href="order.html">全部</a></li>
	    			<li class="clearfloat"><a href="#">待付款</a></li>
	    			<li class="clearfloat"><a href="#">待发货</a></li>
	    			<li class="clearfloat cur"><a href="">待收货</a></li>
	    			<li class="clearfloat"><a href="#">待评价</a></li>
	    		</ul>
	    	</div>
	    
	    <c:choose>
        <c:when test="${not empty comOrderList}">
		<c:forEach items="${comOrderList}" var="comOrder" varStatus="vs">
	    
	      <div class="order-list clearfloat" onclick="toOrderDetail('${comOrder.orderId}');">
    		  <p class="ordernum box-s">
	    			订单  ${comOrder.orderCode } <span class="fr">${comOrder.createTime }</span>    			
    		  </p>
    		  <c:choose>
		        <c:when test="${not empty comOrder.comOrderDetailList}">
				<c:forEach items="${comOrder.comOrderDetailList}" var="comOrderDetail" varStatus="vs1">
				
	    		<div class="list clearfloat fl box-s">
	    			<div class="tu fl clearfloat">
	    				<img src="${comOrderDetail.headImgSrc}"/>
	    			</div>
	    			<div class="middle clearfloat fl">
	    				<p class="tit">${comOrderDetail.productName}</p>
	    				<p class="fu-tit">${comOrderDetail.summary}</p>
	    				<p class="price clearfloat">
	    					<span class="xprice fl">积分：${comOrderDetail.currentPrice}</span>
	    					<span class="yprice fl">积分：${comOrderDetail.currentPrice}</span>
	    					<span class="shu">×1</span>
	    				</p>
	    			</div>
	    		</div>
	    		</c:forEach>
	    		</c:when>
	    		</c:choose>
	    				<p class="odr-jg box-s">
			    			合计：<span><b>￥</b>1200<s:property value="#inner.payPrice" /></span>（余额支付：100）
			    		</p>
						<a onclick="" class="blue-btn ra3 fr">
							再次购买
						</a>
						<a  onclick="" class="gray-btn ra3 fr">
							我要申诉
						</a>
						<a  onclick="" class="lijzf-btn ra3 fr">
							立即评价
						</a> 
	    	</div>
            </c:forEach>
            </c:when>
            <c:otherwise>
            <div class="empty-list clearfloat" id="main">
			    <i class="iconfont icon-icon04"></i>
			    <p>亲，您还没有相关的订单哦！</p>
                <p class="p-ft">可以去看看有哪些要买的</p>
		   </div>
            </c:otherwise>
            </c:choose>
	    </div>
	</body>

	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>


</html>
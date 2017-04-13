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
	
	function toBuyMore(orderId){
		window.location.href = "<%=basePath%>weixin/order/toBuyMore.do?orderId="+orderId;
	}
	
	function toPay(orderId){
		window.location.href = "<%=basePath%>weixin/order/toPay.do?orderId="+orderId;
	}
	
	function toCancle(orderId){
		$.post("<%=basePath%>weixin/order/toCancle.do",{orderId:orderId},
				function(data){
			if(data.resultCode=='success'){
				window.location.reload();
			}else{
				layer.open({
			    	content: data.resultContent
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
			}
		});
	}
	
	function toRemind(orderId){
		$.post("<%=basePath%>weixin/order/toRemind.do",{orderId:orderId},
				function(data){
			if(data.resultCode=='success'){
				window.location.reload();
			}else{
				layer.open({
			    	content: data.resultContent
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
			}
		});
	}
	
	function toWl(orderId){
		window.location.href = "<%=basePath%>weixin/order/toWl.do?orderId="+orderId;
	}
	
	function toRefund(orderId){
		window.location.href = "<%=basePath%>weixin/order/toRefund.do?orderId="+orderId;
	}
	
	function toConfirm(orderId){
		$.post("<%=basePath%>weixin/order/toConfirm.do",{orderId:orderId},
				function(data){
			if(data.resultCode=='success'){
				window.location.reload();
			}else{
				layer.open({
			    	content: data.resultContent
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
			}
		});
	}
	
	function toEvaluate(orderId){
		window.location.href = "<%=basePath%>weixin/order/toEvaluate.do?orderId="+orderId;
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
	    			<li class="clearfloat <c:if test="${empty state}">cur</c:if>"><a href="<%=basePath%>weixin/order/list.do">全部</a></li>
	    			<li class="clearfloat <c:if test="${state=='01'}">cur</c:if>"><a href="<%=basePath%>weixin/order/list.do?state=01">待付款</a></li>
	    			<li class="clearfloat <c:if test="${state=='02'}">cur</c:if>"><a href="<%=basePath%>weixin/order/list.do?state=02">待发货</a></li>
	    			<li class="clearfloat <c:if test="${state=='03'}">cur</c:if>"><a href="<%=basePath%>weixin/order/list.do?state=03">待收货</a></li>
	    			<li class="clearfloat <c:if test="${state=='04'}">cur</c:if>"><a href="<%=basePath%>weixin/order/list.do?state=04">待评价</a></li>
	    		</ul>
	    	</div>
	    
	    <c:choose>
        <c:when test="${not empty comOrderList}">
		<c:forEach items="${comOrderList}" var="comOrder" varStatus="vs">
	    
	      <div class="order-list clearfloat" onclick="toOrderDetail('${comOrder.orderId}');">
    		  <p class="ordernum box-s">
	    			订单  ${comOrder.orderCode } <span class="fr"><param:display type="com_orderState" value="${comOrder.orderStatus}"/></span>    			
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
	    					<span class="yprice fl">积分：${comOrderDetail.originalPrice}</span>
	    					<span class="shu">×1</span>
	    				</p>
	    			</div>
	    		</div>
	    		</c:forEach>
	    		</c:when>
	    		</c:choose>
	    				<p class="odr-jg box-s">
			    			合计：<span>${comOrder.allActPrice}</span>（优惠：${comOrder.allDisPrice}）
			    		</p>
			    		
		    		<c:if test="${comOrder.orderStatus == '00' }">
		    			<a onclick="toBuyMore('${comOrder.orderId}');" class="blue-btn ra3 fr">再次购买</a>
		    		</c:if>
		    		<c:if test="${comOrder.orderStatus == '01' }">
		    			<a onclick="toPay('${comOrder.orderId}');" class="blue-btn ra3 fr">立即支付</a>
		    			<a onclick="toCancle('${comOrder.orderId}');" class="lijzf-btn ra3 fr">取消订单</a>
		    		</c:if>
		    		<c:if test="${comOrder.orderStatus == '02' }">
		    			<a onclick="toRemind('${comOrder.orderId}');" class="lijzf-btn ra3 fr">提醒发货</a>
		    		</c:if>
		    		<c:if test="${comOrder.orderStatus == '03' }">
		    			<a onclick="toWl('${comOrder.orderId}');" class="blue-btn ra3 fr">查看物流</a>
		    			<a onclick="toRefund('${comOrder.orderId}');" class="gray-btn ra3 fr">我要退款</a>
		    			<a onclick="toConfirm('${comOrder.orderId}');" class="blue-btn ra3 fr">确认收货</a>
		    		</c:if>
		    		<c:if test="${comOrder.orderStatus == '04' }">
		    			<a onclick="toWl('${comOrder.orderId}');" class="blue-btn ra3 fr">查看物流</a>
		    			<a onclick="toEvaluate('${comOrder.orderId}');" class="lijzf-btn ra3 fr">立即评价</a>
		    		</c:if>
		    		<c:if test="${comOrder.orderStatus == '05' || comOrder.orderStatus == '06' }">
		    			<a onclick="toWl('${comOrder.orderId}');" class="blue-btn ra3 fr">查看物流</a>
		    			<a onclick="toBuyMore('${comOrder.orderId}');" class="blue-btn ra3 fr">再次购买</a>
		    		</c:if>
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

	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>


</html>
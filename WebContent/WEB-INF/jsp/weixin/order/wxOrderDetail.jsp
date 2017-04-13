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
	
	function toChooseReceiveAddress(){
		$("#confirmOrderForm").attr("action","weixin/receiveAddress/list.do");
		$("#confirmOrderForm").submit();
	}
	
	function toProductDetail(productId){
		window.location.href = "<%=basePath%>weixin/product/toProductDetail.do?productId="+productId;
	}
	
	function toChoosePay(){
		$("#confirmOrderForm").submit();
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
		<form action="weixin/order/toPay.do" name="confirmOrderForm" id="confirmOrderForm" method="post">
		
	    <div class=" warptwo clearfloat">
	    	<div class="confirm clearfloat">
	    	
	    		<div class="add clearfloat box-s" onclick="toChooseReceiveAddress();">
		    			<div class="left clearfloat fl">
		    				<i class="iconfont icon-dizhi1"></i>
		    			</div>
		    			<div class="middle clearfloat fl">
		    				<p class="tit">
		    					收货人：${comReceiveAddress.receicerName }&nbsp;&nbsp;&nbsp;&nbsp;${comReceiveAddress.phone }
		    				</p>
		    				<p class="fu-tit over2">
		    					收货地址：${comReceiveAddress.province }&nbsp;${comReceiveAddress.city }&nbsp;${comReceiveAddress.district }<br>
	    					${comReceiveAddress.street }&nbsp;${comReceiveAddress.detail }
		    				</p>
		    			</div>
		    			<div class="left clearfloat fr">
		    				<i class="iconfont icon-jiantou1"></i>
		    			</div>
	    		</div>
                
				<input type="hidden" name="orderId" value="${comOrder.orderId }">
                <c:choose>
		        <c:when test="${not empty comOrder.comOrderDetailList}">
				<c:forEach items="${comOrder.comOrderDetailList}" var="comOrderDetail" varStatus="vs1">
	    		<div class="lie clearfloat" onclick="toProductDetail('${comOrderDetail.productId }')"">
						<div class="tu clearfloat fl">
							<img src="${comOrderDetail.headImgSrc}"/>
						</div>
					<div class="right clearfloat fl">
							<p class="tit over2">${comOrderDetail.productName}</p>
							<p class="tit over2">${comOrderDetail.summary}</p>
							<p class="tit over2">${comOrderDetail.productStyleName}</p>
						<div class="xia clearfloat">
							<p class="jifen fl over">${comOrderDetail.currentPrice}积分</p>
							<span class="fr db">×${comOrderDetail.count}</span>
						</div>
					</div>
				</div>
				</c:forEach>
				</c:when>
				</c:choose>
				
				<div class="gmshu clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">购买数量</p>
			     		<div class="you clearfloat fr">
			     			${comOrder.orderProductCount}件
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">配送方式</p>
			     		<div class="you clearfloat fr">
			     			<span>快递 免邮</span>
			     			<i class="iconfont icon-jiantou1"></i>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshuthree clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">买家留言</p>
			     		<div class="you clearfloat fl">
			     			<input type="text" name="remark" id="" value="" class="text" placeholder="选填 对本次交易需求给商家留言" />
			     		</div>
					</div>		     		
		     	</div>
		     	
	    	</div>
	    </div>	    
	    
		<!--settlement star-->
	    <div class="settlement clearfloat">
	    	<a onclick="toBuyMore('${comOrder.orderId}');" class="blue-btn ra3 fr">再次购买</a>
	    </div>
	    <!--settlement end-->
	    </form>
	</body>

	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>

</html>
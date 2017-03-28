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
	
	function toProductDetail(productId){
		window.location.href = "<%=basePath%>weixin/product/toProductDetail.do?productId="+productId;
	}
	
	function toConfirmOrder(){
		
		if(){
			
		}
		
		$("#shopCarForm").submit();
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
	<form action="weixin/order/toConfirmOrder2.do" name="shopCarForm" id="shopCarForm" method="post">
						
	    <div class="warptwo clearfloat">
	    	<div class="shopcar clearfloat">
	    		<c:set var="supplierId" value=""/>
				<c:choose>
				<div class="list clearfloat fl">
            	<c:when test="${not empty comShopCarList}">
					<c:forEach items="${comShopCarList}" var="comShopCar" varStatus="vs">
	    		<c:if test="${comShopCar.comProduct.supplierId != supplierId }">
	    		</div>
	    		<div class="list clearfloat fl">
	    		<c:set var="supplierId" value="${comShopCar.comProduct.supplierId }"/>
	    		</c:if>
	    		
	    			<div class="xuan clearfloat fl">
	    				<div class="radio" > 
						    <label>
						        <input type="checkbox" name="shopCarId" value="${comShopCar.shopCarId }" />
						        <div class="option"></div>
						    </label>
						</div>
	    			</div>
	    			
		    			<div class="tu clearfloat fl" onclick="toProductDetail('${comShopCar.comProduct.productId }')">
		    				<span></span>
		    				<img src="${comShopCar.comProduct.headImgSrc }"/>
		    			</div>
		    			<div class="right clearfloat fl">
		    				<p class="tit over">${comShopCar.comProduct.productName }</p>
		    				<p class="fu-tit over">${comShopCar.comProduct.comProductStyle.productStyleName }</p>
		    				<p class="jifen over">积分：${comShopCar.comProduct.comProductStyle.currentPrice }积分</p>
		    				<div class="bottom clearfloat">
		    					<div class="zuo clearfloat fl">
		    						<ul>
		    							<li><img src="weui/gemo/img/jian.png"/></li>
		    							<li><input type="number" id="count${comShopCar.shopCarId }" value="${comShopCar.count }" max="${comShopCar.comProduct.comProductStyle.stockNum }"> </li>
		    							<li><img src="weui/gemo/img/jia.png"/></li>
		    						</ul>
		    					</div>
		    					<i class="iconfont icon-lajixiang fr" ></i>
		    				</div>
		    			</div>
	    		</c:forEach>
	    		</div>
	    		</c:when>
	    		<c:otherwise>
	    		</c:otherwise>
	    		</c:choose>
	    	</div>
	    </div>
	    
	    <!--settlement star-->
	    <div class="settlement clearfloat">
	    	<div class="zuo clearfloat fl box-s">
	    		合计：<span>12000</span>
	    	</div>
	    	<a onclick="toConfirmOrder();" class="fl db">
	    		立即结算
	    	</a>
	    </div>
	    <!--settlement end-->
	    </form>
	    
	    
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
				<li class="active">
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
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>

</html>
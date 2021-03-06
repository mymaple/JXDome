<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
<%@ page import="com.jx.common.config.Const"  %>
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
	<script src="weui/gemo/js/rem.js?${resultInfo.version}"></script> 
    <script src="weui/gemo/js/jquery.min.js?${resultInfo.version}" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css?${resultInfo.version}"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js?${resultInfo.version}"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	$(function(){
		ignoreBack();
	});
	
	function ignoreBack(){
		if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}
	
	function toChooseReceiveAddress(){
		$("#confirmOrderForm").attr("action","weixin/receiveAddress/list.do");
		$("#confirmOrderForm").submit();
	}
	
	function toProductDetail(productId){
		window.location.href = "<%=basePath%>weixin/product/toProductDetail.do?productId="+productId;
	}
	
	function toChoosePay(){
		if($("#receiveAddressId").val()==null || $("#receiveAddressId").val()==''){
			layer.open({
		    	content: '请选择收货地址'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			return false;
		}
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
		<input type="hidden" name="receiveAddressId" id="receiveAddressId" value="${comReceiveAddress.receiveAddressId}"/>
		
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
                
        <c:choose>
        <c:when test="${not empty comOrderList}">
        <c:set var="supplierId" value=""/>
		<c:forEach items="${comOrderList}" var="comOrder" varStatus="vs">
				<input type="hidden" name="orderId" value="${comOrder.orderId }">
				<c:if test="${comOrder.supplierId != supplierId || empty supplierId}">
                     <div class="mt">
	    			<p class="gys-tit box-s">供应商：<param:display type="com_supplierEffective" value="${comOrder.supplierId}"/></p>
	    		<c:set var="supplierId" value="${comOrder.supplierId }"/>
	    		</c:if>
                <c:choose>
		        <c:when test="${not empty comOrder.comOrderDetailList}">
				<c:forEach items="${comOrder.comOrderDetailList}" var="comOrderDetail" varStatus="vs1">
	    		<div class="lie clearfloat" onclick="toProductDetail('${comOrderDetail.productId }')"">
						<div class="tu clearfloat fl">
							<img src="<%=Const.BG_WEBSITE %>/${comOrderDetail.headImgSrc}"/>
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
				
				<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">购买数量</p>
			     		<div class="you clearfloat fr">
			     			<span><fmt:parseNumber integerOnly="true" value="${comOrder.orderProductCount}" />件</span>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">配送方式</p>
			     		<div class="you clearfloat fr">
			     			<span>快递 免邮</span>
			     			<!-- <i class="iconfont icon-jiantou1"></i> -->
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshuthree clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">买家留言</p>
			     		<div class="you clearfloat fr">
			     			<input type="text" name="remark" id="" value="" class="text" placeholder="选填 对本次交易需求给商家留言" />
			     		</div>
					</div>		     		
		     	</div>
		     	<div>
		     	
		     </c:forEach>
		     </c:when>
		     </c:choose>
                <!--
		     	<div class="integral clearfloat fl box-s">
		     		<div class="zuo clearfloat fl">
		     			积分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用积分2446
		     		</div>
		     		<div class="you clearfloat fr">
		     			<div class="xuan clearfloat">
		     				<div class="radiothree" > 
							    <label>
							        <input type="checkbox" name="fapiao" value="" />
							        <div class="option"></div>
							    </label>
							</div>
		     			</div>
		     		</div>
		     	</div>
                -->
	    	</div>
	    </div>	    
	    
		<!--settlement star-->
	    <div class="settlement clearfloat" style="bottom:0;">
	    	<div class="zuo clearfloat fl box-s">
	    		共<span><fmt:parseNumber integerOnly="true" value="${payCount }" /></span>件    &nbsp;&nbsp;<span>${pay }</span>积分
	    	</div>
	    	<a onclick="toChoosePay();" class="fl db">
	    		选择支付
	    	</a>
	    </div>
	    <!--settlement end-->
	    </form>
	</body>

	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/js/mui.min.js?${resultInfo.version}"></script>
	<script src="weui/gemo/js/others.js?${resultInfo.version}"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/slick/slick.js?${resultInfo.version}" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css?${resultInfo.version}">
	<script src="weui/gemo/js/swiper.jquery.min.js?${resultInfo.version}"></script>


</html>
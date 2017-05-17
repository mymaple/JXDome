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
	<script type="text/javascript" src="plugins/layer/js/layer.js?${resultInfo.version}"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	function toProductDetail(productId){
		window.location.href = "<%=basePath%>weixin/product/toProductDetail.do?productId="+productId;
	}
	
	function toBuyMore(orderId){
		window.location.href = "<%=basePath%>weixin/order/toBuyMore.do?orderId="+orderId;
	}
	
	function toRemind(orderId){
		$.post("<%=basePath%>weixin/order/toRemind.do",{orderId:orderId},
				function(data){
			if(data.resultCode=='success'){
				layer.open({
			    	content: data.resultContent
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
				//window.location.reload();
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
	
	function toWantRefund(orderId){
		$.post("<%=basePath%>weixin/order/toWantRefund.do",{orderId:orderId},
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
		<%-- window.location.href = "<%=basePath%>weixin/order/toRefund.do?orderId="+orderId; --%>
	}
	
	function toConfirmReceive(orderId){
		$.post("<%=basePath%>weixin/order/toConfirmReceive.do",{orderId:orderId},
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
		<form action="weixin/order/toPay.do" name="confirmOrderForm" id="confirmOrderForm" method="post">
		
	    <div class=" warptwo clearfloat">
	    	<div class="confirm clearfloat">
	    	
	    		<div class="add clearfloat box-s">
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
		    			<!-- <div class="left clearfloat fr">
		    				<i class="iconfont icon-jiantou1"></i>
		    			</div> -->
	    		</div>
                
				<input type="hidden" name="orderId" value="${comOrder.orderId }">
                <c:choose>
		        <c:when test="${not empty comOrder.comOrderDetailList}">
                <div class="mt">
		        <p class="gys-tit box-s">供应商：<param:display type="com_supplierEffective" value="${comOrder.supplierId}"/></p>
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
			     			<span>${comOrder.orderProductCount}件</span>
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
		     	
		     	<c:if test="${not empty comOrder.remark}">
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">买家留言</p>
						<div class="you clearfloat fr">
			     			<span>${comOrder.remark}</span>
			     		</div>
					</div>		     		
		     	</div></div>
	    		</c:if>
		     	
                <div class="mt">
		     	<p class="gys-tit box-s">订单信息</p>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">物流公司</p>
						<div class="you clearfloat fr">
			     			<span><param:display type="com_wlgs" value="${comOrder.wlgs}" /></span>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">运单编号</p>
						<div class="you clearfloat fr">
			     			<span>${comOrder.wlNum}</span>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">创建时间</p>
						<div class="you clearfloat fr">
			     			<span>${comOrder.createTimeStr}</span>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">付款时间</p>
						<div class="you clearfloat fr">
			     			<span>${comOrder.payTimeStr}</span>
			     		</div>
					</div>		     		
		     	</div>
		     	<div class="gmshu gmshutwo clearfloat box-s fl">
					<div class="gcontent clearfloat">
						<p class="fl">发货时间</p>
						<div class="you clearfloat fr">
			     			<span>${comOrder.sendTimeStr}</span>
			     		</div>
					</div>		     		
		     	</div>
	    	</div>
	    </div>	    
	    </div>
	    <!--footerone star-->
		<div class="footerone clearfloat" style="bottom:0;">
			<div class="right clearfloat fr">
				<!-- <a class="btn fl" onClick="toshare();toChoose('1');">加入购物车</a>
				<a onClick="toshare();toChoose('2');" class="btn btnone fr">立即购买</a> -->
				
				<c:if test="${comOrder.orderStatus == '02' }">
	    			<a onclick="toRemind('${comOrder.orderId}');" class="btn btnone fr">提醒发货</a>
	    		</c:if>
	    		<c:if test="${comOrder.orderStatus == '03' }">
	    			<%-- <a onclick="toWl('${comOrder.orderId}');" class="blue-btn ra3 fr">查看物流</a> --%>
	    			<a href="http://dh.cx/${comOrder.wlNum}" class="btn fl">查看物流</a>
	    			<%-- <a onclick="toWantRefund('${comOrder.orderId}');" class="gray-btn ra3 fr">我要退款</a> --%>
	    			<a onclick="toConfirmReceive('${comOrder.orderId}');" class="btn btnone fr">确认收货</a>
	    		</c:if>
	    		<c:if test="${comOrder.orderStatus == '04' }">
	    			<a onclick="toWl('${comOrder.orderId}');" class="btn fl">查看物流</a>
	    			<a onclick="toEvaluate('${comOrder.orderId}');" class="btn btnone fr">立即评价</a>
	    		</c:if>
	    		<c:if test="${comOrder.orderStatus == '05' || comOrder.orderStatus == '06' }">
	    			<a onclick="toWl('${comOrder.orderId}');" class="btn fl">查看物流</a>
	    			<a onclick="toBuyMore('${comOrder.orderId}');" class="btn btnone fr">再次购买</a>
	    		</c:if>
				
				
			</div>
		</div>
		<!--footerone end-->
	    
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
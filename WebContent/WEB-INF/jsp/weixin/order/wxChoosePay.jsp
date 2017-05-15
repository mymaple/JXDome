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
	<link rel="stylesheet" href="weui/dist/style/weui.min.css?${resultInfo.version}"/>
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
	
	function pay(){
		
		var orderIds = $("#orderIds").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>weixin/order/pay.do?tm='+new Date().getTime(),
	    	data: {"orderIds":orderIds},
			dataType:'json',
			//beforeSend: validateData,
			cache: false,
			success: function(data){
				if(data.resultCode == "success"){
					window.location.href = data.resultContent;
				}else{
					layer.open({
				    	content: data.resultContent
				    	,skin: 'msg'
				    	,time: 2 //2秒后自动关闭
				 	});
				}
			}
		});
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
		<input type="hidden" id="orderIds" value="${orderIds }">
		
		<div class="warpthree clearfloat">
	      	<div class="recharge clearfloat">
	      <c:choose>
	        <c:when test="${not empty comOrderList}">
				<c:forEach items="${comOrderList}" var="comOrder" varStatus="vs">
	      		<div class="czhi clearfloat box-s">
	    			<div class="fl">订单号：</div>
	    			<div class="fr f-col">${comOrder.orderNum }</div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl">应付金额：</div>
	    			<div class="fr f-col">${comOrder.allActPrice }</div>
	    		</div>
	      		</c:forEach>
	      	</c:when>
	      </c:choose>
	    	</div>
	      
	      <div class="recharge clearfloat">
	      		<div class="czhi clearfloat box-s">
	    			<div class="fl">选择支付方式：</div>
	    		</div>
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl">积分支付</div>
	    			
	    			<div class="fr">剩余积分：${integralCount }</div>
	    		</div>
	    	</div>
	    </div>
		
		<!--settlement star-->
	    <div class="settlement clearfloat">
	    	<div class="zuo clearfloat fl box-s">
	    		合计支付：<span>${pay }</span>积分
	    	</div>
	    	<a onclick="pay();" class="fl db">
	    		确认支付
	    	</a>
	    </div>
	    <!--settlement end-->
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
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
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?231"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?213"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?12312"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?12312"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css?2312"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css?adasd"/>
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
		
		/* if(){
			
		} */
		
		$("#shopCarForm").submit();
	}
	
	
	function toGetAllPay(){
		var shopCarIdChecks = $("input[name='shopCarId']:checked");
		if(shopCarIdChecks.length==0){
			return;
		}
		var shopCarIdChecksStr = "";
		for(var i=0; i<shopCarIdChecks.length; i++){ 
			shopCarIdChecksStr += shopCarIdChecks[i].value + ",";
		}
		shopCarIdChecksStr = shopCarIdChecksStr.substring(0, shopCarIdChecksStr.length-1);
		$.post("<%=basePath%>weixin/shopCar/toGetAllPay",{shopCarIds:shopCarIdChecksStr},
			function(data){
				if(data.resultCode=='success'){
					$("#allPay").html(data.resultContent);
				}else{
					layer.open({
				    	content: data.resultContent
				    	,skin: 'msg'
				    	,time: 2 //2秒后自动关闭
				 	});
				}
		});
	}
	
	//全选
	$(function() {
		$("#shopCarIdAll").click(function() {
	       $('input[name="shopCarId"]').prop("checked",this.checked);
	       toGetAllPay();
	    });
	    var $shopCarIds = $("input[name='shopCarId']");
	    $shopCarIds.click(function(){
	       $("#shopCarIdAll").prop("checked",$shopCarIds.length == $("input[name='shopCarId']:checked").length ? true : false);
	       toGetAllPay();
	     });
	});

	//清空
	function toDeletes(){
		if(confirm("确定清除选中商品？")){
			var shopCarIdChecks = $("input[name='shopCarId']:checked");
			var shopCarIdChecksStr = "";
			for(var i=0; i<shopCarIdChecks.length; i++){ 
				shopCarIdChecksStr += shopCarIdChecks[i].value + ",";
			}
			shopCarIdChecksStr = shopCarIdChecksStr.substring(0, shopCarIdChecksStr.length-1);
			$.post("<%=basePath%>weixin/shopCar/toDelete",{shopCarIds:shopCarIdChecksStr},
					function(data){
						if(data.resultCode=='success'){
							window.location.reload();
						}
				});
		}
	}
	
	function toDelete(shopCarId){
		if(confirm("确定删除？")){
			$.post("<%=basePath%>weixin/shopCar/toDelete",{shopCarIds:shopCarId},
				function(data){
					if(data.resultCode=='success'){
						window.location.reload();
					}
			});
		}
	}
	
	function countL(shopCarId){
		var count = Number($('#count_'+shopCarId).val());
		if(count<=1){
			layer.open({
		    	content: '最少选购1件'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			changeCount(shopCarId,'1');
		}else{
			changeCount(shopCarId,count-1);
		}
	}
	
	function countM(shopCarId){
		var count = Number($('#count_'+shopCarId).val());
		var stockNum = Number($('#stockNum_'+shopCarId).val());
		if(count>=stockNum){
			layer.open({
		    	content: '超出库存'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
		}else{
			changeCount(shopCarId,count+1);
		}
	}
	
	function toChangeCount(shopCarId){
		var count = Number($('#count_'+shopCarId).val());
		var stockNum = Number($('#stockNum_'+shopCarId).val());
		if(count>=stockNum){
			layer.open({
		    	content: '超出库存'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			changeCount(shopCarId,stockNum);
		}else{
			changeCount(shopCarId,count);
		}
	}
	
	function changeCount(shopCarId,count){
		$.post("<%=basePath%>weixin/shopCar/toChangeCount",{shopCarId:shopCarId,count:count},
				function(data){
					if(data.resultCode=='success'){
						$('#count_'+shopCarId).val(count);
						toGetAllPay();
					}else{
						layer.open({
					    	content: data.resultContent
					    	,skin: 'msg'
					    	,time: 2 //2秒后自动关闭
					 	});
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
	<form action="weixin/order/toConfirmOrder2.do" name="shopCarForm" id="shopCarForm" method="post">
				<c:choose>
            	<c:when test="${not empty comShopCarList}">
        <!--header star-->
		<header class="mui-bar mui-bar-nav" >
  			<div class="radio" > 
			    <label>
			        <input type="checkbox" id="shopCarIdAll" />
			        <div class="option" style="vertical-align:middle; margin-top:-.1rem;"></div>&nbsp;
			    </label>
			</div>
	        <div class="top-sch-box top-sch-boxtwo flex-col">
	                      全选
	        </div>
	        <a class="btn" onclick="toDeletes();">
	            <i class="iconfont icon-lajixiang"></i>
	        </a>
	    </header>
	    <!--header end-->
	    
	    <div class="warp warptwo clearfloat">
	    	<div class="shopcar clearfloat">
	    		<c:set var="supplierId" value=""/>
					<c:forEach items="${comShopCarList}" var="comShopCar" varStatus="vs">
	    		<c:if test="${comShopCar.comProduct.supplierId != supplierId && not empty supplierId}">
	    		</div>
	    		</c:if>
	    		<c:if test="${comShopCar.comProduct.supplierId != supplierId || empty supplierId}">
	    			<p class="gys-tit box-s">供应商：<param:display type="com_supplierEffective" value="${comShopCar.comProduct.supplierId}"/></p>
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
		    				<p class="fu-tit over">${comShopCar.comProduct.comProductStyle.productStyleName }(剩余${comShopCar.comProduct.comProductStyle.stockNum }件)</p>
		    				<p class="jifen over">积分：${comShopCar.comProduct.comProductStyle.currentPrice }积分</p>
		    				<div class="bottom clearfloat">
		    					<div class="zuo clearfloat fl">
		    						<ul>
		    							<li onclick="countL('${comShopCar.shopCarId }');"><img src="weui/gemo/img/jian.png"/></li>
		    							<li class="num-w">
		    							<input class="num-txt" type="number" id="count_${comShopCar.shopCarId }" value="${comShopCar.count }" onkeyup="toChangeCount('${comShopCar.shopCarId }');"> 
		    							<input type="hidden" id="stockNum_${comShopCar.shopCarId }" value="${comShopCar.comProduct.comProductStyle.stockNum }">
		    							</li>
		    							<li onclick="countM('${comShopCar.shopCarId }');"><img src="weui/gemo/img/jia.png"/></li>
		    						</ul>
		    					</div>
		    					<i class="iconfont icon-lajixiang fr" onclick="toDelete('${comShopCar.shopCarId }');"></i>
		    				</div>
		    			</div>
	    		</c:forEach>
	    		</div>
	    	</div>
	    </div>
	    <!--settlement star-->
	    <div class="settlement clearfloat">
	    	<div class="zuo clearfloat fl box-s">
	    		合计：<span id="allPay">0</span>
	    	</div>
	    	<a onclick="toConfirmOrder();" class="fl db">
	    		立即结算
	    	</a>
	    </div>
	    <!--settlement end-->
	    		</c:when>
	    		<c:otherwise>
	    <div class="empty-list clearfloat" id="main">
			    <i class="iconfont icon-gouwuche"></i>
			    <p>您的购物车空空如也！</p>
                <p class="p-ft">快去挑点宝贝吧</p>
		   </div>		
	    		
	    		</c:otherwise>
	    		</c:choose>
	    
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
	<script src="weui/gemo/js/others.js?sdasd"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css?asdas">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>

</html>
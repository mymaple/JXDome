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
	
	function toShopCar(){
		window.location.href = "<%=basePath%>weixin/shopCar/list.do";
	}
	
	function toChoose(flag){
		$('#flag').val(flag);
	}
	
	function toChoosePS(productStyleId){
		var count = Number($('#count').val());
		var stockNum = $('#stockNum_'+productStyleId).val();
		if(stockNum <=0 ){
			layer.open({
		    	content: '商品已售罄'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			return false;
		}
		if(count>stockNum){
			$('#count').val(stockNum);
		}
		$('.ps1').hide();
		$('#ps1_'+productStyleId).show();
		$('.ps2').removeClass('cur');
		$('#ps2_'+productStyleId).addClass('cur');
		$('#productStyleId').val(productStyleId);
	}
	
	function countL(){
		var count = Number($('#count').val());
		if(count<=1){
			layer.open({
		    	content: '最少选购1件'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			
			$('#count').val('1');
		}else{
			$('#count').val(count-1);
		}
	}
	
	function countM(){
		var count = Number($('#count').val());
		var stockNum = $('#stockNum_'+$('#productStyleId').val()).val();
		if(count>=stockNum){
			layer.open({
		    	content: '超出库存'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
		}else{
			$('#count').val(count+1);
		}
	}
	
	function toConfirm(){
		var productStyleId = $('#productStyleId').val();
		var count = Number($('#count').val());
		var stockNum = $('#stockNum_'+productStyleId).val();
		var flag = $('#flag').val();
		
		if(stockNum <=0 ){
			layer.open({
		    	content: '商品已售罄'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			return false;
		}
		
		if(count<1){
			layer.open({
		    	content: '最少选购1件'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			
			$('#count').val('1');
			return false;
		}
		
		if(count>=stockNum){
			layer.open({
		    	content: '超出库存'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
			$('#count').val(stockNum);
			return false;
		}
		
		if(flag == '1'){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>weixin/shopCar/add.do?tm='+new Date().getTime(),
		    	data: {"productStyleId":productStyleId,"count":count},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
						layer.open({
					    	content: data.resultContent
					    	,skin: 'msg'
					    	,time: 2 //2秒后自动关闭
					 	});
						$(".am-share").removeClass("am-modal-active");	
						setTimeout(function(){
							$(".sharebg-active").removeClass("sharebg-active");	
							$(".sharebg").remove();	
						},300);
				}
			});
		}else if(flag =='2'){
			window.location.href = "<%=basePath%>weixin/order/toConfirmOrder1.do?productStyleId="+productStyleId+"&count="+count;
		}
		
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
	<input id="flag" type="hidden">
		<div class="warptwo clearfloat">
			<div class="detail clearfloat">
				<!--banner star-->
				<div class="banner swiper-container">
		            <div class="swiper-wrapper">
		            	<c:forEach items="${ fn:split(comProduct.imgSrc2,',') }" var="imgSrc2">
		                <div class="swiper-slide"><img class="swiper-lazy" data-src="${imgSrc2 }" alt=""></div>
		            	</c:forEach>
		            </div>
		            <div class="swiper-pagination"></div>
		        </div>
				<!--banner end-->
				<div class="top clearfloat box-s">
					<div class="shang clearfloat">
						<div class="zuo clearfloat fl over2 box-s">
							${comProduct.productName }
						</div>
					</div>
				</div>
				<div class="middle clearfloat box-s">
					<a href="#">
						<span class="fl">商品详情</span>
						<i class="iconfont icon-jiantou1 fr"></i>
					</a>
				</div>
				<div class="chanpxq">
                	<div class="tu">
                		<c:forEach items="${ fn:split(comProduct.imgSrc3,',') }" var="imgSrc3">
                		<img src="${imgSrc3 }">
		            	</c:forEach>
                	</div>
                </div>
				
				<!-- <div class="middle clearfloat box-s">
					<a href="#">
						<span class="fl">商品评价</span>
						<i class="iconfont icon-jiantou1 fr"></i>
					</a>
				</div> -->
			</div>
		</div>
		
		<!--footerone star-->
		<div class="footerone clearfloat">
			<div class="left clearfloat fl">
				<ul>
					<li>
						<a onclick="toShopCar();" class="gwc">
							<i class="iconfont icon-gouwuche"></i>
							<c:if test="${shopCarCount != 0 }"><span>${shopCarCount}</span></c:if>
						</a>
					</li>
					<!-- <li>
						<a href="#">
							<i class="iconfont icon-shangcheng"></i>
							<p>商城</p>
						</a>
					</li> -->				
				</ul>
			</div>
			<div class="right clearfloat fr">
				<span class="btn fl" onClick="toshare();toChoose('1');">加入购物车</span>
				<a onClick="toshare();toChoose('2');" class="btn btnone fr">立即购买</a>
			</div>
		</div>
		<!--footerone end-->
		
		<!--这里是弹出购物车内容-->
		<div class="am-share">
		<div class="am-share-footer"><button class="share_btn"><img src="weui/gemo/img/chahao.png"/></button></div>
		  <div class="am-share-sns box-s">
		     <div class="sdetail clearfloat">
		     	<div class="top clearfloat">
		     		<div class="tu clearfloat fl">
		     			<span></span>
		     			<img src="${comProduct.headImgSrc }"/>
		     		</div>
		     		<c:choose>
     					<c:when test="${ not empty comProductStyleList }">
     						<c:forEach items="${comProductStyleList}" var="comProductStyle" varStatus="vs">
		     							
		     		<div class="you clearfloat fl ps1" id="ps1_${comProductStyle.productStyleId }" <c:if test="${ vs.index > 0}"> style="display: none;" </c:if> >
		     			<p class="tit">${comProduct.productName }</p>
		     			<span>库存：${comProductStyle.stockNum }
		     			<input type="hidden" id="stockNum_${comProductStyle.productStyleId }" value="${comProductStyle.stockNum }">
		     			</span>
                        <span>已选择:${comProductStyle.productStyleName }</span>
		     		</div>
		     				</c:forEach>
		     			</c:when>
		     		</c:choose>
		     	</div>
		     	
		     	<div class="middle clearfloat">
		     		<p>规格</p>
		     		<div class="xia clearfloat">
		     			<ul>
		     				<c:choose>
		     					<c:when test="${ not empty comProductStyleList }">
		     						<c:forEach items="${comProductStyleList}" var="comProductStyle" varStatus="vs">
		     							<c:if test="${ vs.index == 0}">
		     							<input id="productStyleId" name="productStyleId" type="hidden" value="${comProductStyle.productStyleId }">
		     							<li id="ps2_${comProductStyle.productStyleId }" class="ra3 cur ps2" onclick="toChoosePS('${comProductStyle.productStyleId }');">${comProductStyle.productStyleName }</li>
		     							</c:if>
		     							<c:if test="${ vs.index > 0}">
		     							<li id="ps2_${comProductStyle.productStyleId }" class="ra3 ps2" onclick="toChoosePS('${comProductStyle.productStyleId }');">${comProductStyle.productStyleName }</li>
		     							</c:if>
		     						</c:forEach>
		     					</c:when>
		     				</c:choose>
			     		</ul>
		     		</div>		     		
		     	</div>
		     	
		     	<div class="bottom clearfloat">
		     		<p class="fl">购买数量</p>
		     		<div class="you clearfloat fr">
		     			<ul>
		     				<li onclick="countL();"><img src="weui/gemo/img/jian.jpg"/></li>
		     				<li class="num-w"><input id="count" name="count" type="text" class="num-txt" value="1" onkeyup="toChangeCount();"></li>
		     				<li onclick="countM();"><img src="weui/gemo/img/jia.jpg"/></li>
		     			</ul>
		     		</div>
		     	</div>
		     </div>
		  </div>
		  <a onClick="toConfirm();" class="shop-btn db">确定</a>
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
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<script type="text/javascript" src="weui/gemo/js/shopcar.js" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>
</html>
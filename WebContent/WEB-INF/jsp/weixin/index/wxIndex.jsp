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
	
	function toAddInShopCar(productStyleId){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>weixin/shopCar/add.do?tm='+new Date().getTime(),
	    	data: {"productStyleId":productStyleId,"count":1},
			dataType:'json',
			//beforeSend: validateData,
			cache: false,
			success: function(data){
					layer.open({
				    	content: data.resultContent
				    	,skin: 'msg'
				    	,time: 2 //2秒后自动关闭
				 	});
			}
		});
	}
	
	function toProductDetail(productId){
		window.location.href = "<%=basePath%>weixin/product/toProductDetail.do?productId="+productId;
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
<!--header star-->
		<header class="mui-bar mui-bar-nav" id="header">
			<a class="btn slide-menu" href="#">
	            <i class="iconfont icon-iconfontcaidan"></i>
	        </a>
	        <div class="top-sch-box flex-col">
	            <div class="centerflex">
	                <i class="fdj iconfont icon-sousuo"></i>
	                <input type="text" name="" id="" value="" class="sch-txt" placeholder="输入您要搜索的商品" />
	            </div>
	        </div>
	        <a class="btn" href="#">
	            <i class="iconfont icon-erweima"></i>
	        </a>
	    </header>
	    <!--header end-->
	    
	    <!-- 侧边导航 -->
		<div class="slide-mask"></div>
		<aside class="slide-wrapper">
		  <div>
			<ul>
              <li><a href="list.html">购物卡</a></li>
			  <li><a href="list.html">旅游</a></li>
			  <li><a href="list.html">3C数码</a></li>
			  <li><a href="list.html">家居家纺</a></li>
			  <li><a href="list.html">手机充值</a></li>
			  <li><a href="list.html">汽配零件</a></li>
			  <li><a href="list.html">酒水茶饮</a></li>
			</ul>
		  </div>
		</aside>
	    
		<div id="main" class="clearfloat warp">			
		    <div class="mui-content">
				<!--banner开始-->
				<div class="banner swiper-container">
				<c:choose>
            	<c:when test="${not empty comLbtList}">
		            <div class="swiper-wrapper">
					<c:forEach items="${comLbtList}" var="comLbt" varStatus="vs">
		                <div class="swiper-slide"><a href="javascript:void(0)"><img class="swiper-lazy" data-src="${comLbt.lbtImgSrc }" alt=""></a></div>
		            </c:forEach>
		            </div>
		        </c:when>
		        </c:choose>
		        </div>
		        <!--banner结束-->
		        
				<c:choose>
            	<c:when test="${not empty comProductList}">
		        <div class="boutit clearfloat">
		        		<span></span>
		        		<samp>兑换专区（HOT）</samp>
		        	</div>
		        <!--like star-->
		        <div class="like clearfloat box-s">
		        	<div class="content clearfloat">
            	<c:forEach items="${comProductList}" var="comProduct" varStatus="vs">
		        		<div class="list clearfloat fl">
			        			<div class="tu clearfloat" onclick="toProductDetail('${comProduct.productId }');">
			        				<img src="${comProduct.headImgSrc }"/>
			        			</div>
			        			<div class="bottom clearfloat box-s">
			        				<p class="over2">${comProduct.productName }</p>
			        				<p class="integral">兑换积分：<span>${comProduct.comProductStyle.currentPrice }</span>
			        				<i class="iconfont icon-gouwuche fr gwc" onclick="toAddInShopCar('${comProduct.comProductStyle.productStyleId }');"></i></p>
			        			</div>
		        		</div>
		        </c:forEach>
		        	</div>
	          </div>
		        <!--like end-->
		        </c:when>
		        </c:choose>		
	        </div>
	        </div>
		<!--footer star-->
		<footer class="page-footer fixed-footer" id="footer">
			<ul>
				<li class="active">
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
		<script src="weui/gemo/js/others.js"></script>
		<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
		<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
		<!--插件-->
		<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
		<script src="weui/gemo/js/swiper.jquery.min.js"></script>
		<!--新闻资讯滚动-->
		<script type="text/javascript">
			$('.autoplay').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  autoplay: true,
			  autoplaySpeed: 2000,
			});
		</script>
		
	</body>

</html>
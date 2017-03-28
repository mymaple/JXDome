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
	function toAddShopCar(){
		
	}
	function toConfirmOrder(){
		
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
		
		<div class="warptwo clearfloat">
			<div class="detail clearfloat">
				<!--banner star-->
				<div class="banner swiper-container">
		            <div class="swiper-wrapper">
		                <div class="swiper-slide"><a href="javascript:void(0)"><img class="swiper-lazy" data-src="upload/21.jpg" alt=""></a></div>
		                <div class="swiper-slide"><a href="javascript:void(0)"><img class="swiper-lazy" data-src="upload/21.jpg" alt=""></a></div>
		                <div class="swiper-slide"><a href="javascript:void(0)"><img class="swiper-lazy" data-src="upload/21.jpg" alt=""></a></div>
		                <div class="swiper-slide"><a href="javascript:void(0)"><img class="swiper-lazy" data-src="upload/21.jpg" alt=""></a></div>
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
				<div class="middle clearfloat box-s">
					<a href="#">
						<span class="fl">商品评价</span>
						<i class="iconfont icon-jiantou1 fr"></i>
					</a>
				</div>
			</div>
		</div>
		
		<!--footerone star-->
		<div class="footerone clearfloat">
			<div class="left clearfloat fl">
				<ul>
					<li>
						<a href="#" class="gwc">
							<i class="iconfont icon-gouwuche"></i>
							<span>2</span>
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
				<span class="btn fl" onClick="toshare();">加入购物车</span>
				<a onClick="toshare();" class="btn btnone fr">立即购买</a>
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
		     		<div class="you clearfloat fl">
		     			<span>需要积分</span>
		     			<span>库存件</span>
		     			<span>已选择</span>
		     		</div>
		     	</div>
		     	<div class="middle clearfloat">
		     		<p>规格</p>
		     		<div class="xia clearfloat">
		     			<ul>
			     			<li class="ra3 cur">金色</li>
			     			<li class="ra3">灰色</li>
			     		</ul>
		     		</div>		     		
		     	</div>
		     	
		     	<div class="bottom clearfloat">
		     		<p class="fl">购买数量</p>
		     		<div class="you clearfloat fr">
		     			<ul>
		     				<li onclick="countL();"><img src="weui/gemo/img/jian.jpg"/></li>
		     				<li><input type="number" id="count"></li>
		     				<li onclick="countM();"><img src="weui/gemo/img/jia.jpg"/></li>
		     			</ul>
		     		</div>
		     	</div>
		     </div>
		  </div>
		  <a onClick="toComfirm();" class="shop-btn db">确定</a>
		</div>
		
		<!--footer star-->
	    <footer class="page-footer fixed-footer" id="footer">
			<ul>
				<li>
					<a href="index.html">
						<i class="iconfont icon-shouye"></i>
						<p>首页</p>
					</a>
				</li>
				<li class="active">
					<a href="cation.html">
						<i class="iconfont icon-icon04"></i>
						<p>分类</p>
					</a>
				</li>
				<li>
					<a href="shopcar.html">
						<i class="iconfont icon-gouwuche"></i>
						<p>购物车</p>
					</a>
				</li>
				<li>
					<a href="login.html">
						<i class="iconfont icon-yonghuming"></i>
						<p>我的</p>
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
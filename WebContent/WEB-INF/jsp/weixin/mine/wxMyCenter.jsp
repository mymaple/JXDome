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
	<meta name="keywords" content="">
    <meta name="description" content="">
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
	
	function toMyPartner(){
		window.location.href = "<%=basePath%>weixin/mine/toMyPartner.do";
	}
	
	function toMyOrder(state){
		window.location.href = "<%=basePath%>weixin/order/list.do?state="+state;
	}
	
	function toMyInfo(){
		window.location.href = "<%=basePath%>weixin/mine/toMyInfo.do";
	}
	
	function toMyPhone(){
		window.location.href = "<%=basePath%>weixin/mine/toMyPhone.do";
	}
	
	function toReceiveAddress(){
		window.location.href = "<%=basePath%>weixin/receiveAddress/list.do";
	}
	
	function toIntegralNote(){
		window.location.href = "<%=basePath%>weixin/note/toIntegralNoteList.do";
	}
	
	function toSparepartDeal(){
		window.location.href = "<%=basePath%>weixin/sparepartDeal/list.do";
	}
	function toParseQRCoder(){
		window.location.href = "<%=basePath%>weixin/sparepartDeal/toParseQRCoder.do";
	}
	
	function toLogout(){
		window.location.href = "<%=basePath%>weixin/main/logout.do";
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

<div class="clearfloat">
	    	<div class="h-top clearfloat box-s">
	    		<div class="tu clearfloat fl">
	    			<img src="<%=Const.BG_WEBSITE %>/${comAppUser.headImgSrc}"/>
	    		</div>
	    		<div class="content clearfloat fl">
	    			<p class="hname">${comAppUser.appUserName}</p>
	    			<p class="htel">ID:${comAppUser.appUserCode}</p>
	    			<p class="hpthy"><c:forEach items="${ fn:split(comAppUser.roleId,',') }" var="roleId">	
					<param:display type="com_appUserRoleEffectiveP" value="${roleId}"/>  
					</c:forEach></p>
	    		</div>
	    		<a onclick="toMyPartner();" class="btn db clearfloat fr ra3">查看成员</a>
	    	</div>
	    	<div class="cash clearfloat">
	    		<div class="shang xia clearfloat">
	    			<ul>
					    <li>
	    					<p><span>可用积分</span></p>
	    				</li>
	    				<li>
	    					<p>${integralCount }</p>
	    				</li>
	    			</ul>
	    		</div>
	    	</div>
	    	
	    	
            <div class="myorder clearfloat">
                 <ul>
                     <li class="box-s">
                        <a onclick="toMyOrder('01')">
                        <p class="tu"><img src="weui/gemo/img/ord-1.png"></p>
                        <c:if test="${count1 != 0 }"><p class="num">${count1}</p></c:if>                        
                        <p>待付款</p>
                        </a>
                     </li>
                     <li class="box-s">
                        <a onclick="toMyOrder('02')">
                        <p class="tu"><img src="weui/gemo/img/ord-2.png"></p>
                        <c:if test="${count2 != 0 }"><p class="num">${count2}</p></c:if>    
                        <p>待发货</p>
                        </a>
                     </li>
                     <li class="box-s">
                        <a onclick="toMyOrder('03')">
                        <p class="tu"><img src="weui/gemo/img/ord-3.png"></p>
                        <c:if test="${count3 != 0 }"><p class="num">${count3}</p></c:if>    
                        <p>待收货</p>
                        </a>
                     </li>
                     <li class="box-s">
                         <a onclick="toMyOrder('04')">
                        <p class="tu"><img src="weui/gemo/img/ord-4.png"></p>
                        <p>交易成功</p>
                        </a>
                     </li>                                                               
                     <li class="box-s">
                         <a onclick="toMyOrder('05')">
                        <p class="tu"><img src="weui/gemo/img/ord-5.png"></p>
                        <p>退款/售后</p>
                        </a>
                     </li>                     
                 </ul>
            </div>    
	    	
	    	<div class="cashlist clearfloat">
	    		<ul>
	    			<li class="box-s">
	    				<a onclick="toMyInfo();">
	    					<i class="iconfont icon-yonghuming fl"></i><p class="fl">我的资料</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    			<li class="box-s">
	    				<a onclick="toReceiveAddress();">
	    					<i class="iconfont icon-dizhi fl"></i><p class="fl">收货地址</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
                    <li class="box-s">
	    				<a onclick="toIntegralNote();">
	    					<i class="iconfont icon-jifen fl"></i><p class="fl">积分记录</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    			<li class="box-s">
	    				<a onclick="toSparepartDeal();">
	    					<i class="iconfont icon-bianji fl"></i><p class="fl">审核记录</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    		</ul>
	    	</div>
	    	<!-- <div class="cashlist clearfloat">
	    		<ul>
	    			<li class="box-s">
	    				<a onclick="toParseQRCoder();">
	    					<i class="iconfont icon-iconfontxingming fl"></i><p class="fl">qrcode</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    			<li class="box-s">
	    				<a href="weui/pyqp/index.html">
	    					<i class="iconfont icon-iconfontxingming fl"></i><p class="fl">代理管理</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    		</ul>
	    	</div> -->
	  		<a onclick="toLogout();" class="center-btn db ra3">退出登录</a>
	    </div>
	    
	    <div class="warp"></div>
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
				<li class="active">
					<a href="<%=basePath%>weixin/mine/toMyCenter.do">
						<i class="iconfont icon-yonghuming"></i>
						<p>个人中心</p>
					</a>
				</li>
			</ul>
		</footer>
		<!--footer end-->
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
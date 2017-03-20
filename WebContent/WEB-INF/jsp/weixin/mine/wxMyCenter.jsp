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
	<meta name="keywords" content="">
    <meta name="description" content="">
	<script src="weui/gemo/js/rem.js"></script> 
    <script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?123"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?213"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?435"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?345"/>
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
	
	function toMyPartner(){
		window.location.href = "<%=basePath%>weixin/mine/toMyPartner.do";
	}
	
	function toOrder(){
		window.location.href = "<%=basePath%>weixin/order/list.do";
	}
	
	function toMyInfo(){
		window.location.href = "<%=basePath%>weixin/mine/toMyInfo.do";
	}
	
	function toMyPwd(){
		window.location.href = "<%=basePath%>weixin/mine/toMyPwd.do";
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
	    			<img src="${comAppUser.headImgSrc}"/>
	    		</div>
	    		<div class="content clearfloat fl">
	    			<p class="hname">${comAppUser.appUserName}</p>
	    			<p class="htel">ID:${comAppUser.appUserCode}</p>
	    			<p class="hpthy"><param:display type="com_appUserRole" value="${comAppUser.roleId}"/></p>
	    		</div>
	    		<a onclick="toMyPartner();" class="btn db clearfloat fr ra3">查看成员</a>
	    	</div>
	    	<div class="cash clearfloat">
	    		<div class="shang xia clearfloat">
	    			<ul>
	    				<li>
	    					<a href="#">
	    						<p>31000</p>
	    						<span>积分</span>
	    					</a>
	    				</li>
	    				<li>
	    					<a href="#">
	    						<p>56179</p>
	    						<span>待用积分</span>
	    					</a>
	    				</li>
	    			</ul>
	    		</div>
	    	</div>
	    	<div class="cashlist clearfloat">
	    		<ul>
	    			<li class="box-s">
	    				<a onclick="toMyInfo();">
	    					<i class="iconfont icon-yonghuming fl"></i><p class="fl">我的资料</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    			<!-- <li class="box-s">
	    				<a onclick="toChangePwd();">
	    					<i class="iconfont icon-mima fl"></i><p class="fl">修改密码</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li> -->
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
	    		</ul>
	    	</div>
	    	<div class="cashlist clearfloat">
	    		<ul>
	    			<li class="box-s">
	    				<a onclick="toSparepartDeal();">
	    					<i class="iconfont icon-bianji fl"></i><p class="fl">审核记录</p>
	    					<i class="iconfont icon-jiantou1 fr"></i>
	    				</a>
	    			</li>
	    			<li class="box-s">
	    				<a onclick="toChangePwd();">
	    					<i class="iconfont icon-iconfontxingming fl"></i><p class="fl">代理管理</p>
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
	    	</div>
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
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script src="weui/gemo/js/others.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script src="weui/gemo/slick/slick.js" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css">
	<script src="weui/gemo/js/swiper.jquery.min.js"></script>
</html>
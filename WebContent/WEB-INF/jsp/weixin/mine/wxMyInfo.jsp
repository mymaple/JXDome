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
    <script src="weui/gemo/js/rem.js?${resultInfo.version}"></script> 
    <script src="weui/gemo/js/jquery.min.js?${resultInfo.version}" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/slick/slick.css?${resultInfo.version}"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.picker.min.css?${resultInfo.version}" />
	<link href="weui/gemo/css/mui.picker.css?${resultInfo.version}" rel="stylesheet" />
	<script type="text/javascript" src="plugins/layer/js/layer.js?${resultInfo.version}"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
	
	$(function(){
		ignoreBack();
	});
	
	function ignoreBack(){
		if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}
	
	function toEdit(type){
		if(type=='1'){
			$("#display").hide();
			$("#edit").show();
		}else{
			$("#display").show();
			$("#edit").hide();
		}
	}
	
	function edit(){
		
		if($("input[name='sex']:checked").val()==""){
			$("#sex").tips({
				side:3,
	            msg:'性别',
	            bg:'#AE81FF',
	            time:2
	        });
		return false;
		}
		
		if($("#brithdayStr").val().replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#brithdayStr").tips({
				side:3,
	            msg:'生日',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#brithdayStr").focus();
		return false;
		}
		if($("#remarks").val().replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#remarks").tips({
				side:3,
	            msg:'备注信息',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#remarks").focus();
		return false;
		}
		
		layer.open({
		    type: 2
		    ,shadeClose: false
		    ,content: '正在处理，请稍候...'
		  });
		
		$("#changeMyInfoForm").submit();
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
	    <div class="warpthree clearfloat" id="display">
	      <div class="recharge clearfloat">
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">姓名：</div>
	    			<div class="fr fc2">${comAppUser.appUserName}</div>
	    		</div>
                
                 <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">角色：</div>
	    			<div class="fr fc2"><param:display type="com_appUserRoleEffectiveP" value="${comAppUser.roleId}"/></div>
	    		</div>
	    		
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl fc1">性别：</div>
	    			<div class="fr fc2"><param:display type="com_sex" value="${comAppUser.sex}"/></div>
	    		</div>
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">生日：</div>
	    			<div class="fr fc2">${comAppUser.brithdayStr}</div>
	    		</div>
                
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl fc1">备注信息:</div>
	    		</div>
	    		<textarea disabled="disabled" rows="4" cols="" placeholder="请填写备注信息，不少于5个字" class="textare box-s" onchange="this.value=this.value.substring(0, 100)" 
										onkeydown="this.value=this.value.substring(0, 100)" onkeyup="this.value=this.value.substring(0, 100)">${comAppUser.remarks}</textarea>
                                        
	    		<a onclick="toEdit('1');" class="address-add fl">编辑</a>
	    	</div>
	    </div>
	    
	    <form action="weixin/mine/changeMyInfo.do" id="changeMyInfoForm" method="post">
	    <div class="warpthree clearfloat" id="edit" style="display: none;">
	      <div class="recharge clearfloat">
                
                <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">姓名：</div>
	    			<div class="fr fc2">${comAppUser.appUserName}</div>
	    		</div>
                
                 <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">角色：</div>
	    			<div class="fr fc2"><param:display type="com_appUserRoleEffectiveP" value="${comAppUser.roleId}"/></div>
	    		</div>
                
                
                <!--------性别-------->  
              <div class="confirm clearfloat" style="margin-top:3%;">  
	    	  <div class="gmshu gmshutwo clearfloat box-s fl">
				  <div class="gcontent clearfloat">
						<p class="fl" style="font-size:.4rem;">性别：</p>
			     		<div class="you fr">
			     			<div class="xuan clearfloat">
			     				<div class="radiotwo" > 
								    <label>
								        <input type="radio" name="sex" value="01" <c:if test="${comAppUser.sex == '01'}">checked="checked"</c:if>/>
								        <div class="option"></div>
								        <span class="opt-text" style="color:#333;">男</span>
								    </label>
								</div>
			     			</div>
                            
		    				<div class="xuan clearfloat">
			     				<div class="radiotwo" > 
								    <label>
								        <input type="radio" name="sex" value="02" <c:if test="${comAppUser.sex == '02'}">checked="checked"</c:if>/>
								        <div class="option"></div>
								        <span class="opt-text" style="color:#333;">女</span>
								    </label>
								</div>
			     			</div>
			     		</div>
				</div>		     		
	     	  </div>
              
              
	    	</div><!---->
            <div class="clearfloat"></div>
            
                <div class="czhi clearfloat box-s">
	    			<div class="fl fc1">生日：</div>
	    			<div class="fr fc2"><input class="fr shuru" name="brithdayStr" id="brithdayStr" value="${comAppUser.brithdayStr}" type="date" title="生日"/></div>
	    		</div>
                
	    		<div class="czhi clearfloat box-s">
	    			<div class="fl fc1">备注信息:</div>
	    		</div>
	    		<textarea name="remarks" id="remarks" rows="4" cols="" placeholder="请填写备注信息，不少于5个字" class="textare box-s" onchange="this.value=this.value.substring(0, 100)" 
										onkeydown="this.value=this.value.substring(0, 100)" onkeyup="this.value=this.value.substring(0, 100)">${comAppUser.remarks}</textarea>
	    		
                
                <div class="bottom clearfloat">
	    			<a onclick="toEdit('0');" class="db fl btn ra3">取消</a>
	    			<a onclick="edit();" class="db fr btn ra3">确定</a>
	    		</div>
                
	    	</div>  	
	    </div>
	    </form>
	    
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
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/js/mui.min.js?${resultInfo.version}"></script>
	<script src="weui/gemo/js/mui.picker.min.js?${resultInfo.version}"></script>
	<script src="weui/gemo/js/others.js?${resultInfo.version}"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js?${resultInfo.version}" ></script>
	<script src="weui/gemo/slick/slick.js?${resultInfo.version}" type="text/javascript" ></script>
	<!--插件-->
	<link rel="stylesheet" href="weui/gemo/css/swiper.min.css?${resultInfo.version}">
	<script src="weui/gemo/js/swiper.jquery.min.js?${resultInfo.version}"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js?${resultInfo.version}"></script>
</html>

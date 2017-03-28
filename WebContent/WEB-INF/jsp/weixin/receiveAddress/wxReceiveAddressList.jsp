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
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage");
		$(".loading").fadeOut(300);
	});
	
	$(function(){
		$("input[name='receiveAddressStatus']").change(function(){  
	        var receiveAddressId = $("input[name='receiveAddressStatus']:checked").val();
	        if(receiveAddressId==""||receiveAddressId==null){
	        	layer.open({
			    	content: '参数缺失'
			    	,skin: 'msg'
			    	,time: 2 //2秒后自动关闭
			 	});
	        	window.location.reload(); 
	        }
	        var url =  "<%=basePath%>weixin/receiveAddress/toDefault.do";
	        $.post(url, {"receiveAddressId": receiveAddressId},
       		   function(data){
		        	if(data.resultCode == "success"){
					}else{
						layer.open({
					    	content: data.resultContent
					    	,skin: 'msg'
					    	,time: 2 //2秒后自动关闭
					 	});
					}
		        	window.location.reload(); 
       		});
		});
	});
	
	
	function toAdd(){
		window.location.href = "<%=basePath%>weixin/receiveAddress/toAdd.do?choose="+$('#choose').val();
	}
	
	function toEdit(receiveAddressId){
		if(receiveAddressId==""||receiveAddressId==null){
        	layer.open({
		    	content: '参数缺失'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
        	window.location.reload(); 
        }
		window.location.href = "<%=basePath%>weixin/receiveAddress/toEdit.do?receiveAddressId="+receiveAddressId+"&choose="+$('#choose').val();
	}
	
	function toDelete(receiveAddressId){
		if(receiveAddressId==""||receiveAddressId==null){
        	layer.open({
		    	content: '参数缺失'
		    	,skin: 'msg'
		    	,time: 2 //2秒后自动关闭
		 	});
        	window.location.reload(); 
        }
        var url =  "<%=basePath%>weixin/receiveAddress/toDelete.do";
        $.post(url, {"receiveAddressId": receiveAddressId},
   		   function(data){
	        	if(data.resultCode == "success"){
				}else{
					layer.open({
				    	content: data.resultContent
				    	,skin: 'msg'
				    	,time: 2 //2秒后自动关闭
				 	});
				}
	        	window.location.reload(); 
   		});
	}
	
	function toChoose(receiveAddressId){
		window.location.href = "<%=basePath%>weixin/order/toConfirmOrder3.do?receiveAddressId="+receiveAddressId;
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
		<a onclick="toAdd();" class="address-add fl">
     		添加新地址
     	</a>
     	
	    <div id="main" class="mui-clearfix contaniner">
	    <input type="hidden" id="choose" name="choose" value="${choose }">
	    <c:choose>
			<c:when test="${not empty comReceiveAddressList}">
				<c:forEach items="${comReceiveAddressList}" var="comReceiveAddress" varStatus="vs">
			
			<div class="addlist clearfloat">
				<c:if test="${choose=='choose' }">
	    		<div class="top clearfloat box-s" onclick="toChoose('${comReceiveAddress.receiveAddressId}');">
	    		</c:if>
	    		<c:if test="${choose!='choose' }">
	    		<div class="top clearfloat box-s">
	    		</c:if>
	    			<ul>
	    				<li>
	    					<span class="fl">${comReceiveAddress.receicerName }</span>
	    					<span class="fr">${comReceiveAddress.phone }</span>
	    				</li>
	    				<li>
	    					${comReceiveAddress.province }&nbsp;${comReceiveAddress.city }&nbsp;${comReceiveAddress.district }<br>
	    					${comReceiveAddress.street }&nbsp;${comReceiveAddress.detail }
	    				</li>
	    			</ul>
	    		</div>
	    		<div class="bottom clearfloat box-s">
	    			<section class="shopcar clearfloat">
						<div class="radio fl"> 
						    <label>
						    	<c:if test="${comReceiveAddress.receiveAddressStatus == '01' }">
						        <input type="radio" name="receiveAddressStatus" value="${comReceiveAddress.receiveAddressId}" checked="checked">
						        </c:if>
						    	<c:if test="${comReceiveAddress.receiveAddressStatus != '01' }">
						        <input type="radio" name="receiveAddressStatus" value="${comReceiveAddress.receiveAddressId}" >
						        </c:if>
						        
						        <div class="option"></div>
						        <span class="mradd smradd fl">设为默认</span>
						    </label>
						</div>
						
						<div class="right fr clearfloat">
							<a onclick="toDelete('${comReceiveAddress.receiveAddressId }');" class="fr">
								<i class="iconfont icon-lajixiang"></i>
								删除
							</a>
							<a onclick="toEdit('${comReceiveAddress.receiveAddressId }');" class="fr">
								<i class="iconfont icon-shouji"></i>
								编辑
							</a>							
						</div>
					</section>
	    		</div>
	    	</div>	
				
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	    	
	    </div>
     	
     	 <div class="warp"></div>
     	 
     	 <c:if test="${choose !='choose' }">
		<!--footer star-->
		<footer class="page-footer fixed-footer" id="footer">
			<ul>
				<li>
					<a href="index.html">
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
				<li>
					<a href="<%=basePath%>weixin/mine/toMyCenter.do">
						<i class="iconfont icon-yonghuming"></i>
						<p>个人中心</p>
					</a>
				</li>
			</ul>
		</footer>
		<!--footer end-->
     	</c:if>
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/fastclick.js"></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
</html>
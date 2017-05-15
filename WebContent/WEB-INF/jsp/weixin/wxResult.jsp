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
	
</head>
<body>

	<c:if test="${resultInfo.resultCode=='success' }">
		<div class="weui-msg">
		    <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
		    <div class="weui-msg__text-area">
		        <h2 class="weui-msg__title">操作成功</h2>
		        <p class="weui-msg__desc">${resultInfo.resultContent }</p>
		    </div>
		    <div class="weui-msg__opr-area">
		        <p class="weui-btn-area">
		            <a href="<%=basePath %>${resultInfo.resultUrl }" class="weui-btn weui-btn_primary">确定</a>
		            <!-- <a href="#" class="weui-btn weui-btn_default">取消</a> -->
		        </p>
		    </div>
		    <div class="weui-msg__extra-area">
	            <div class="weui-footer">
	                <!-- <p class="weui-footer__links">
	                    <a href="javascript:void(0);" class="weui-footer__link">底部链接文本</a>
	                </p> -->
	                <p class="weui-footer__text">Copyright © 2016-2017 mymaple</p>
	            </div>
	        </div>
		</div>
	</c:if>
	<c:if test="${resultInfo.resultCode!='success' }">
		<div class="weui-msg">
		    <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
		    <div class="weui-msg__text-area">
		        <h2 class="weui-msg__title">操作失败</h2>
		        <p class="weui-msg__desc">${resultInfo.resultContent }</p>
		    </div>
		    <div class="weui-msg__opr-area">
		        <p class="weui-btn-area">
		            <a href="<%=basePath %>${resultInfo.resultUrl }" class="weui-btn weui-btn_primary">确定</a>
		            <!-- <a href="#" class="weui-btn weui-btn_default">取消</a> -->
		        </p>
		    </div>
		    <div class="weui-msg__extra-area">
	            <div class="weui-footer">
	                <!-- <p class="weui-footer__links">
	                    <a href="javascript:void(0);" class="weui-footer__link">底部链接文本</a>
	                </p> -->
	                <p class="weui-footer__text">Copyright © 2016-2017 mymaple</p>
	            </div>
	        </div>
		</div>
	</c:if>
</body>
<script type="text/javascript">
	window.onload=function(){
      if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}
</script>
</html>
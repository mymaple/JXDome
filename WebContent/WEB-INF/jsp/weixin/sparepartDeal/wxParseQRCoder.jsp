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
	<script src="weui/gemo/js/jquery.min.js" type="text/javascript"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  
	<script type="text/javascript">
	$(function() {
		alert(location.href.split('#')[0]);
        var appId = $("#appId").val();//时间戳
        var timestamp = $("#timestamp").val();//时间戳
        var nonceStr = $("#nonceStr").val();//随机串
        var signature = $("#signature").val();//签名
        wx.config({
            debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId : appId, // 必填，公众号的唯一标识
            timestamp : timestamp, // 必填，生成签名的时间戳
            nonceStr : nonceStr, // 必填，生成签名的随机串
            signature : signature,// 必填，签名，见附录1
            jsApiList : [ 'scanQRCode' ]
        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    
	//点击扫描按钮，扫描二维码并返回结果
	  $("#scanQRCode").click(function() {
		  alert("sadsad");
            wx.scanQRCode({
                // 默认为0，扫描结果由微信处理，1则直接返回扫描结果
                needResult : 1,
                desc : 'scanQRCode desc',
                success : function(res) {
                    //扫码后获取结果参数赋值给Input
                    var url = res.resultStr;
                    //商品条形码，取","后面的
                    if(url.indexOf(",")>=0){
                        var tempArray = url.split(',');
                        var tempNum = tempArray[1];
                        $("#id_securityCode_input").val(tempNum);
                    }else{
                        $("#id_securityCode_input").val(url);
                    }
                }
            });
        });
	});
	</script>  
</head>
<body>
	<input type="hidden" id="appId" value="${ret.appId}"/>
	<input type="hidden" id="timestamp" value="${ret.timestamp}"/>
	<input type="hidden" id="nonceStr" value="${ret.nonceStr}"/>
	<input type="hidden" id="signature" value="${ret.signature}"/>
	<input id="id_securityCode_input">
	<input id="scanQRCode" type="button" value="扫码">
</body>
</html>
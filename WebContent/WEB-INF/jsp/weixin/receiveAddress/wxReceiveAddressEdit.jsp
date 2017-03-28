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
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/base.css?31"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/page.css?22"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loaders.min.css"/>
    <link rel="stylesheet" type="text/css" href="weui/gemo/css/loading.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/layer/style/layer.css"/>
	<script type="text/javascript" src="plugins/layer/js/layer.js"></script>
<script type="text/javascript">
	$(window).load(function(){
		$(".loading").addClass("loader-chanage")
		$(".loading").fadeOut(300)
	})
	
	$(function(){
		ignoreBack();
		
		var province = "${comReceiveAddress.province}";
		if(province==""||province==null) province="---- 所在省份 ----";
		var city = "${comReceiveAddress.city}";
		if(city==""||city==null) city="---- 所在城市----";
		var district = "${comReceiveAddress.district}";
		if(district==""||district==null) district="---- 所在城区 ----";
		$("#distpicker").distpicker({
			  province: province,
			  city: city,
			  district: district
			});
		
		
		if("01"==$("#receiveAddressStatus").val()){
			$("#receiveAddressStatus").next().addClass('toggle--off');
		}else{
			$("#receiveAddressStatus").next().addClass('toggle--on');
		}
		
	});
	
	
	function toDefault(elem){
		$(elem).toggleClass('toggle--on').toggleClass('toggle--off').addClass('toggle--moving');
		setTimeout(function(){
			$(toggle).removeClass('toggle--moving');
		}, 200)
		if($(elem).attr("class").indexOf('toggle--off')){
			$("#receiveAddressStatus").val("01");
		}else{
			$("#receiveAddressStatus").val("00");
		}
	}
	
	
	function ignoreBack(){
		if(window.history.replaceState){
			window.history.replaceState(null,'',document.referrer);
		}
	}
	
	//保存
	function save(){
		var phoneExg = /^0?(13|15|17|18|14)[0-9]{9}$/; //手机
		if($("#receicerName").val()==""){
			$("#receicerName").tips({
				side:3,
	            msg:'请输入收货人',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#receicerName").focus();
		return false;
		}
		if(!phoneExg.test($("#phone").val())){
			$("#phone").tips({
				side:3,
	            msg:'请输入手机号码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#phone").focus();
		return false;
		}
		if($("#province").val()==""){
			$("#province").tips({
				side:3,
	            msg:'请选择省份',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#province").focus();
		return false;
		}
		if($("#city").val()==""){
			$("#city").tips({
				side:3,
	            msg:'请选择城市',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#city").focus();
		return false;
		}
		if($("#district").val()==""){
			$("#district").tips({
				side:3,
	            msg:'请输入城区',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#district").focus();
		return false;
		}
		if($("#street").val()==""){
			$("#street").tips({
				side:3,
	            msg:'请输入街道',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#street").focus();
		return false;
		}
		if($("#detail").val()==""){
			$("#detail").tips({
				side:3,
	            msg:'请输入详细地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#detail").focus();
		return false;
		}
		if($("#receiveAddressStatus").val()==""){
			$("#receiveAddressStatus").next().tips({
				side:3,
	            msg:'请选择 默认状态',
	            bg:'#AE81FF',
	            time:2
	        });
		return false;
		}
		$("#receiveAddressForm").submit();
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
	<form action="weixin/receiveAddress/${methodPath }.do" name="receiveAddressForm" id="receiveAddressForm" method="post">
		<input type="hidden" name="receiveAddressId" id="receiveAddressId" value="${comReceiveAddress.receiveAddressId}"/>
		<input type="hidden" name="choose" id="choose" value="${choose}"/>
		
		<div id="main" class="mui-clearfix add-address">
	    	<div class="plist clearfloat data">
				<ul>
					<li class="clearfloat">
						<p class="fl">收货人</p>
						<input type="text" class="fr shuru" name="receicerName" id="receicerName" value="${comReceiveAddress.receicerName}" maxlength="100" placeholder="这里输入 收货人" title="收货人" />
					</li>
					<li class="clearfloat">
						<p class="fl">联系电话</p>
						<input type="tel" class="fr shuru" name="phone" id="phone" value="${comReceiveAddress.phone}" maxlength="11" placeholder="这里输入 联系电话" title="联系电话" />
					</li>          
				</ul>
			</div>
            
            
            <div class="plist clearfloat data" data-toggle="distpicker" id="distpicker">
				<ul>
					<li class="clearfloat">
						<p class="fl">省份</p><i class="fr iconfont icon-jiantou1"></i>
                       	<div class="fr adr"><select id="province" name="province"></select></div>
					</li>
                    
                    <li class="clearfloat">
						<p class="fl">城市</p><i class="fr iconfont icon-jiantou1"></i>
                        <div class="fr adr"><select id="city" name="city"></select></div>
					</li>
                    
                    <li class="clearfloat">
						<p class="fl">城区</p><i class="fr iconfont icon-jiantou1"></i>
                        <div class="fr adr"><select id="district" name="district"></select></div>
					</li>
					<li class="clearfloat">
						<p class="fl">街道</p>
						<input type="text" class="fr shuru" name="street" id="street" value="${comReceiveAddress.street}" maxlength="100" placeholder="这里输入 街道" title="街道" />
					</li> 
				</ul>
			</div>
        
			<textarea name="detail" id="detail" rows="4" cols="" placeholder="请填写详细地址，不少于5个字" class="textare box-s" onchange="this.value=this.value.substring(0, 100)" 
			onkeydown="this.value=this.value.substring(0, 100)" onkeyup="this.value=this.value.substring(0, 100)">${comReceiveAddress.detail}</textarea>
	    	
	    	<div class="address-btn clearfloat">
	    		<span class="szwmr fl">设为默认</span>
	    		<input type="hidden" name="receiveAddressStatus" id="receiveAddressStatus" value="${comReceiveAddress.receiveAddressStatus}"/>
	    		<a onclick="toDefault(this);" class="toggle fr"></a>
	    	</div>
            
            <input onclick="save();" type="button" value="保存" class="ra3 address-add f5" >
            
     </div>  				
     </form>
     	
	</body>
	<script type="text/javascript" src="weui/gemo/js/jquery-1.8.3.min.js" ></script>
	<script src="weui/gemo/js/fastclick.js"></script>
	<script src="weui/gemo/js/mui.min.js"></script>
	<script type="text/javascript" src="weui/gemo/js/hmt.js" ></script>
	<script type="text/javascript" src="weui/gemo/js/jquery.min.js"></script>
	<script type="text/javascript" src="plugins/distpicker/distpicker.data.js"></script>
	<script type="text/javascript" src="plugins/distpicker/distpicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--默认按钮-->
</html>

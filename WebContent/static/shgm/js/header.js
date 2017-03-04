// 获取item参数值
function QueryString(item){ 
	var sValue = location.search.match(new RegExp("[\?\&]"+item+"=([^\&]*)(\&?)","i")); 
	return sValue ? sValue[1] : sValue;
} 

// 清除字符左右两边的空格
function ltrim(myStr){
	while((myStr.indexOf(" ")==0)&&(myStr.length>1)){
		myStr=myStr.substring(1,myStr.length);
	}
	while((myStr.lastIndexOf(" ")==myStr.length-1)&&(myStr.length>1)){
		myStr=myStr.substring(0,myStr.length-1);
	}	
	if(myStr==" "){
		myStr="";
	}
	return myStr;
}


// 人工输入的积分范围获取
function getPriceRegion(){
	var minValue = $.trim($("input[name='minPrice']").val());
	var maxValue = $.trim($("input[name='maxPrice']").val());
	var scoreValue = "积分范围";
	if(isNaN(minValue) || isNaN(maxValue)){
		alert("积分范围必须为数值");
		$("input[name='minPrice']").val('');
		$("input[name='maxPrice']").val('');
		return scoreValue;
	}
	if(minValue == ""){
		if(maxValue == ""){
			scoreValue = "积分范围";
		}
		else{
			scoreValue = "0～" + maxValue;
		}
	}
	else{
		if(maxValue == ""){
			scoreValue = minValue + "以上";
		}
		else if(maxValue - minValue < 0){
			alert("积分范围输入有误");
			$("input[name='minPrice']").val('');
			$("input[name='maxPrice']").val('');
		}
		else{
			scoreValue = minValue + "～" + maxValue;
		}
	}
	return scoreValue;
}

//var scoreRegion = null;
// search text
$(document).ready(
	function() { 
		var $keyword_class = $(".searchtext");
		// 聚焦关注点
		$keyword_class.focus(
			function(){
				var keyword = ltrim($(this).val());
				if(keyword == "输入品牌或商品进行搜索"){
					$(this).css("color","#333");
					$(this).val("");
				}
				this.select();
				//alert("searchtext focus!" + valuemm + " - " + this.defaultValue);
			}
		);
		// 移除关注点
		$keyword_class.blur(
			function(){
				var keyword = ltrim($(this).val());
				if(keyword == ""){
					$(this).css("color","#aaa");
					$(this).val("输入品牌或商品进行搜索");
				}
				//alert("searchtext blur!" + valuemm + " - " + this.defaultValue);
			}
		);	
		// 获取tid展示
		var tid = QueryString('tid');
		if (tid) {
			$('#nav li[class*=first]').removeClass('first');
			$('#nav li#'+tid).addClass('first');
		}
		//alert("tid:" + tid);
	}
);

// price range
$(document).ready(
	function() { 
		// 积分范围下拉框
		var $score_list = $(".sr1_ul");
		$score_list.hide();
		// 积分范围按钮
		var $score_class = $(".sr1_a");
		// 积分范围默认值按钮
		var $score_default_class = $(".sr1_li");
		// 积分范围最小值输入框
		var $score_min_class = $("input[name='minPrice']");
		// 积分范围最大值输入框
		var $score_max_class = $("input[name='maxPrice']");
		// 关键字类别
		var $keyword_class = $("input[name='keywordGBK']");
		// 下拉框是否被触发人工输入积分
		var isEdit = false;
		
		// 积分范围按钮
		$score_class.click(
			function(){
				//alert("score range select action click");
				isEdit = false;
				if($score_list.is(":hidden")){
					$score_list.show(100);
					return false;
				}
				else{
					$score_list.hide();
					return false;
				}
			}
		);
		
		// 系统默认提供的积分范围
		$score_default_class.click(
			function(){
				isEdit = false;
				var jffw = ltrim($(this).text());
				//scoreRegion=jffw;//获取积分
				$(".sr1_a").attr({ title: ''});
				$("#jffw").text(jffw);
				$score_list.hide();
				// 选择积分范围后跳转到搜索输入框
				//$keyword_class.val('');
				$keyword_class.focus();
				//alert("score range default select click, action 2 " + scored);
				
				return false;
			}
		);
		
		$score_min_class.click(
			function(){
				isEdit = true;
				//alert("score range active minPrice action click" + isHide);
			}
		);
		$score_min_class.keydown(
			function(event){
				// enter button
				if(event.keyCode == 13){
					$score_max_class.val('');
					$score_max_class.focus();
					isEdit = true;
				}
			}
		); 

		$score_max_class.click(
			function(){
				isEdit = true;
				//alert("score range active maxPrice action click" + isHide);
			}
		);
		
		// enter button
		$score_max_class.keydown(
			function(event){
				// enter button
				//alert(event.keyCode);
				if(event.keyCode == 13){
					var scoreValue = getPriceRegion();
					if(scoreValue != null){
						$("#jffw").text(scoreValue);
						//scoreRegion = scoreValue;//获取积分
						$score_list.hide();
						//$keyword_class.val('');
						$keyword_class.focus();
						isEdit = false;

						//alert("score range active maxPrice action keydown, action 2 " + scored);
					}
				}
			}
		); 

		$(this).click(
			function(){
				if(! $score_list.is(":hidden") && ! isEdit){
					var scoreValue = getPriceRegion();
					if(scoreValue != null){
						$("#jffw").text(scoreValue);
						$(".sr1_a").attr({ title: scoreValue});
						//scoreRegion = scoreValue;//获取积分
						//$keyword_class.val('');
						$keyword_class.focus();

						//alert("score range active priceRegion action blur, action " + scored);
					}
					$score_list.hide();
				}
				isEdit = false;
				return true;
			}
		);
		
		$.ajax({
			type:"POST",
			url:frontPath+"/exchangecenter/login/getLogin."+Utils.webType,
			dataType:"json",
			success:function(data){
				if(data!=null){
					//$("#userType").val()="",登录注册页面
					var curUrlPath=window.document.location.href;
					if(curUrlPath.indexOf("merchantcenter")!=-1){
						userType=1;//商户中心页面
					}else if(curUrlPath.indexOf("exchangecenter")!=-1){
						userType=0;//兑换中心页面
					}
					if(data.userType!=null){
						
						if(data.userType==0){
							$("#userName").html(" <a style='color:#F00' href='"+frontPath+"/customercenter/index."+Utils.webType+"'>"+data.userAccount+"</a>" + " <a href='"+frontPath+"/exchangecenter/exit."+Utils.webType+"'>退出</a>");
							var myCustomer = "<a href='"+frontPath+"/customercenter/index.jhtml' target='_top'><img src='"+frontPath+"img/header/top_03.jpg' /> 个人中心</a>&nbsp;|";
							$("#myCustomerCenter").html(myCustomer);
						}else if(data.userType==1){
							$("#cartId").attr("style","display:none");
							$("#cartLate").attr("style","display:none");
							$("#userName").html(" <a style='color:#F00' href='"+frontPath+"/merchantcenter/index."+Utils.webType+"'>"+data.userAccount+"</a>" + " <a href='"+frontPath+"/exchangecenter/exit."+Utils.webType+"'>退出</a>");
							var myCustomer = "<a href='"+frontPath+"/merchantcenter/index."+Utils.webType+"' target='_top'>商户中心</a>&nbsp;|";
							$("#myCustomerCenter").html(myCustomer);
						}
					}else{
						$("#userName").html('<a href="'+ frontPath +'/exchangecenter/login/showLogin.'+Utils.webType+'" target="_top">登录</a>&nbsp;|&nbsp;<a href="'+ frontPath +'/exchangecenter/register.'+Utils.webType+'" target="_top">注册</a>');
					}
				}else{
					$("#userName").html('<a href="'+ frontPath +'/exchangecenter/login/showLogin.'+Utils.webType+'" target="_top">登录</a>&nbsp;|&nbsp;<a href="'+ frontPath +'/exchangecenter/register.'+Utils.webType+'" target="_top">注册</a>');
				}
				
			}
		});

    }
);	
	//异步换栏目
	function changeColumnNo(columnNo){
		var data ={"columnNo":columnNo};
		$.ajax({
			type:"POST",
			url:frontPath+"/exchangecenter/index/changeColumnNo."+Utils.webType,
			data:data,
			dataType:"json",
			success:function(data){
			}
		});
	}






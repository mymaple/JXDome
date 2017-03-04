function updateCount(type){
	var count = document.getElementById("count");
	var num = parseInt(count.value);
	if(type==1){
		if(count.value>pageCache.pLimit||count.value>pageCache.pInvent){
			alert('抱歉，您输入的商品数量超过库存数量');
		}else{
			count.value = num + 1;
		}
	}else if(num > 1){
		count.value = num - 1;
	}
};

var errAccoutText = '抱歉，您输入的建行信用卡必须是16位数字';
var errMobileText = '抱歉，您输入的手机号码后4必须为数字';
var errCodeText = '抱歉，您输入的手机验证码有误';

var nullAccoutText = '请输入建行信用卡账号';
var nullMobileText = '请输入手机号码后4位';
var nullCodeText = '输入手机验证码';

var noEqAccoutText = '抱歉，请重新获取验证码';
var errIllegalInput = '抱歉，您只能输入数字';

var mobilceCode = '<div class="acc_right_yzm_time"><input class="acc_right_yzt" type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;秒后重新获取" /><span class="acc_time js_left_sec">60</span></div>';

var getCodeHTML = '<input type="button" class="acc_right_yzm_time" value="获取手机验证码" id="getMcode">';

var mobileCodeTip = '<span class="acc_right_lineheight2 js_Tip">我行已向该手机<span class="acc_tdcolor1 js_mobile">#f3#***#l4#</span> 发送短信验证码，请及时输入验证码，如未收到，请点重新获取</span>';

var errorTip = '<span class="acc_right_lineheight2 js_Tip" style="line-height:35px;color:red;">#tip#</span>';

var scoreResult = "<dd><span class=\"card-b0\">1</span><span class=\"card-b1\">世界旅行卡</span><span class=\"card-b2\">6224 7656 **** 5675</span><span class=\"card-b3\">4399</span></dd>";

//var scoreValue ='<div class="acc_right_div1 score_Tip">尊敬的用户，您当前可用积分为：<strong class="acc_tdcolor1">#scoreValue#</strong> 分</div>';

var valFlag = false,pre_accoumt = null,pre_mobilelastNum = null,pre_mcode = null;

var leftime = 60;

var defsec = 60;

var pageCache = {
		pInvent:500,
		pLimit:200,
		pid:$('#pid').val(),
		otype:$('#otype').val()
	};


function getMobileCode() {
	if($('#getMcode')[0].value!="免费获取"){
		return;
	}
	var accoumt = $('input[name="accoumt"]').val();
	var mobilelastNum = $('input[name="mobilelastNum"]').val();
	var mcode = $('input[name="mcode"]').val();
	var accessCode = $('input[name="accessCode"]').val();
   
	valFlag = validateData(accoumt,mobilelastNum,mcode);

	if(!valFlag){
		return ;
	}
	$('#getMcode')[0].value = "60秒后重新获取";
	$('#getMcode')[0].className="acc_right_yzm_time1";
	//请求通讯接口，查询手机号和身份证
	$.ajax({
		url: viewScoreVerifyCodeUrl,
		type: 'POST',
		dataType: 'json',
		data: {accoumt: accoumt,mobilelastNum:mobilelastNum,accessCode:accessCode,state:1}
	})
	.done(function(result) {
		if(result.code == 0){
			//手机号码后四位和接口返回的手机号码一致 
			rightMobileNum(result.message,mobilelastNum);
			valFlag = "SIOK";
			pre_accoumt = accoumt;
			pre_mobilelastNum = mobilelastNum;
		}else{
			setErrorTip(result.message||errAccoutText);
		}
		$('input[name="accessCode"]').val(result.data);
	})
	.fail(function(result) {
		setErrorTip(result.message||errAccoutText);
	});
	setTimeout(getBack,1000);
}




function getBack() {
	if($('#getMcode')[0].value=="1秒后重新获取"){
		$('#getMcode')[0].value = "免费获取";
		$('#getMcode')[0].className="acc_right_yzm_time";
	}else {
		var getMcValue = $('#getMcode')[0].value;
		getMcValue = getMcValue.substring(0, getMcValue.indexOf("秒"));
		var k = getMcValue-1;
		$('#getMcode')[0].value = k+"秒后重新获取";
		setTimeout(getBack,1000);
	}
}
function rightMobileNum(first,last) {
	$('#getMcode').remove();
	$('.js_mcode').after(mobilceCode+ mobileCodeTip.replace('#f3#',first||'').replace('#l4#',last||''));
	
	var txt = $('.js_left_sec');

	if(!txt.length){
		return;
	}

	var tid = setInterval(function() {
		if(leftime<=1){
			leftime = defsec;	
			$('.acc_right_yzm_time').remove();
			$('.js_mcode').after(getCodeHTML );
			return clearInterval(tid);
		}
		leftime --;
		$('.js_left_sec').text(leftime);
	},1000);
};

function validateData (accoumt,mobilelastNum,mcode) {
	clearErrorTip();
	//数据输入校验，建行卡号不能必须是16位
	if(!$.trim(accoumt).length){
		return setErrorTip(nullAccoutText);
	}
	else if(!/^\d{4}\s{1}\d{4}\s{1}\d{4}\s{1}\d{4}$/g.test(accoumt)){//16位建行卡号
		return setErrorTip(errAccoutText);
	}	
	else if(!$.trim(mobilelastNum).length){
		return setErrorTip(nullMobileText);
	}
	else if(!/^\d{4}$/g.test(mobilelastNum)){//4位手机号码
		return setErrorTip(errMobileText);
	}
	return true;
};

function clearErrorTip (argument) {
	$('.js_Tip').remove();
}

function setErrorTip(text) {
	if($('#getMcode').length){
		clearErrorTip();
		$('#getMcode').after(errorTip.replace('#tip#',text));
	}else{
		alert(text);
	}
}

var rightMobileNum = function (first,last) {
	$('#getMcode').remove();
	$('.js_mcode').after(mobilceCode+ mobileCodeTip.replace('#f3#',first||'').replace('#l4#',last||''));
	
	var txt = $('.js_left_sec');

	if(!txt.length){
		return;
	}

	var tid = setInterval(function() {
		if(leftime<=1){
			leftime = defsec;	
			$('.acc_right_yzm_time').remove();
			$('.js_mcode').after(getCodeHTML );
			return clearInterval(tid);
		}
		leftime --;
		$('.js_left_sec').text(leftime);
	},1000);
};

jQuery(document).ready(function($) {
	
	$('.cartdialog .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover.cartdialog').slideUp(200);
	});
	//拉起登录框
//	$('.xq_js_a1').click(function(){
//		$('.theme-popover-mask').css('opacity', 0.2);
//		$('.theme-popover-mask').fadeIn(100);
//		$('.theme-popover.logindialog').slideDown(200);
//	});
	
	$('.logindialog .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover.logindialog').slideUp(200);
	});
	
	$('.show-scroe-dialog').click(function(){
		/**$('.scoredialog .dl-content .score-result').hide();
		$('.scoredialog .dl-content .login-info').show();
		$('.theme-popover-mask').css('opacity', 0.2);
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover.scoredialog').slideDown(200);**/
		window.open(path+"/exchangecenter/account/viewscore."+Utils.webType);
	});
	
	$('.scoredialog .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover.scoredialog').slideUp(200);
	});
	
	$('.scoredialog .search-score').click(function(){
		//alert("bbb");
		//短信验证码
		var mcode = $('input[name="mcode"]').val();
		//随机访问串
		var accessCode = $('input[name="accessCode"]').val();
		
		var accoumt = $('input[name="accoumt"]').val();
		var mobilelastNum = $('input[name="mobilelastNum"]').val();
		$('.score_Tip').remove();
		//var mcode = $('input[name="mcode"]').val();
		//等短信接口调通后使用
		if(valFlag == "SIOK"){
			var valSubmitFlag = validateMcode(accoumt,mobilelastNum,mcode);
			
			if(!valSubmitFlag){
				return ;
			}
			//发送短信验证码成功
			//异步验证短信验证码是否正确
			$.ajax({
				url: submitQueryUrl,
				type: 'POST',
				dataType: 'json',
				data: {mcode: mcode,accessCode:accessCode,state:1,queryType:"2"}
			})
			.done(function(result) {
				$('input[name="accessCode"]').val(result.data);
				if(result.code == 1){
					$('#scoreValue')[0].innerHTML = result.message;
					$('#resultHead')[0].insertAdjacentHTML("afterEnd",result.data);
					$('.scoredialog .dl-content .login-info').hide();
					$('.scoredialog .dl-content .score-result').show();
				}else{
					setErrorTip(result.message||errAccoutText);
				}
			})
			.fail(function(result) {
				setErrorTip(result.message||errAccoutText);
			});
		}else{
			setErrorTip('请先获取短信验证码');
		}
		//临时使用
		//$('#queryScoreform').submit();
		
		//$('.scoredialog .dl-content .login-info').hide();
		//$('.scoredialog .dl-content .score-result').show();
	});
	
	
	//add cjw
	
	var ajaxSend = function(url,data,callback,type){
		$.ajax({
			url: url,
			type: type||"GET",
			dataType: 'json',
			data: data
		})
		.done(callback)
		.fail(function() {
//			console.log("error");
		})
		.always(function() {
//			console.log("complete");
		});

	};
	pageCache = {
		pInvent:500,
		pLimit:200,
		pid:$('#pid').val(),
		otype:$('#otype').val()
	};
	function updateCount(type){
		var count = document.getElementById("count");
		var num = parseInt(count.value);
		if(type==1){
			if(count.value>=pageCache.pLimit){
				alert('抱歉，每件商品一次最多只能兑换'+pageCache.pLimit+'件');
			}else if(count.value>=pageCache.pInvent){
				alert('抱歉，您输入的商品数量超过了库存数量');
			}else{
				count.value = num + 1;
			}
		}else if(num > 1){
			if(count.value <= 1){
				alert('兑换数量必须为正整数');
			}else{
				count.value = num - 1;
			}		
		}
	};
	$('.kc_1_up').click(function(){
		updateCount(1);
	});
	
	$('.kc_1_down').click(function(){
		updateCount(-1);
	});
	//查询库存
	$.ajax({
		   type: "POST",
		   url: realpath+"/exchangecenter/orderCheckInventory."+Utils.webType,
		   data: "productId="+$('input[name="pId"]').val()+"&inventoryId="+$('input[name="inId"]').val(),
		   success: function(res){
			if(res.invent===0){
				$('.xq_js_div').html('很抱歉，商品库存为0，暂不提供兑换'); //很抱歉，商品兑换已结束 liuhao 20150212 
				$('#pint').html(0);
	//			if($(".toggle_btn")){
	//				$(".toggle_btn").html("");	
	//			}
				$(".relative").attr("style","display:none");
			}else{
				//更新页面库存数量
				$('#pint').html((res.invent||50));
			}
			//缓存库存数
			pageCache.pInvent = res.invent||500;
			pageCache.pLimit = res.limit||500;	
		   }
	});
	
	//委托
	$('body').delegate('#insertVirtualOrder','click',function(){
			addItem2OrderExchange();
	});
	
	$('body').delegate('#insertProductOrder','click',function(){
		addItem2OrderExchange();
	});
	
	$('body').delegate('#insertActivityProductOrder','click',function(){
		$.ajax({
			type:"POST",
			url:realpath+"/exchangecenter/product/checkActivityTime."+Utils.webType,
			data:"productId="+$('input[name="pId"]').val(),
			success:function(res){
				if(0 == res.activityTimeFlag){
					addItem2OrderExchange();
				}else if(1 == res.activityTimeFlag){
					alert("抱歉，活动还没开始");
				}else if(2 == res.activityTimeFlag){
					alert("抱歉，活动已经结束");
				}
			},
			error:function(data){
				alert("系统繁忙");
			},
			beforeSend:function ()  
            {  
				$('#insertActivityProductOrder').attr('disabled',true);
            },  
            complete: function ()  
            {  
            	$('#insertActivityProductOrder').attr('disabled',false);  
            }
		});
	});
	
	$('body').delegate('#insertMovieOrder','click',function(){
	});
	
	
	var addItem2OrderExchange=function(productId,orderType){
		//判断登录，ajax
		//查询库存，缓存至页面，跟随页面跳转
		//跳转页面
		
		productId=productId||$('input[name="pId"]').val();
		orderType=orderType||$('input[name="otype"]').val();
		quantity=$('input[name="textarea"]').val();	
		shopId=$('#log_selectid option:selected').val();
		var inventoryCount = $('#pint').html();
		if("" == quantity){
			alert("请填写兑换数量"); return;
		}
		if(parseInt(quantity)>parseInt(inventoryCount)){
			alert("抱歉，您输入的商品数量超过了库存数量"); return;
		}
		if(parseInt(quantity)>pageCache.pLimit){
			alert("抱歉，每件商品一次最多只能兑换"+pageCache.pLimit+"件"); return;
		}
		
		if("1" == $("#o2oFlag").val()){
			if(typeof(shopId) == "undefined" || shopId==="0"){
				alert("请选择店铺!");
				return;
			}
		}

		$.ajax({
			type:"POST",
			url:realpath+"/exchangecenter/orderCheckLogin."+Utils.webType,
			dataType:"json",
			success:function(res){
				
				if(res&&res.userId){//登录
					//商户登录后，不可进行商品兑换  刘浩
					if(res.userType == '1'){
						alert("商户不可兑换商品！");
					}
					else
					{
						var params = {productId:productId,orderType:orderType,quantity:quantity,shopId:shopId};
					    var str = jQuery.param(params);
						location.href=realpath + "/exchangecenter/order/submitOrderdetail."+Utils.webType+"?"+str;
					}
					
				}//未登录
				else{
					//保存登录后将要跳转的链接
					var params = {productId:productId,orderType:orderType,quantity:quantity,shopId:shopId};
				    var str = jQuery.param(params);
				    afterLoginUrl = realpath + "/exchangecenter/order/submitOrderdetail."+Utils.webType+"?"+str;
					//弹出登录框
					$("#loginTips1").html("");
//					$("#loginForm")[0].reset();
					$('.theme-popover-mask').css('opacity', 0.2);
					$('.theme-popover-mask').fadeIn(100);
					$('#loginBox').slideDown(200);
				}
			}
		});
	    /*IE10以下不兼容
	     * ajaxSend('exchangecenter/orderCheckLogin?r='+ Math.random(),{},function(res){
			
			if(res&&res.userId){//登录
				
				var params = {productId:productId,orderType:orderType,quantity:quantity};
			    var str = jQuery.param(params);
//				console.log(str);
				location.href=realpath + "/exchangecenter/order/submitOrderdetail?"+str;
			}//未登录
			else{
				//保存登录后将要跳转的链接
				var params = {productId:productId,orderType:orderType,quantity:quantity};
			    var str = jQuery.param(params);
			    afterLoginUrl = realpath + "/exchangecenter/order/submitOrderdetail?"+str;
				//弹出登录框
				$("#loginTips1").html("");
				$("#loginForm")[0].reset();
				$('.theme-popover-mask').css('opacity', 0.2);
				$('.theme-popover-mask').fadeIn(100);
				$('#loginBox').slideDown(200);
			}
		});*/
		
	};
	
	$('body').delegate('#addCart','click',function(){
//		console.log('addCart');
		var pid = $('input[name="pId"]').val();
		var otype = $('input[name="otype"]').val();
		var count = $('#count').val();
		var pint = $('#pint').text();
		addCart(pid,count,'1',pint);
	});
	
	var buildOrderUrl = function(){
		$('input[name=""]').val();
		$('input[name=""]').val();
		$('input[name=""]').val();
		
		
	};
	
	
	var showLoginDiv=function(){
		$('.theme-popover-mask').css('opacity', 0.2);
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover.logindialog').slideDown(200);
	};
	
	$('.log_btn01').click(function(){
		$('.theme-popover-mask').css('opacity', 0.2);
		$('.theme-popover-mask').fadeIn(100);
		//$('.theme-popover.cartdialog').slideDown(200);
		});
	$('.cartdialog .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover.cartdialog').slideUp(200);
	});
	
	var CreditQuery = {};
	CreditQuery.discardInput = function() {
		console.log($(this).val());
		var tmp = $.trim($(this).val());
		if (!/^[\d\s]*$/g.test(tmp)) {
			$(this).val($(this).data("oldVal"));
			setErrorTip(errIllegalInput);
		} else {
			$(this).data("oldVal", tmp);
			$(this).val(tmp);
			clearErrorTip();
		}
	};
	
	CreditQuery.format = function() {
		var tmp = $(this).val();
		tmp = tmp.replace(/\s/g,  ""); 
		tmp = tmp.replace(/(\d{4})/g,  "$1 ");
		$(this).val($.trim(tmp.substring(0, 19)));
	};
	
	$('input[name="accoumt"]').on('input', CreditQuery.discardInput);
	$('input[name="accoumt"]').on('input', CreditQuery.format);
	$('input[name="mobilelastNum"]').on('input', CreditQuery.discardInput);
	$('input[name="mcode"]').on('input', CreditQuery.discardInput);
	
	
	if("1" == $("#o2oFlag").val())
		jQuery.post(realpath+"/exchangecenter/product/getDefaultCityInfo."+Utils.webType, 
				{ "searchDataObj.categoryId": $("#o2oCategoryId").val()},
				   function(data){
					if(data){
						var newhtml = "<select class='log_input_select' id='log_selectid'  onchange='showAddress(this)' >"+
						"<option value='0'>选择店铺</option>";
						var shopArray = eval("("+data+")");
						//||$("#pint").text()=='库存0件'
						$(".toggle_city p").text(shopArray[0].cityName);
						if(shopArray.length>0){
							for(var i =  0; i<shopArray.length;i++){
							newhtml = newhtml+"<option value='"+shopArray[i].shopId+"'"+
							" title='"+shopArray[i].shopAddress+"' id='shop_"+shopArray[i].shopId+"'>"
							+shopArray[i].shopName+"</option>";
								
							}
							newhtml = newhtml+"</select>";
							$("#movieSpan").html(newhtml);
							
							//var sumHtml = "<a id='insertProductOrder' href='javascript:void(0)' class='xq_js_a1'></a>";
							
							//$(".xq_js_div").html(sumHtml);
						}else{
							$("#movieSpan").html("很抱歉，暂无可选的店铺");
							//$(".xq_js_div").html("");
						};
					}else{
						$("#movieSpan").html("很抱歉，暂无可选的店铺");
						//$(".xq_js_div").html("");
					}				
		});	
});

function showAddress(obj){
	var addressTitle = obj.children[obj.selectedIndex].title;
	$("#shopAddress").html(addressTitle);
	$(".m-address").attr("style","display:block;margin:6px 0 6px 0;");
}

function checkAmount(){
	
	//var countInput = $("#t1_"+id);
	var invent = $.trim($('#pint').html());
	var count = $.trim($('#count').val());
		
	if("0" === count){
		var count = 1;
	}
	else if(parseInt(invent) < parseInt(count)){
		alert("该商品目前库存为"+invent+"件，请重新填写兑换数量");
		count = 1;
	}
	else if(pageCache.pLimit < parseInt(count)){
		alert("抱歉，每件商品一次最多只能兑换"+pageCache.pLimit+"件");
		count = 1;
	}
	
	$('#count').val(count);
}

//添加购物车
function addCart(productid,quantity,buyType,inventoryQuantity){
	
	if("" == quantity){
		alert("请填写兑换数量"); 
		return;
	}
	if(parseInt(quantity)>parseInt(inventoryQuantity)){
		alert("抱歉，您输入的商品数量超过了库存数量"); 
		return;
	}
	else if(parseInt(quantity) > pageCache.pLimit){
		alert("抱歉，每件商品一次最多只能兑换"+pageCache.pLimit+"件"); 
		return;
	}
	jQuery.ajax({
	   type: "POST",
	   url: path+"/exchangecenter/shoppingcart/addShopCart."+Utils.webType,
	   //url: path+"/exchangecenter/shoppingcart/mycartslist",
	   data: "shoppingCart.productId="+productid+"&shoppingCart.quantity="+quantity+"&shoppingCart.buyType=1",
	   success: function(msg){
		   var returnMsg = msg.split("|");
		   if(returnMsg[0]=="0"){
			   $("#cartnum").text(returnMsg[1]);
			   //$(".cartSize").text(returnMsg[1]);
			   	$('.theme-popover-mask').css('opacity', 0.2);
			   	$('.theme-popover-mask').fadeIn(100);
			   	$('.theme-popover.cartdialog').slideDown(200);
		   }else{
			   alert(msg);
		   }
	   },
	   error:function(data){
			alert("系统繁忙");
		},
		beforeSend:function ()  
       {  
			$('#addCart').attr('disabled',true);
       },  
       complete: function ()  
       {  
       		$('#addCart').attr('disabled',false);  
       }
	});
}


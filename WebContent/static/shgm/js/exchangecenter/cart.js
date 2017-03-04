var proNumLimit = 200;

jQuery(document).ready(function($) {
	proNumLimit = parseInt($("#proNumLimit").val());
	
	$('body').delegate('.pay-btn','click',function(){
		var that = $(this);
		var productId=that.attr('id');
		var quantity=$('input[name="q_'+productId+'"]').val();
		var orderType=1;
		$.ajax({
			type:"POST",
			url:realpath+"/exchangecenter/orderCheckLogin."+Utils.webType,
			dataType:"json",
			success:function(res){
				
				if(res&&res.userId){//登录				
						var params = {productId:productId,quantity:quantity,orderType:orderType};
					    var str = jQuery.param(params);
						location.href=path + "/exchangecenter/order/submitOrderdetail."+Utils.webType+"?"+str;
				}//未登录
				else{
							
					//保存登录后将要跳转的链接
					var params = {productId:productId,quantity:quantity,orderType:orderType};
				    var str = jQuery.param(params);
					afterLoginUrl = path + "/exchangecenter/order/submitOrderdetail."+Utils.webType+"?"+str;
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
				
				var params = {productId:productId,quantity:quantity};
			    var str = jQuery.param(params);
				location.href=path + "/exchangecenter/order/submitOrderdetail?"+str;
			}//未登录
			else{
				//保存登录后将要跳转的链接
				var params = {productId:productId,quantity:quantity};
			    var str = jQuery.param(params);
				afterLoginUrl = path + "/exchangecenter/order/submitOrderdetail?"+str;
				//弹出登录框
				$("#loginTips1").html("");
//				$("#loginForm")[0].reset();
				$('.theme-popover-mask').css('opacity', 0.2);
				$('.theme-popover-mask').fadeIn(100);
				$('#loginBox').slideDown(200);
			}
		});*/
	});
	
});


function updateCount(type){
	var count = document.getElementById("count");
	var num = parseInt(count.value);
	if(type==1){
		count.value = num + 1;
	}else if(num > 1){
		count.value = num - 1;
	}
}

var net={};
net.tab={};
if (typeof($) == 'undefined') {
  $ = function(elem) {
    if (arguments.length > 1) {
      for (var i = 0, elems = [], length = arguments.length; i < length; i++)
        elems.push($(arguments[i]));
      return elems;
    }
    if (typeof elem == 'string') {
      return document.getElementById(elem);
    } else {
    	return elem;
    }
  };
}
//按钮点击时事件
net.tab.objSelect=function(clickobj,obj,elems){
	for(var i=0;i<obj.length;i++)
	{
		obj[i].className="";
		elems[i].style.display="none";
	}
	clickobj.className="selected";
	elems[clickobj.getAttribute("name")].style.display="";
}
//初始话按钮状态
//参数elem:按钮标签id
//参数num:初始时选中标签
net.tab.menuEvent=function(elem,num){
	var objs = document.getElementById(elem);
	if(objs==null) return;
	var objs=objs.getElementsByTagName("li");
	var conobjs=$(elem+"_content").getElementsByTagName("div");
	objs[num].className="selected";
	conobjs[num].style.display="";
	net.tab.addEvent(objs,function (){
		net.tab.objSelect(this,objs,conobjs);
	});
}
//添加按钮事件
net.tab.addEvent=function(elems,fun){
	for (var i=0;i<elems.length;i++)
	{
		elems[i].setAttribute("name",i);
		elems[i].onclick=fun;
	}
}
window.onload=function(){
	net.tab.menuEvent("tab_nav",0)
};
var showLoginDiv=function(){
	$('.theme-popover-mask').css('opacity', 0.2);
	$('.theme-popover-mask').fadeIn(100);
	$('.theme-popover.logindialog').slideDown(200);
};

//删除购物车商品
function deleteCart(productid){
	jQuery.ajax({
	   type: "POST",
	   //url: path+"/exchangecenter/shoppingcart/mycartslist!addShopCart",
	   url: path+"/exchangecenter/shoppingcart/deleteShopCart."+Utils.webType,
	   data: "shoppingCart.productId="+productid,
	   success: function(msg){
		   var returnMsg = msg.split("|");
		   if(returnMsg[0]=="0"){
			   $("#cartnum").text(returnMsg[1]);
			   if(returnMsg[1]==0){
				   $(".cart_box").html('<div class="page-mid">'+
							   '<div class="cart_tips">'+
						        '<div class="cart_tips_info">'+
						         '<i class="cart_tips_ico"></i>'+
						         '<p>购物车内暂时没有任何商品，请选购后再来查看吧！</p>'+
						         '<p>现在就去 <a href="'+path+'/exchangecenter/index.' + Utils.webType + '">选购商品</a>!</p>'+
						       '</div>'+
						     '</div>'+
						     '</div');
			   }
			   //$(".cartSize").text(returnMsg[1]);
			   
			   $("#div_"+productid).attr("style","display: none;");
		   }else{
			   alert(msg);
		   }
	   }
	});
}
var ajaxSend = function(url,data,callback,type){
	$.ajax({
		url: url,
		type: type||"GET",
		dataType: 'json',
		data: data
	})
	.done(callback)
	.fail(function() {
//		console.log("error");
	})
	.always(function() {
	});
};

function checkProcutLimit(productId, quantity, type){
	jQuery.ajax({
		url:path+"/exchangecenter/shoppingcart/checkProductLimit."+Utils.webType,
		type:"POST",
		data:"productId="+productId + "&" + "quantity=" + quantity + "&" + "type=" + type,
		dataType:"json",
		success:function(data){
			if(!data.result){
				alert(data.msg);
			}else{
				var params = {productId:productId,quantity:quantity};
			    var str = jQuery.param(params);
				location.href=path + "/exchangecenter/order/submitOrderdetail." +Utils.webType + "?"+str;			
			}
		},
		error:function(data){
			alert(data.msg);
		}
	});
}


function calculatePayAmount(id, countStr, pointValue){
	
	//var countInput = $("#t1_"+id);
	var invent = parseInt($.trim($('#invent_'+id).val()));
	$("#countTip_"+id).html("");
	var count = parseInt($.trim(countStr));
	
	$("#sub_"+id).removeClass('sub_off');
	$("#sub_"+id).addClass('sub_on');
	
	if(0===count){
		var count = 1;
		$("#sub_"+id).removeClass('sub_on');
		$("#sub_"+id).addClass('sub_off');
	}
	else if(1===count){
		$("#sub_"+id).removeClass('sub_on');
		$("#sub_"+id).addClass('sub_off');
	}
	else if(invent < count){
		alert("该商品目前库存为"+invent+"件，请重新填写兑换数量");
		count = 1;
	}
	else if(proNumLimit < count){
		alert("抱歉，每件商品一次最多只能兑换"+proNumLimit+"件");
		count = 1;
	}
	
	if("" == countStr){
		//$("#t1_"+id).val(count);
		$("#cartSize_"+id).html("");
		$("#sumPointValue_"+id).html("");
		$("#totalPointValue_"+id).html("");
	}
	else{
		$("#t1_"+id).val(count);
		$("#cartSize_"+id).html(count);
		$("#sumPointValue_"+id).html((count * parseFloat(pointValue)).toFixed(2));
		$("#totalPointValue_"+id).html((count * parseFloat(pointValue)).toFixed(2));
	}
	
}

function setCountFormatValue(obj){
	if("" == obj.value){
		return;
	}
	var value = '';
	var number = '1234567890';
	for(var i=0;i<obj.value.length;i++){
		if(number.indexOf(obj.value.charAt(i))>=0){
			value = value+obj.value.charAt(i);
		}
	}
	obj.value = value;
}

function addPaymentCount(id,pointValue){  
	var invent = parseInt($.trim($('#invent_'+id).val()));
	var objInput = document.getElementById("t1_"+id);
	var cartSize = document.getElementById("cartSize_"+id);
	var v = parseInt(objInput.value);
	if(invent <= v){
		alert("该商品目前库存为"+invent+"件，请重新填写兑换数量");
		return;
	}
	else if(proNumLimit <= v){
		alert("抱歉，每件商品一次最多只能兑换"+proNumLimit+"件!");
		return;
	}
	//if(v<1000){
		objInput.value = v + 1;
		cartSize.innerHTML =  v + 1;
		var sumPointValue =  document.getElementById("sumPointValue_"+id);
		sumPointValue.innerHTML  =((v + 1)*parseFloat(pointValue)).toFixed(2);
		var totalPointValue =  document.getElementById("totalPointValue_"+id);
		totalPointValue.innerHTML = ((v + 1)*parseFloat(pointValue)).toFixed(2);
		
		$("#sub_"+id).removeClass('sub_off');
		$("#sub_"+id).addClass('sub_on');
		
	//}

	/*else{
		alert("数量只能小于1000");
	}*/
	
}

function subPaymentCount(id,pointValue){
	var objInput = document.getElementById("t1_"+id);
	var cartSize = document.getElementById("cartSize_"+id);
	var v = parseInt(objInput.value);
	if(v >= 2){
		objInput.value = v - 1;
		cartSize.innerHTML = v - 1;
		var sumPointValue =  document.getElementById("sumPointValue_"+id);
		sumPointValue.innerHTML = ((v -1)*parseFloat(pointValue)).toFixed(2);
		var totalPointValue =  document.getElementById("totalPointValue_"+id);
		totalPointValue.innerHTML = ((v - 1)*parseFloat(pointValue)).toFixed(2);
	}else{
		$("#sub_"+id).removeClass('sub_on');
		$("#sub_"+id).addClass('sub_off');
	}
}











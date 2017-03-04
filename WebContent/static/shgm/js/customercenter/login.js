var afterLoginUrl = '';//弹窗登录后的跳转链接



jQuery(document).ready(function($) {
	
	
	var $dpro1=$(".donatediv01");
	var $dpro2=$(".donatediv02");
	$dpro2.hide();	
	
	$('#fastlia').click(function(){
		$(this).css({background:"#afafaf",color:"#fff"});
		$('#fastlib').css({background:"#f3f3f3",color:"#999"});
		$dpro2.hide();
		$dpro1.show();	
		
	});	
	$('#fastlib').click(function(){
		$(this).css({background:"#afafaf",color:"#fff"});
		$('#fastlia').css({background:"#f3f3f3",color:"#999"});
		$dpro2.show();
		$dpro1.hide();	
		
	});	
	
	$('.close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	});
	
	//当且仅当没有来自Action的回显数据时才使用Cookie填充个人会员用户名
	if ($("#userAccount0").val().length==0) {
		//记住用户名
		var rebUserAccount = getCookie("ck_lg_reb_user_account");
		rebUserAccount = rebUserAccount.replace(/\"/g, "");
		
		var rebType = getCookie("ck_lg_reb_type");
		if (rebType == 0) {
			$("#userAccount0").val(rebUserAccount);
			$("#userAccount1").val("");
			$("#remember0").attr("checked", true);
		} else if (rebType == 1) {
			$("#userAccount1").val(rebUserAccount);
			$("#userAccount0").val("");
			$("#remember1").attr("checked", true);
		}
	}
	
	if ($("#userAccount1").length>0) {
		//当且仅当没有来自Action的回显数据时才使用Cookie填充商户会员用户名
		if ($("#userAccount1").val().length == 0) {
			//记住用户名
			var rebUserAccount = getCookie("ck_lg_reb_user_account");
			rebUserAccount = rebUserAccount.replace(/\"/g, "");
			var rebType = getCookie("ck_lg_reb_type");
			if (rebType == 0) {
				$("#userAccount0").val(rebUserAccount);
				$("#userAccount1").val("");
				$("#remember0").attr("checked", true);
			} else if (rebType == 1) {
				$("#userAccount1").val(rebUserAccount);
				$("#userAccount0").val("");
				$("#remember1").attr("checked", true);
			}
		}
	}
	//“会员登录”选项卡里面的图形验证码失去焦点时触发本身的客户端校验
	$("#randomCode0").blur(function(event){
		if (event.relatedTarget==null) {
			return ;
		}
		
		var randomCode = $("#randomCode0");
		var error_tips = $(".error-tips0");
		var registerTip = $("#registerTip0");
		var randomCodeCheck = $("#randomCodeCheck0");
		
		if(randomCode.val()=="" || randomCode.val().length==0){
			registerTip.html("图形验证码不能为空,请输入图形验证码");
			error_tips.css("width","280px");
			error_tips.show(500);
			randomCodeCheck.addClass("ico_wrong_login");
			randomCodeCheck.removeClass("ico_right_login");
			return;
		}

		if(randomCode.val().length<5){
			registerTip.html("图形验证码长度为5位字符，注意区分大小写");
			error_tips.css("width","280px");
			error_tips.show(500);
			randomCodeCheck.addClass("ico_wrong_login");
			randomCodeCheck.removeClass("ico_right_login");
			return;
		}
	}
	);
	
	
	//“商户登录”选项卡里面的图形验证码失去焦点时触发本身的客户端校验
	$("#randomCode1").blur(function(event){
		
		var randomCode = $("#randomCode1");
		var error_tips = $(".error-tips1");
		var registerTip = $("#registerTip1");
		var randomCodeCheck = $("#randomCodeCheck1");
		
		if(randomCode.val()=="" || randomCode.val().length==0){
			registerTip.html("图形验证码不能为空,请输入图形验证码");
			error_tips.css("width","280px");
			error_tips.show(500);
			randomCodeCheck.addClass("ico_wrong_login");
			randomCodeCheck.removeClass("ico_right_login");
			return;
		}
		
		if(randomCode.val().length<5){
			registerTip.html("图形验证码长度为5位字符，注意区分大小写");
			error_tips.css("width","280px");
			error_tips.show(500);
			randomCodeCheck.addClass("ico_wrong_login");
			randomCodeCheck.removeClass("ico_right_login");
			return;
		}
	}
	);

	//“会员登录”选项卡里面的图形验证码发生键盘输入事件且字符个数为5个时触发与服务器的异步合法性验证
	$("#randomCode0").keyup(function(event){
		
			var randomCode = $("#randomCode0");

			if(randomCode.val().length==5){
				checkRandomCodeForAjax(0);
				event.stopPropagation();
			}
		}
		);
	
	//“会员登录”选项卡里面的图形验证码发生变化事件且字符个数为5个时触发与服务器的异步合法性验证
//	$("#randomCode0").change(function(event){
//		
//		var randomCode = $("#randomCode0");
//		
//		if(randomCode.val().length==5){
//			checkRandomCodeForAjax(0);
//		}
//	}
//	);
	
	
	//“商户登录”选项卡里面的图形验证码发生键盘输入事件且字符个数为5个时触发与服务器的异步合法性验证
	$("#randomCode1").keyup(function(event){
		
		var randomCode = $("#randomCode1");
		
		if(randomCode.val().length==5){
			checkRandomCodeForAjax(1);
			event.stopPropagation();
		}
	}
	);
	
	//“商户登录”选项卡里面的图形验证码发生变化事件且字符个数为5个时触发与服务器的异步合法性验证
//	$("#randomCode1").change(function(event){
//		
//		var randomCode = $("#randomCode1");
//		
//		if(randomCode.val().length==5){
//			checkRandomCodeForAjax(1);
//		}
//	}
//	);
	
	
});


/*
 * 点击“登录”按钮后进行表单提交前的字段客户端校验
 * @author zhangming2.zh
 * t-0 个人会员;t-1 商户会员
 */
function doSubmit(t){
	
	var userAccount = $("#userAccount"+t);
	var password = $("#password"+t);
	var randomCode = $("#randomCode"+t);
	var error_tips = $(".error-tips"+t);
	var registerTip = $("#registerTip"+t);
	var randomCodeCheck = $("#randomCodeCheck"+t);
	
	error_tips.hide();
	registerTip.html("");
	
	if(userAccount.val() == "" || userAccount.val().length==0){
		registerTip.html("请输入用户名");
		error_tips.css("width","206px");
		error_tips.show(500);
		userAccount.focus();
		return false;
	}
	
	if(password.val().length < 6 || password.val().length > 16){
		
		registerTip.html("密码长度为6至16位字符，注意区分大小写");
		error_tips.css("width","276px");
		error_tips.show(500);
		password.focus();
		return false;
    }
	

	if(randomCode.val()=="" || randomCode.val().length==0){
		registerTip.html("图形验证码不能为空,请输入图形验证码");
		error_tips.css("width","280px");
		error_tips.show(500);
		randomCodeCheck.addClass("ico_wrong_login");
		randomCodeCheck.removeClass("ico_right_login");
		randomCode.focus();
		return false;
	}
	
	if(randomCode.val().length<5){
		registerTip.html("图形验证码长度为5位字符，注意区分大小写");
		error_tips.css("width","280px");
		error_tips.show(500);
		randomCodeCheck.addClass("ico_wrong_login");
		randomCodeCheck.removeClass("ico_right_login");
		randomCode.focus();
		return false;
	}
	
	//避免明文密码域出现在HTTP请求体中
	$("#password0").attr("disabled", true);
	$("#password1").attr("disabled", true);
	
	if(t==1){
		//商户登录
		$("#passwordMerch").val(calcMD5(password.val()));
	}else if(t==0){
		//个人会员登录
		$("#passwordCustomer").val(calcMD5(password.val()));
	}

	return true;
}


/*
 * 异步与服务器端通信校验图形验证码的合法性
 * @author zhangming2.zh
 * t-0 个人会员;t-1 商户会员
 */
function checkRandomCodeForAjax(t){
	
	var randomCode = $("#randomCode"+t);
	var error_tips = $(".error-tips"+t);
	var registerTip = $("#registerTip"+t);
	var randomCodeCheck = $("#randomCodeCheck"+t);
	
	var data = {randomCode:randomCode.val(),type:t};
	$.ajax({
		type:"POST",
		url:path+"/exchangecenter/login/checkRandomCodeForAjax."+Utils.webType,
		data:data,
		dataType:"json",
		success:function(data){
			
			if(!data.result){
				registerTip.html("图形验证码错误");
				error_tips.css("width","286px");
				error_tips.show(500);
				randomCodeCheck.addClass("ico_wrong_login");
				randomCodeCheck.removeClass("ico_right_login");
				randomCode.focus();
			}else{
				randomCodeCheck.addClass("ico_right_login");
				randomCodeCheck.removeClass("ico_wrong_login");
				error_tips.hide(500);
				registerTip.html("");
			} 
		}
	});
}


function getCookie(name){
//	var arr,reg = new RegExp("(^|)"+name+"=([^;]*)(;|$)");
//	if(arr=document.cookie.match(reg))
//		return unescape(arr[2]);
//	else
//		return "";
//	var arr,reg = new RegExp(name+"=.+");
	var cookieValue = "";  
    if (document.cookie && document.cookie != '') {  
        var cookies = document.cookie.split(';');  
        for (var i = 0; i < cookies.length; i++) {  
            var cookie = jQuery.trim(cookies[i]);  
            if (cookie.substring(0, name.length + 1) == (name + '=')) {  
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));  
                break;  
            }  
        }  
    }
    return cookieValue;  
}



//登录弹窗处理
function loginWondowSubmit(){
	
	if(doSubmit(0)){
		$.ajax({
			type:"POST",
			url:path+"/exchangecenter/login/loginForAjax."+Utils.webType,
			data:$("#form0").serialize(),
			dataType:"json",
			success:function(data){
				
				//将密码文本域复位有效
				$("#password0").attr("disabled", false);
				
				if(!data.result){
					//如果异步登录失败
					loadimage(0);
					//清空密码输入框的显示值和实际传递值
					$("#password0").val('');
					$("#passwordCustomer").val('');
					$("#registerTip0").html(data.errTip);
					$(".error-tips0").css("width","286px");
					$(".error-tips0").show(500);
				}else{
					if(afterLoginUrl!=''){
						location.href = afterLoginUrl;
					}else{
						window.location.reload();
					}
					
				} 
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				//访问Web服务器端发生错误
				//textStatus封装了错误信息，有可能得到null，也有可能是"timeout"、"error"、"notmodified"和"parseerror"
				if(textStatus!=''&&textStatus!=null){
					if(textStatus.indexOf("timeout")!=-1){
						$("#divTipSmsError").dialog({
							show:'slide',
							closeOnEscape:true,
							dialogClass:"demo",
							posision:"center",
							closeText:"close",
							title:"登录失败",
							bgiframe:false,
							autoOpen:false,
							draggable:true,
							resizable:false,
							modal:true,
							width:430,
							height:200,
							stack:true,
							zIndex:9999,
							buttons:{"关   闭":function(){$(this).dialog("close");}}
						});
						$("#divTipSmsError #errTip").html("网络连接超时，登录失败！");
						$("#divTipSmsError").dialog("open");
						//关闭按钮被单击后关闭对话框
//						$("#closeDialogBtn").click(function(){
//							$("#divTipSmsError").dialog("close");
//						}
//						);
						
//						$("#registerTip").html("网络连接超时，验证码发送失败！");
//						$(".error-tips").css("width","226px");
//						$(".error-tips").show(500);
					}else{
//						alert(errorThrown+" :"+textStatus);
						$("#divTipSmsError").dialog({
							show:'slide',
							closeOnEscape:true,
							dialogClass:"demo",
							posision:"center",
							closeText:"close",
							title:"登录失败",
							bgiframe:false,
							autoOpen:false,
							draggable:true,
							resizable:false,
							modal:true,
							width:430,
							height:200,
							stack:true,
							zIndex:9999,
							buttons:{"关   闭":function(){$(this).dialog("close");}}
						});
						$("#divTipSmsError #errTip").html(errorThrown+" :"+textStatus);
						$("#divTipSmsError").dialog("open");
						//关闭按钮被单击后关闭对话框
//						$("#closeDialogBtn").click(function(){
//							$("#divTipSmsError").dialog("close");
//						}
//						);
					}
				}else{
//					alert("网络传输发生未知错误，验证码发送失败！");
					$("#divTipSmsError").dialog({
						show:'slide',
						closeOnEscape:true,
						dialogClass:"demo",
						posision:"center",
						closeText:"close",
						title:"登录失败",
						bgiframe:false,
						autoOpen:false,
						draggable:true,
						resizable:false,
						modal:true,
						width:430,
						height:200,
						stack:true,
						zIndex:9999,
						buttons:{"关   闭":function(){$(this).dialog("close");}}
					});
					$("#divTipSmsError #errTip").html("网络传输发生未知错误，登录失败！");
					$("#divTipSmsError").dialog("open");
					//关闭按钮被单击后关闭对话框
//					$("#closeDialogBtn").click(function(){
//						$("#divTipSmsError").dialog("close");
//					}
//					);
					
//					$("#registerTip").html("网络传输发生未知错误，新邮箱激活邮件发送失败！");
//					$(".error-tips").css("width","226px");
//					$(".error-tips").show(500);
				}
			}
		});
	}
}

function clearForm(){
	$('#userAccount0').val("");
	$('#password0').val("");
	$('#randomCode0').val("");
}
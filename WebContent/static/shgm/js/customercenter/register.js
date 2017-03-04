

$(function(){
	/*
	var $hide_pro=$("#yzm-display");
	var txtCodeCheck = $("#txtCodeCheck");
	$hide_pro.hide();
	*/
	
	//短信验证码的“免费获取”按钮的单击事件
	$("#log-get").click(function(){
		$("#passwordEnc").val(calcMD5($("#password").val()));
		var phoneNum = $("#phoneNum").val();
		var account = $("#account").val();
		var passwordEnc = $("#passwordEnc").val();
		var registerToken = $("#registerToken").val();
		
		if(new RegExp("^0?(13|15|17|18|14)[0-9]{9}$").test(phoneNum)){
			//手机号码格式正确的情况下
			
			/*
			$("#txtCode").val('');
			txtCodeCheck.removeClass("ico_right_register");
			txtCodeCheck.removeClass("ico_wrong_register");
			$hide_pro.show(500);
			//$("#yzm-close").attr('disabled',true);
			loadimage();
			return false;
			*/
			
			$.ajax({
				type:"POST",
				url:ctx + "/exchangecenter/register/ajaxExistMobile."+Utils.webType,
				data : {
					phone_num : phoneNum,
					account : account,
					passwordEnc : passwordEnc,
					registerToken : registerToken
				},
				dataType:"json",
				success:function(data){

					if(data.resultStr=="phoneNumberUsed"){
					//该手机号码已经被使用，请重新输入
						
						$("#showinfomess").html("该手机号码已经被使用，请重新输入");
						$('.yyc2').slideDown(200);
						
						$('.close').click(function(){
							$('.yyc2').slideUp(200);
						});
						
						$("#registerTip").html("该手机号码已经被使用，请重新输入");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
						
					}else if(data.resultStr=="userAccountUsed"){
						//用户名已经被占用
						$("#showinfomess").html("该用户名已经被使用，请重新输入");
						$('.yyc2').slideDown(200);
						
						$('.close').click(function(){
							$('.yyc2').slideUp(200);
						});
						
						
						$("#registerTip").html("该用户名已经被使用，请重新输入");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="accountBlank"){
						//未填写用户名
						
						$("#registerTip").html("用户名不能为空，请填写用户名");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="tooQucik"){
						//点击免费获取按钮过于频繁
						
						$("#showinfomess").html("您的操作过于频繁，请稍候片刻再试");
						$('.yyc2').slideDown(200);
						
						$('.close').click(function(){
							$('.yyc2').slideUp(200);
						});
						
						$("#registerTip").html("您的操作过于频繁，请稍候片刻再试");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="passwordBlank"){
						//未填写密码
						
						$("#registerTip").html("密码不能为空，请填写密码");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="phoneNumBlank"){
						//未填写手机号码
						
						$("#registerTip").html("手机号码不能为空，请填写手机号码");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="accountFormatError"){
						//用户名格式不正确
						
						$("#registerTip").html("用户名格式不正确，请重新填写");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="phoneNumberFormatError"){
						//手机号码格式不正确
						
						$("#registerTip").html("手机号码格式不正确，请重新填写");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}else if(data.resultStr=="ok"){
						//该手机号码还未被使用，可以用来注册新用户
						$(".error-tips").hide(500);
						$("#registerTip").html("");
						sendMsg(phoneNum,account,passwordEnc,$("#registerToken").val());
					}else{
						//未知结果
						
						$("#registerTip").html("系统异常，请刷新页面重新尝试");
						$(".error-tips").css("width","256px");
						$(".error-tips").show(500);
					}
				
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){

					//访问Web服务器端发生错误
					//textStatus封装了错误信息，有可能得到null，也有可能是"timeout"、"error"、"notmodified"和"parseerror"
					if(textStatus!=''&&textStatus!=null){
						if(textStatus.indexOf("timeout")!=-1){
//							alert("网络连接超时，验证码手机号码唯一性失败！");
							$("#showinfomess").html("网络连接超时，验证码手机号码唯一性失败！");
							$('.yyc2').slideDown(200);
							
							$('.close').click(function(){
								$('.yyc2').slideUp(200);
							});
							
							$("#registerTip").html("网络连接超时，验证码手机号码唯一性失败！");
							$(".error-tips").css("width","276px");
							$(".error-tips").show(500);
						}else{
//							alert(errorThrown+" :"+textStatus);
							$("#showinfomess").html(errorThrown+" :"+textStatus);
							$('.yyc2').slideDown(200);
							
							$('.close').click(function(){
								$('.yyc2').slideUp(200);
							});
							
						}
					}else{
//						alert("网络传输发生未知错误，验证码手机号码唯一性失败！");
						$("#showinfomess").html("网络传输发生未知错误，验证码手机号码唯一性失败！");
						$('.yyc2').slideDown(200);
						
						$('.close').click(function(){
							$('.yyc2').slideUp(200);
						});
						
						$("#registerTip").html("网络传输发生未知错误，验证码手机号码唯一性失败！");
						$(".error-tips").css("width","286px");
						$(".error-tips").show(500);
					}
				}
			});
			
		}else{
//			
			$("#registerTip").html("请输入正确的手机号码");
			$(".error-tips").css("width","226px");
			$(".error-tips").show(500);
			
			$("#phoneNum").focus();
			
		}
	});
	
	/*
	//图片验证码对应的“确定”按钮的单击事件
	$("#yzm-close").click(function(){
		var phoneNum = $("#phoneNum").val();
		if(passMobile && new RegExp("^0?(13|15|17|18|14)[0-9]{9}$").test(phoneNum)){
			var error_tips = $(".error-tips");
			var registerTip = $("#registerTip");
			var txtCode = $("#txtCode");
			var txtCodeCheck = $("#txtCodeCheck");
			
			if(txtCode.val()=="" || txtCode.val().length==0){
				registerTip.html("请输入图形验证码");
				error_tips.css("width","206px");
				error_tips.show(500);
				txtCodeCheck.addClass("ico_wrong_register");
				txtCodeCheck.removeClass("ico_right_register");
				txtCode.focus();
				return;
			}else if(txtCode.val().length<5){
					registerTip.html("图形验证码长度为5位字符，注意区分大小写");
					error_tips.css("width","290px");
					error_tips.show(500);
					txtCodeCheck.addClass("ico_wrong_register");
					txtCodeCheck.removeClass("ico_right_register");
					txtCode.focus();
					return;
			}
			else{
				checkTxtCodeForRegister();
			}
		}else{
//			alert("请先输入正确的手机号码");
		   	$("#divTipSmsError").dialog({
				show:'slide',
				closeOnEscape:true,
				dialogClass:"demo",
				posision:"center",
				closeText:"close",
				title:"手机号码格式不正确",
				bgiframe:false,
				autoOpen:false,
				draggable:true,
				resizable:false,
				modal:true,
				width:430,
				height:200,
				buttons:{"关   闭":function(){$(this).dialog("close");}}
			});
			$("#divTipSmsError #errTip").html("请先输入正确的手机号码");
			$("#divTipSmsError").dialog("open");
			return false;
		}
	});
	
*/	

	
/*	
	//图形验证码失去焦点时触发本身的客户端校验
	$("#txtCode").blur(function(event){
		
		var error_tips = $(".error-tips");
		var registerTip = $("#registerTip");
		var txtCode = $("#txtCode");
		var txtCodeCheck = $("#txtCodeCheck");
		
		if(txtCode.val()=="" || txtCode.val().length==0){
			registerTip.html("请输入图形验证码");
			error_tips.css("width","206px");
			error_tips.show(500);
			txtCodeCheck.addClass("ico_wrong_register");
			txtCodeCheck.removeClass("ico_right_register");
			//取消事件的冒泡（传播）
			event.stopPropagation();
			return;
		}

		if(txtCode.val().length<5){
			registerTip.html("图形验证码长度为5位字符，注意区分大小写");
			error_tips.css("width","290px");
			error_tips.show(500);
			txtCodeCheck.addClass("ico_wrong_register");
			txtCodeCheck.removeClass("ico_right_register");
			txtCode.focus();
			return;
		}
	}
	);
	
	*/

	
	/*
	//“免费获取短信验证码”图层里面的图形验证码发生键盘输入事件且字符个数为5个时触发与服务器的异步合法性验证
	$("#txtCode").keyup(function(event){
		
			var txtCode = $("#txtCode");
			
			if(txtCode.val().length==5){
				checkTxtCodeForRegister();
			}
		}
		);
	*/
	
	

	//短信验证码发生键盘输入事件且字符个数为6个时触发与服务器的异步合法性验证
	$("#smsCode").keyup(function(event){
		
			var smsCode = $("#smsCode");

			if(smsCode.val().length==6){
				checkSmsCodeForAjax();
				event.stopPropagation();
			}
		}
		);
	
	//短信验证码发生变化事件且字符个数为6个时触发与服务器的异步合法性验证
//	$("#smsCode").change(function(event){
//		
//		var smsCode = $("#smsCode");
//		
//		if(smsCode.val().length==6){
//			checkSmsCodeForAjax();
//		}
//	}
//	);
	
	
	//短信验证码失去焦点时触发客户端校验
	$("#smsCode").blur(function(event){
		
		var error_tips = $(".error-tips");
		var registerTip = $("#registerTip");
		var smsCode = $("#smsCode");
		var smsCodeCheck = $("#smsCodeCheck");
		//短信验证码的“免费获取”按钮
		var logGetBtn = $("#log-get");
		
		//IE浏览器下event.relatedTarget为null，为了保障兼容性则不作下面的前端校验
		if (event.relatedTarget != null) {
			//短信验证码文本框的失去焦点事件的触发者如果不是“免费获取”按钮则校验短信验证码内容
			if (event.relatedTarget != logGetBtn[0]) {
				if (smsCode.val() == "" || smsCode.val().length == 0) {
					registerTip.html("请输入短信验证码");
					error_tips.css("width", "206px");
					error_tips.show(500);
					smsCodeCheck.addClass("ico_wrong_register");
					smsCodeCheck.removeClass("ico_right_register");
					//取消事件的冒泡（传播）
					event.stopPropagation();
					return;
				}
				if (smsCode.val().length < 5) {
					registerTip.html("短信验证码长度为5位字符，注意区分大小写");
					error_tips.css("width", "290px");
					error_tips.show(500);
					smsCodeCheck.addClass("ico_wrong_register");
					smsCodeCheck.removeClass("ico_right_register");
					smsCode.focus();
					return;
				}
			}
		}
		
	}
	);
	
	
	
	//限制手机号码只能键入数字
	$("#phoneNum").keyup(function(){
		$(this).val($(this).val().replace(/\D/g,""));
	});
	
	
	
	
	//验证
	$('.log-ul01 input[name=account]').initValidate({
		minLen:4,
		maxLen:20,
		required:true,
		vtype:'account',
		notRegExp:'^\\d+$',
		tips:'4-20字符，包括字母（区分大小写）、数字、下划线、不能全部为数字',
		onError: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-after');
			pel.removeClass('tips-before');
			pel.removeClass('tips-over');
			//if(!pel.text()) {
			//	pel.text(this.tips);
			//}
			tips_el.find('p span').addClass('tips-afterbg');
			tips_el.find('p span').show();
		},
		onSuccess: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-over');
			pel.removeClass('tips-after');
			//pel.text('');
			tips_el.find('p span').removeClass('tips-afterbg');
			tips_el.find('p span').hide();
		},
		onFucus: function() {
			
		}
	});
	
	$('.log-ul01 input[name=password]').initValidate({
		minLen:6,
		maxLen:16,
		required:true,
		noCutLen:true,
		vtype:'password',
		account:'account',
		tips:'请用英文字母（区分大小写）、数字、符号组成，6-16个字符,不能与用户名相同',
		onError: function(el, params) {
			//清空密码框
			$('.log-ul01 input[name=password]').val("");
			
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-after');
			pel.removeClass('tips-before');
			pel.removeClass('tips-over');
			tips_el.find('p span').addClass('tips-afterbg');
			tips_el.find('p span').show();
		},
		onSuccess: function(el, params) {
			//清除样式
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-over');
			pel.removeClass('tips-after');
			tips_el.find('p span').removeClass('tips-afterbg');
			tips_el.find('p span').hide();
		},
		onFucus: function() {
			
		}
	});
	
	
	$('.log-ul01 input[name=confirm_password]').initValidate({
		minLen:6,
		maxLen:16,
		required:true,
		noCutLen:true,
		vtype:'password',
		confirmPass:'password',		//html标签的name属性
		onError: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-after');
			pel.removeClass('tips-before');
			pel.removeClass('tips-over');
			tips_el.find('p span').addClass('tips-afterbg');
			tips_el.find('p span').show();
		},
		onSuccess: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-over');
			pel.removeClass('tips-after');
			tips_el.find('p span').removeClass('tips-afterbg');
			tips_el.find('p span').hide();
		},
		onFucus: function() {
			
		}
	});
	
	
	$('.log-ul01 input[name=phone_num]').initValidate({
		minLen:11,
		maxLen:11,
		required:true,
		vtype:'mobile',
		onError: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-after');
			pel.removeClass('tips-before');
			pel.removeClass('tips-over');
			tips_el.find('p span').addClass('tips-afterbg');
			tips_el.find('p span').show();
		},
		onSuccess: function(el, params) {
			var tips_el = el.siblings('.log-tips-div');
			var pel = tips_el.find('p');
			pel.addClass('tips-over');
			pel.removeClass('tips-after');
			tips_el.find('p span').removeClass('tips-afterbg');
			tips_el.find('p span').hide();
		},
		onFucus: function() {
			
		}
	});
	
	$('.log-ul01 input[name=smsCode]').initValidate({
		maxLen:8,
		required:true
	});
	
	
	/*
	$('.log-ul01 input[name=txtCode]').initValidate({
		maxLen:5,
		required:true
	});
	
	*/

	$('.log-ul01 input[name=account]').blur(function(){
		if($(this).val()!=""){
			//alert($(this).val());
			var url = ctx + "/exchangecenter/register/ajaxValidateAccount."+Utils.webType;
			$.post(url,{account:$(this).val()},function(result){
				if(result=="false"){
					
					$("#registerTip").html("用户名格式不正确，请重新填写");
					$(".error-tips").css("width","256px");
					$(".error-tips").show(500);
					
					var tips_el = $('.log-ul01 input[name=account]').siblings('.log-tips-div');
					var pel = tips_el.find('p');
					pel.addClass('tips-after');
					pel.removeClass('tips-before');
					pel.removeClass('tips-over');
					pel.html('<span class="tips-beforebg tips-afterbg"></span>4-20字符，包括字母（区分大小写）、数字、下划线、不能全部为数字');
				}else{
					var tips_el = $('.log-ul01 input[name=account]').siblings('.log-tips-div');
					var pel = tips_el.find('p');
					var sp = tips_el.find('p span');
					pel.html('');
					pel.append(sp).append('4-20字符，包括字母（区分大小写）、数字、下划线、不能全部为数字');
					$("#registerTip").html("");
					$(".error-tips").hide(500);
				}
			});
		}
	});
	
	//手机号码文本域失去焦点时触发
	$('.log-ul01 input[name=phone_num]').blur(function(){
		if($(this).val()!=""){
			//alert($(this).val());
			$("#passwordEnc").val(calcMD5($("#password").val()));
			var account = $("#account").val();
			var passwordEnc = $("#passwordEnc").val();
			var url = ctx + "/exchangecenter/register/ajaxValidateMobile."+Utils.webType;
			$.post(
					url,
					{
						phone_num : $(this).val(),
						account : account,
						passwordEnc : passwordEnc,
						registerToken:$('#registerToken').val()
					},
					function(data) {
						if(data.resultStr=="phoneNumBlank"){
							//未填写手机号码
							
							$("#registerTip").html("手机号码不能为空，请填写手机号码");
							$(".error-tips").css("width","256px");
							$(".error-tips").show(500);
						}else if(data.resultStr=="phoneNumberFormatError"){
							//手机号码格式不正确
							
							$("#registerTip").html("手机号码格式不正确，请重新填写");
							$(".error-tips").css("width","256px");
							$(".error-tips").show(500);
						}else if(data.resultStr=="false") {
							var tips_el = $('.log-ul01 input[name=phone_num]').siblings('.log-tips-div');
							var pel = tips_el.find('p');
							var sp = tips_el.find('p span');
							pel.html('');
							pel.append(sp).append('请输入11位手机号码');
							
						}else if(data.resultStr=="accountBlank"){
							//未填写用户名
							
							$("#registerTip").html("用户名不能为空，请填写用户名");
							$(".error-tips").css("width","256px");
							$(".error-tips").show(500);
						}else if(data.resultStr=="accountFormatError"){
							//用户名格式不正确
							
							$("#registerTip").html("用户名格式不正确，请重新填写");
							$(".error-tips").css("width","256px");
							$(".error-tips").show(500);
						}else{
							$("#registerTip").html("");
							$(".error-tips").hide(500);
						}
					});
		}
	});
	
	/*
	loadimage();
	*/
	$('.log-ul01 input[name=account]').focus();
});

/*
//
// 异步与服务器通信校验图形验证码的正确性
//
function checkTxtCodeForRegister(){
	//可隐藏和显示的图形验证码图层
	var $hide_pro=$("#yzm-display");
	var txtCode = $("#txtCode");
	var txtCodeCheck = $("#txtCodeCheck");
	var phoneNum = $("#phoneNum").val();
	var url = ctx + "/exchangecenter/checkTxtCodeForRegister."+Utils.webType;
	
	$.post(url, {txtCode : $("#txtCode").val()}, function(data){
		if(data.resultStr=="true"){
			//$("#yzm-close").attr('disabled',false);
			$("#registerTip").html("");
			$(".error-tips").hide(500);
			txtCodeCheck.addClass("ico_right_register");
			txtCodeCheck.removeClass("ico_wrong_register");
			$hide_pro.hide(500);
			sendMsg(phoneNum);
		}else{
			//$("#yzm-close").attr('disabled',true);
			$("#registerTip").html("图形验证码错误");
			$(".error-tips").css("width","206px");
			$(".error-tips").show(500);
			txtCodeCheck.addClass("ico_wrong_register");
			txtCodeCheck.removeClass("ico_right_register");
			txtCode.focus();
		}
	});
	

}

*/


/**
* 处理获取手机验证码的成功响应
*/
function onGetHandsetVerifyCodeResult(data, textStatus, jqXHR) {
        
	if(data.resultStr=="true"){
		//该手机号码已经被使用，请重新输入
			
			$("#showinfomess").html("该手机号码已经被使用，请重新输入");
			$('.yyc2').slideDown(200);
			
			$('.close').click(function(){
				$('.yyc2').slideUp(200);
			});
			
			$("#registerTip").html("该手机号码已经被使用，请重新输入");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
			
		}else if(data.resultStr=="accountBlank"){
			//未填写用户名
			
			$("#registerTip").html("用户名不能为空，请填写用户名");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}else if(data.resultStr=="passwordBlank"){
			//未填写密码
			
			$("#registerTip").html("密码不能为空，请填写密码");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}else if(data.resultStr=="phoneNumBlank"){
			//未填写手机号码
			
			$("#registerTip").html("手机号码不能为空，请填写手机号码");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}else if(data.resultStr=="accountFormatError"){
			//用户名格式不正确
			
			$("#registerTip").html("用户名格式不正确，请重新填写");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}else if(data.resultStr=="phoneNumberFormatError"){
			//手机号码格式不正确
			
			$("#registerTip").html("手机号码格式不正确，请重新填写");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}else if(data.resultStr=="false"){

		    if (data.code==0) {
		    	// 短信平台发送短信验证码成功
				//alert("短信发送成功,请注意查收,短信验证码将在三分钟后失效!");
		    	$("#showinfomess").html(data.message);
				$('.yyc2').slideDown(200);
				
				$('.close').click(function(){
					$('.yyc2').slideUp(200);
				});
		    			
				updateTimeLabel();

		    }else if(data.code==-2){
		    	//每60秒只能获取一次验证码
		    	$("#log-get").attr('disabled',false);
		    	//alert("发送短信验证码失败，请重新获取");
		    	$("#showinfomess").html(data.errTip);
				$('.yyc2').slideDown(200);
				
				$('.close').click(function(){
					$('.yyc2').slideUp(200);
				});
		    			
		    }else if(data.code==-1){
		    	//短信平台发送短信验证码失败
				$("#log-get").attr('disabled',false);
				//alert("发送短信验证码失败，请重新获取");
				$("#showinfomess").html(data.errTip);
				$('.yyc2').slideDown(200);
				
				$('.close').click(function(){
					$('.yyc2').slideUp(200);
				});
				
			}
		}else{
			//未知结果
			
			$("#registerTip").html("系统异常，请刷新页面重新尝试");
			$(".error-tips").css("width","256px");
			$(".error-tips").show(500);
		}
	
	
	
	
}


/**
 *  处理获取手机短信验证码时发生网络错误的情况
 */
function onGetHandsetVerifyCodeError(XMLHttpRequest,textStatus,errorThrown) {
	//访问Web服务器端发生错误
	//textStatus封装了错误信息，有可能得到null，也有可能是"timeout"、"error"、"notmodified"和"parseerror"
	if(textStatus!=''&&textStatus!=null){
		if(textStatus.indexOf("timeout")!=-1){
//			alert("网络连接超时，短信验证码发送失败！");
			$("#showinfomess").html("网络连接超时，短信验证码发送失败！");
			$('.yyc2').slideDown(200);
			
			$('.close').click(function(){
				$('.yyc2').slideUp(200);
			});
			
			$("#registerTip").html("网络连接超时，短信验证码发送失败！");
			$(".error-tips").css("width","276px");
			$(".error-tips").show(500);
		}else{
//			alert(errorThrown+" :"+textStatus);
			$("#showinfomess").html(errorThrown+" :"+textStatus);
			$('.yyc2').slideDown(200);
			
			$('.close').click(function(){
				$('.yyc2').slideUp(200);
			});
			
		}
	}else{
//		alert("网络传输发生未知错误，验证码发送失败！");
		$("#showinfomess").html("网络传输发生未知错误，短信验证码发送失败！");
		$('.yyc2').slideDown(200);
		
		$('.close').click(function(){
			$('.yyc2').slideUp(200);
		});
		
		$("#registerTip").html("网络传输发生未知错误，短信验证码发送失败！");
		$(".error-tips").css("width","286px");
		$(".error-tips").show(500);
	}
}


/*
 * 
 * @param phoneNum 手机号码
 * @param account 用户名
 * @param passwordEnc 密码(MD5)
 * @param registerToken  防刷Token
 */
function sendMsg(phoneNum,account,passwordEnc,registerToken){
	$("#log-get").attr('disabled',true);
	var url = ctx + "/exchangecenter/sendSmsCodeForRegister."+Utils.webType;
	jQuery.post(url, {
		phone_num : phoneNum,
		account : account,
		passwordEnc : passwordEnc,
		registerToken : registerToken
	}, onGetHandsetVerifyCodeResult, "json").error(onGetHandsetVerifyCodeError);
	
};




/*
 * 异步与服务器端通信校验短信验证码的合法性
 * @author zhangming2.zh
 */
function checkSmsCodeForAjax(){
	
	var smsCode = $("#smsCode");
	var error_tips = $(".error-tips");
	var registerTip = $("#registerTip");
	var smsCodeCheck = $("#smsCodeCheck");
	
	var data = {smsCode:smsCode.val()};
	$.ajax({
		type:"POST",
		url:path+"/exchangecenter/register/checkSmsCodeForAjax."+Utils.webType,
		data:data,
		dataType:"json",
		success:function(data){
			
			if(!data.result){
				registerTip.html("短信验证码错误");
				error_tips.css("width","206px");
				error_tips.show(500);
				smsCodeCheck.addClass("ico_wrong_register");
				smsCodeCheck.removeClass("ico_right_register");
				smsCode.focus();
			}else{
				smsCodeCheck.addClass("ico_right_register");
				smsCodeCheck.removeClass("ico_wrong_register");
				error_tips.hide(500);
				registerTip.html("");
			} 
		},
	error:function(XMLHttpRequest,textStatus,errorThrown){
		//访问Web服务器端发生错误
		//textStatus封装了错误信息，有可能得到null，也有可能是"timeout"、"error"、"notmodified"和"parseerror"
		if(textStatus!=''&&textStatus!=null){
			if(textStatus.indexOf("timeout")!=-1){
				$("#showinfomess").html("网络连接超时，校验短信验证码失败！");
				$('.yyc2').slideDown(200);
				
				$('.close').click(function(){
					$('.yyc2').slideUp(200);
				});
								
				$("#registerTip").html("网络连接超时，校验短信验证码失败！");
				$(".error-tips").css("width","286px");
				$(".error-tips").show(500);
			}else{
//				alert(errorThrown+" :"+textStatus);
				$("#showinfomess").html(errorThrown+" :"+textStatus);
				$('.yyc2').slideDown(200);
				
				$('.close').click(function(){
					$('.yyc2').slideUp(200);
				});
				
			}
		}else{
//			alert("网络传输发生未知错误，验证码发送失败！");
			$("#showinfomess").html("网络传输发生未知错误，校验短信验证码失败！");
			$('.yyc2').slideDown(200);
			
			$('.close').click(function(){
				$('.yyc2').slideUp(200);
			});
						
			$("#registerTip").html("网络传输发生未知错误，校验短信验证码失败！");
			$(".error-tips").css("width","286px");
			$(".error-tips").show(500);
		}
	}
	});
}





/*
 * 一段时间内“免费获取”按钮不可用,其标签从60秒倒计时显示等待时间 
 */
function updateTimeLabel() {
	
	//“免费获取”按钮灰色不可用倒计时秒数
	var time = 60;
    var btn = $("#log-get");  
	//设定“免费获取”按钮不可用
    btn.attr('disabled',true);
    //设定“免费获取”按钮灰色
    btn.css({'background':'#b7b7b7','cursor':'default'});
    
    var iCount = setInterval(function() {  
    	
        if (time <= 0) {
           btn.attr('disabled',false);
           btn.val("免费获取");
           clearInterval(iCount);
           btn.css({'background':'#fe6e6d','cursor':'pointer'});
        }else{  
        	time = time-1;
        	if(time < 10){
        		btn.val(" " + (time) + "秒等待");  
        	}else{
        		btn.val("" + (time) + "秒等待");
        	}
        }
    }, 1000);
};

/*
//
// 生成图形验证码
//
function loadimage() {
	var imgCode = document.getElementById('codeimage');
	//append a query string to the URL, it will force IE not to use cached copy of the img
	imgCode.setAttribute('src', ctx + '/exchangecenter/RandomCodeAction/makeImageCodeForRegist.'+Utils.webType'?r=' + Math.random());
	
	if($("#txtCode").val() != ""){
		$("#txtCode").val("");
	}
	
}

*/

/*
 * 点击注册按钮后进行表单控件验证
 */
function doSubmit(){
	if($(".tips-after").size() > 0 || $("#smsCode").val() == "" 
		/*
		||$("#txtCode").val() == ""
		*/
										){
		if($("#smsCode").val() == "" ){
			$("#registerTip").html("短信验证码不能为空，请填写短信验证码");
			$(".error-tips").css("width","266");
			$(".error-tips").show(500);
		}
		/*
		else if($("#txtCode").val() == ""){
			$("#registerTip").html("您还未获取短信验证码,请单击'免费获取'按钮并填写5位图形验证码后获取");
			$(".error-tips").css("width","500px");
			$(".error-tips").show(500);
		}
		*/
		else{
			$("#registerTip").html("");
			$(".error-tips").hide(500);
		}
		return false;
	}
	if($("#agreeChk").attr("checked") != "checked" && $("#agreeChk").attr("checked") != true){
		$("#registerTip").html("请先同意协议");
		$(".error-tips").show(500);
		return false;
	}

	
	//避免明文密码域出现在HTTP请求体中
	$("#password").attr("disabled", true);
	$("#confirm_password").attr("disabled", true);
	
	$("#passwordEnc").val(calcMD5($("#password").val()));
	
	return true;
}










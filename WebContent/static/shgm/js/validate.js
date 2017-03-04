var ValidateUtil = {
    validateRegExp : {
    	decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
	    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
	    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
	    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
	    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
	    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
	    intege: "^-?[1-9]\\d*$", //整数
	    intege1: "^[1-9]\\d*$", //正整数
	    intege2: "^-[1-9]\\d*$", //负整数
	    num: "^([+-]?)\\d*\\.?\\d+$", //数字
	    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
	    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
	    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
	    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
	    color: "^[a-fA-F0-9]{6}$", //颜色
	    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
	    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
	    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
	    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
	    letter: "^[A-Za-z]+$", //字母
	    letter_l: "^[a-z]+$", //小写字母
	    letter_u: "^[A-Z]+$", //大写字母
	    mobile: "^0?(13|15|17|18|14)[0-9]{9}$", //手机
	    notempty: "^\\S+$", //非空
	    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
	    fullNumber: "^[0-9]+$", //数字
	    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
	    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
	    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
	    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
	    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
	    account: "^[A-Za-z0-9_]+$", //用户名
	    zipcode: "^\\d{6}$"//邮编
	},
	
	pwdLevel : function (value) {
	    var pattern_1 = /^.*([\W_])+.*$/i;
	    var pattern_2 = /^.*([a-zA-Z])+.*$/i;
	    var pattern_3 = /^.*([0-9])+.*$/i;
	    var level = 0;
	    if (value.length > 10) {
	        level++;
	    }
	    if (pattern_1.test(value)) {
	        level++;
	    }
	    if (pattern_2.test(value)) {
	        level++;
	    }
	    if (pattern_3.test(value)) {
	        level++;
	    }
	    if (level > 3) {
	        level = 3;
	    }
	    return level;
	},
    
	isNull: function (str) {
        return (str == "" || typeof str != "string");
    },
    
    isMaxLength : function(str, max_len) {
    	return !str || str.length <= max_len;
    },
    
    isMinLength : function(str, min_len) {
    	return str.length >= min_len;
    },
    
    betweenLength: function (str, min_len, max_len) {
        return (str.length >= min_len && str.length <= max_len);
    },
    
    isVtype : function(vtype, value) {
    	var regExp = this.validateRegExp[vtype];
    	if (regExp) {
    		return new RegExp(regExp).test(value);
    	}
    	return false;
    },
    
    isRegExp : function(regExp, value) {
    	return new RegExp(regExp).test(value);
    }
};

var ExtValidateDeal = function (el, options, needcallback) {
	var eid = el.attr('id');
	var etype = el.attr('type');
	if (!etype) {
		etype = el.attr('ctype'); //area, select 应该配置
	} 
	var val = el.val();
	var required = options.required;
	
	//how to validate
	var result = true;
	switch(etype) {
		case 'text': 
		case 'textarea':
		case 'password': {
			var result = true;
			if (options.minLen && !ValidateUtil.isMinLength(val, options.minLen)) {
				result = false;
			}
			
			//输入长度控制, 默认最长10000
			var _max_len = options.maxLen? options.maxLen : 10000;
			if (!ValidateUtil.isMaxLength(val, _max_len)) {
				result = false;
				if (options.noCutLen !== true) {
					val = val.substr(0, _max_len);
					el.val(val);
				}
			}
			
			//必填
			if (required && ValidateUtil.isNull(val)) {
				result = false;
			}
			
			//必须符合的格式
			var vtype = options.vtype;
			var regExp = options.regExp;
			if (vtype && !ValidateUtil.isVtype(vtype, val)) {
				result = false;
			} else if (regExp && !ValidateUtil.isRegExp(regExp, val)) {
				result = false;
			}
			
			//不能符合的格式
			var notRegExp = options.notRegExp;
			if (notRegExp && ValidateUtil.isRegExp(notRegExp, val)) {
				result = false;
			}
			
			//确认密码
			var confirmPass = options.confirmPass;
			if(confirmPass){
				var pwd = $('.log-ul01 input[name=' + confirmPass + ']').val();
				if(val != pwd){
					result = false;
				}
			}
			
			//密码与用户名不能相同
			var account = options.account;
			if(account){
				var account = $('.log-ul01 input[name=' + account + ']').val();
				if(val == account){
					result = false;
				}
			}
			
			
			
			//密码强度
			if(vtype == 'password' && !confirmPass){
				var lvl = ValidateUtil.pwdLevel(val);
				if(lvl == 1){
					$(".log-pwd-low").addClass('log-pwd-bg');
					$(".log-pwd-mid").removeClass('log-pwd-bg');
					$(".log-pwd-high").removeClass('log-pwd-bg');
				}else if(lvl == 2){
					$(".log-pwd-low").addClass('log-pwd-bg');
					$(".log-pwd-mid").addClass('log-pwd-bg');
					$(".log-pwd-high").removeClass('log-pwd-bg');
				}else if(lvl == 3){
					$(".log-pwd-low").addClass('log-pwd-bg');
					$(".log-pwd-mid").addClass('log-pwd-bg');
					$(".log-pwd-high").addClass('log-pwd-bg');
				}else{
					$(".log-pwd-low").removeClass('log-pwd-bg');
					$(".log-pwd-mid").removeClass('log-pwd-bg');
					$(".log-pwd-high").removeClass('log-pwd-bg');
				}
			}
			
			break;
		}
		default: break;
	}
	
	//自定义validator
	var validator = options.validator;
	if (result && validator) {
		result = validator.call(el, el, val);
	}
	
	//how to deal
	if (needcallback === false) return;
	if (result) {
		if (options.onSuccess) {
			options.onSuccess.call(options, el, {});
		}
	} else {
		if (options.onError) {
			options.onError.call(options, el, {});
		}
	}
};

$.fn.initValidate = function(options) {
	var el = this;
	var etype = el.attr('type');
	if (!etype) {
		etype = el.attr('ctype'); //area, select 应该配置
	} 
	
	//输入时可进行提示显示
	if (options.onFucus) {
		el.focus(options.onFucus);
	}
	
	//输入时进行验证, 但不回调处理
	if (etype == 'text' || etype == 'password' || etype == 'textarea') {
		el.keyup(function() {
			ExtValidateDeal(el, options, false);
		})
	}
	
	//失焦时进行验证
	el.blur(function() {
		ExtValidateDeal(el, options, true);
	});
	
	//为了$.fn.validate
	//$.data('options', options);
};

//立即调用进行验证的方法
$.fn.validate_tmp = function() {
	var el = this;
	var options = $.data('options');
	ExtValidateDeal(el, options);
}
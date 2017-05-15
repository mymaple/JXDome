	var locat = (window.location+'').split('/'); 
	var upimg_url = '';
	
	$(function(){
		if('background'==locat[3]){
			upimg_url =  locat[0]+'//'+locat[2]+'/common/file/upimg.do';
		}else{
			upimg_url =  locat[0]+'//'+locat[2]+'/'+locat[3]+'/common/file/upimg.do';
		}
        upimg_lazyLoad();
    })  
    
    
    function upimg_lazyLoad(){
    	$('img.lazy').lazyload({
            threshold :20
        });
    }
    
    function upimg_toUploadImg(elem, img, fun){
    	var upimg_maxWidth = $(elem).closest('ul').prevAll('input.upimg_maxWidth')[0].value;
    	var upimg_maxHight = $(elem).closest('ul').prevAll('input.upimg_maxHight')[0].value;
    	var formData = new FormData();
		formData.append('img', img);
		formData.append('maxWidth', upimg_maxWidth);
		formData.append('maxHight', upimg_maxHight);
		$.ajax({
			url:  upimg_url,  
		    type: 'POST',
		    data: formData,
		    async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {  
                if(data.resultCode == 'success'){
                	//console.log(data.resultContent)
                	var flag = fun(elem, data.resultContent);
                	if(flag){
                		upimg_error0(elem);
                	}else{
                		upimg_error1(elem,img.name+'图片添加失败');
                	}
                	return flag;
                }else{
                	upimg_error1(elem,img.name+'图片添加失败');
                	return false;
                }
            },  
            error: function (e) {  
            	upimg_error1(elem,img.name+'图片添加失败');
                return false;
            }  
        }); 
    }
    
    function upimg_error1(elem,msg){
		var $upimg_error = $($(elem).closest('li').find('.upimg_error')[0]);
		$upimg_error.show();
		$upimg_error.text(msg);
	}
	
	function upimg_error0(elem){
		var $upimg_error = $($(elem).closest('li').find('.upimg_error')[0]);
		$upimg_error.hide();
		$upimg_error.text();
	}
    
	var upimg_addFun = function(elem, src){
		    				if(src != null && src != ''){
		    					var upimg_name = $(elem).closest('ul').prevAll('input.upimg_name')[0].value;
					    		var $li = $(
							        '<li>'+
										'<div class="upimg_cell upimg_edit">'+
											'<input type="hidden" name="'+upimg_name+'" value="'+src+'" />'+
											'<div class="upimg_error"></div>'+
											'<img src="plugins/myupimg/img/loading.gif" data-original="'+src+'" class="lazy" />'+
											'<div class="upimg_tools">'+
												'<div class="upimg_tools_item left">'+
													'<input type="file" class="upimg_upload" accept="image/*" onchange="upimg_toUpdateUploadImg(this);"/>'+
													'<i class="iconfont icon-edit"></i></div>'+
													'<div class="upimg_tools_item right"><div onclick="upimg_toDeleteUploadImg(this);"><i class="iconfont icon-delete"></i></div></div>'+
											'</div>'+
										'</div>'+
									'</li>'
									);
							    $(elem).closest('li').before($li);
							    upimg_lazyLoad();
							    return true;
					    	}
		    				return false;
					    }
	
		var upimg_updateFun = function(elem, src){
		    				if(src != null && src != ''){
					    		var $li = $(elem).closest('li');
					    		$li.find('input').val(src);
					    		$li.find('img').attr('src',src);
					    		upimg_lazyLoad();
							    return true;
					    	}
		    				return false;
					    }
	
	function upimg_toAddUploadImg(elem) {
		var upimg_count = $(elem).closest('ul').prevAll('input.upimg_count')[0].value;
		//获取文件列表对象
        var fileList = $(elem)[0].files;
        $(elem).after($(elem).clone().val(''));
        var elem1 = $(elem).next()[0];
		$(elem).remove();
		
        $.each(fileList, function(index, img) {
        	var imgname = img.name,
		    	imgsize = img.size,
		    	imgtype = img.type;
		    if(imgsize>2*1024*1024){
		    	upimg_error1(elem1,img.name+'图片不能超过2M');
		    	return false;
		    }
		    if(imgtype.indexOf('image/')>0){
		    	upimg_error1(elem1,img.name+'不是图片格式');
		    	return false;
		    }
		    
		    upimg_toUploadImg(elem1, img, upimg_addFun);
		    var currentCount = $(elem1).closest('ul').children('li').length;
		    if(upimg_count!=0&&upimg_count!=''&&
		    	upimg_count!=null&&currentCount-1>=upimg_count){
		    	$(elem1).closest('li').hide();
		    	return false;
		    }
        });
	}
	
	function upimg_toUpdateUploadImg(elem) {
		//获取文件列表对象
        var img = $(elem)[0].files[0];
        
        var imgname = img.name,
	    	imgsize = img.size,
	    	imgtype = img.type;
	    if(imgsize>2*1024*1024){
	    	upimg_error1(elem,img.name+'图片不能超过2M');
	    	return false;
	    }
	    if(imgtype.indexOf('image/')>0){
	    	upimg_error1(elem,img.name+'不是图片格式');
	    	return false;
	    }
	    
	    upimg_toUploadImg(elem, img, upimg_updateFun);
        
	}
	
	function upimg_toDeleteUploadImg(elem) {
		$(elem).closest('li').nextAll("li:last").show();
		$(elem).closest('li').remove();
	}
	

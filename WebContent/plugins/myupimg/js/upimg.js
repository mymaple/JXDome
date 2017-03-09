	var locat = (window.location+'').split('/'); 
	var upimg_url = '';
	
	$(function(){
		upimg_url =  locat[0]+'//'+locat[2]+'/'+locat[3]+'/common/file/upimg.do';
		
        $('img.lazy').lazyload({
            threshold :20
        });
    })  
    
    function upimg_toUploadImg(elem, img, fun){
    	var formData = new FormData();
		formData.append('img', img);
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
											'<img src="'+src+'" data-original="img/loading.gif" class="lazy" />'+
											'<div class="upimg_tools">'+
												'<div class="upimg_tools_item left">'+
													'<input type="file" class="upimg_upload" accept="image/*" onchange="upimg_toAddUploadImg(this);"/>'+
													'<i class="iconfont icon-edit"></i></div>'+
													'<div class="upimg_tools_item right"><a onclick="upimg_toDeleteUploadImg(this);"><i class="iconfont icon-delete"></i></a></div>'+
											'</div>'+
										'</div>'+
									'</li>'
									);
							    $(elem).closest('li').before($li);
							    return true;
					    	}
		    				return false;
					    }
	
		var upimg_updateFun = function(elem, src){
		    				if(src != null && src != ''){
					    		var $li = $(elem).closest('li');
					    		$li.find('input').val(src);
					    		$li.find('img').attr('src',src);
							    return true;
					    	}
		    				return false;
					    }
	
	function upimg_toAddUploadImg(elem) {
		var upimg_count = $(elem).closest('ul').prevAll('input.upimg_count')[0].value;
		//获取文件列表对象
        var fileList = $(elem)[0].files;
        $.each(fileList, function(index, img) {
        	var imgname = img.name,
		    	imgsize = img.size,
		    	imgtype = img.type;
		    if(imgsize>2*1024*1024){
		    	alert(imgname+'图片不能超过2M');
		    	return false;
		    }
		    if(imgtype.indexOf('image/')>0){
		    	alert(imgname+'不是图片格式');
		    	return false;
		    }
		    
		    upimg_toUploadImg(elem, img, upimg_addFun);
		    var currentCount = $(elem).closest('ul').children('li').length;
		    if(currentCount-1>=upimg_count){
		    	return;
		    }
        });
        if(upimg_count == 1){
        	$(elem).closest('li').hide();
        }
	}
	
	function upimg_toUpdateUploadImg(elem) {
		//获取文件列表对象
        var img = $(elem)[0].files[0];
        
        var imgname = img.name,
	    	imgsize = img.size,
	    	imgtype = img.type;
	    if(imgsize>2*1024*1024){
	    	alert(imgname+'图片不能超过2M');
	    	return false;
	    }
	    if(imgtype.indexOf('image/')>0){
	    	alert(imgname+'不是图片格式');
	    	return false;
	    }
	    
	    upimg_toUploadImg(elem, img, upimg_updateFun);
        
	}
	
	function upimg_toDeleteUploadImg(elem) {
		$(elem).closest('li').next().show();
		$(elem).closest('li').remove();
	}
	

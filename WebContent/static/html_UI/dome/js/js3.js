	$(function(){
        $("img.lazy").lazyload({
            threshold :20
        });
    })  
    
    function toUploadImg(img){
    	var formData = new FormData();
		formData.append('file', img);
		$.ajax({
			url: 'http://1b62112g29.iok.la/JXDome/common/file/upimg.do' ,  
		    type: 'POST',
		    data: formData,
		    async: false,  
            cache: false,  
            contentType: false,  
            processData: false,  
            success: function (data) {  
                if(data.resultCode == "success"){
                	console.log(data.resultContent)
                	return data.resultContent;
                }else{
                	return null;
                }
            },  
            error: function (e) {  
                return null; 
            }  
        }); 
    }
    
	
	function toAddUploadImg(elem) {
		//获取文件列表对象
        var fileList = $(elem)[0].files;
        $(elem)[0].files = null;
        console.log($(elem).attr("value"));
        $.each(fileList, function(index, img) {
        	var imgname = img.name,
		    	imgsize = img.size,
		    	imgtype = img.type;
		    if(imgsize>2*1024*1024){
		    	alert(imgname+"图片不能超过2M");
		    	return false;
		    }
		    if(imgtype.indexOf('image/')>0){
		    	alert(imgname+"不是图片格式");
		    	return false;
		    }
		    
		    var src = toUploadImg(img);
		    if(src != null){
		    	var $li = $(
		        '<li>'+
					'<div class="img_cell img_edit">'+
						'<input type="hidden" name="" id="" value="'+src+'" />'+
						'<img src="'+src+'" data-original="img/grey.gif" class="lazy" />'+
						'<div class="img_tools">'+
							'<div class="img_tools_item left"><input type="file" class="upload_img_type" accept="image/*" onchange="toUpdateUploadImg(this);"/><i class="iconfont icon-edit"></i></div>'+
							'<div class="img_tools_item right"><a onclick="toDeleteUploadImg(this);"><i class="iconfont icon-delete"></i></a></div>'+
						'</div>'+
					'</div>'+
				'</li>'
				);
		        $(elem).closest("li").before($li);
		    }{
		    	alert(imgname+"图片添加失败");
		    }
        });
       
//      var multiple = $(elem).attr("multiple");
//      if('multiple' != multiple){
//      	$(elem).closest("li").hide();
//      }
	}
	
	function toUpdateUploadImg(elem) {
		//获取文件列表对象
        var fileList = $(elem)[0].files;
        //创建文件流获取文件地址
        var src = URL.createObjectURL(fileList[0]);
        $(elem).parent().parent().prev().attr("src",src);
	}
	
	function toDeleteUploadImg(elem) {
		$(elem).closest("li").next().show();
		$(elem).closest("li").remove();
	}
	

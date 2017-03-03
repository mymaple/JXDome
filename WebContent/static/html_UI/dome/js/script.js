	$(function(){
        $("img.lazy").lazyload({
            threshold :20
        });
    })  
	
	function toAddUploadImg(elem) {
		//获取文件列表对象
        var fileList = $(elem)[0].files;
        var upload_img_name = $(elem).prev();
        var formData = new FormData($( "#uploadForm" )[0]);  
        $.ajax({  
             url: 'http://1b62112g29.iok.la/JXDome/common/file/upimgs' ,  
             type: 'POST',  
             data: formData,  
             async: false,  
             cache: false,  
             contentType: false,  
             processData: false,  
             success: function (returndata) {  
                 alert(returndata);  
             },  
             error: function (returndata) {  
                 alert(returndata);  
             }  
        });  
        
        console.log($(elem).attr("value"));
        $.each(fileList, function(index, item) {
        	//创建文件流获取文件地址
	        var src = URL.createObjectURL(item);
	        
	        var itemli = 
	        '<li>'+
				'<div class="img_cell img_edit">'+
					'<img src="'+src+'" data-original="img/grey.gif" class="lazy" />'+
					'<div class="img_tools">'+
						'<div class="img_tools_item left"><input type="file" class="upload_img_type" name="'+upload_img_name+'" accept="image/*" onchange="toUpdateUploadImg(this);"/><i class="iconfont icon-edit"></i></div>'+
						'<div class="img_tools_item right"><a onclick="toDeleteUploadImg(this);"><i class="iconfont icon-delete"></i></a></div>'+
					'</div>'+
				'</div>'+
			'</li>';
	        $(elem).closest("li").before(itemli);
        });
       
        var multiple = $(elem).attr("multiple");
        if('multiple' != multiple){
        	$(elem).closest("li").hide();
        }
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
	

// 鍥剧墖涓婁紶demo
//jQuery(function() {
//    var $ = jQuery,
//        $list = $('#fileList'),
//        // 浼樺寲retina, 鍦╮etina涓嬭繖涓€兼槸2
//        ratio = window.devicePixelRatio || 1,
//
//        // 缂╃暐鍥惧ぇ灏�
//        thumbnailWidth = 100 * ratio,
//        thumbnailHeight = 100 * ratio,
//
//        // Web Uploader瀹炰緥
//        uploader;
//
//    // 初始化Web Uploader
//	var uploader = WebUploader.create({
//	
//	    // 选完文件后，是否自动上传。
//	    auto: true,
//	
//	    // swf文件路径
//	    swf: 'dist/Uploader.swf',
//	
//	    // 文件接收服务端。
//	    server: 'http://1b62112g29.iok.la/JXDome/common/file/upimg',
//	
//	    // 选择文件的按钮。可选。
//	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
//	    pick: '#filePicker',
//	    
////	  //单次上传最多图片数
////	    fileNumLimit: 3,
////	    //单个文件最大为2m
////	    fileSingleSizeLimit: 2*1024*1024,
//	
//	    // 只允许选择图片文件。
////	    accept: {
////	        title: 'Images',
////	        extensions: 'gif,jpg,jpeg,bmp,png',
////	        mimeTypes: 'image/*'
////	    }
//	});
//
//    // 当有文件添加进来的时候
//	uploader.on( 'fileQueued', function( file ) {
//	    var $li = $(
//	            '<div id="' + file.id + '" class="file-item thumbnail">' +
//	                '<img>' +
//	                '<div class="info">' + file.name + '</div>' +
//	            '</div>'
//	            ),
//	        $img = $li.find('img');
//	
//	
//	    // $list为容器jQuery实例
//	    $list.append( $li );
//	
//	    // 创建缩略图
//	    // 如果为非图片文件，可以不用调用此方法。
//	    // thumbnailWidth x thumbnailHeight 为 100 x 100
//	    uploader.makeThumb( file, function( error, src ) {
//	        if ( error ) {
//	            $img.replaceWith('<span>不能预览</span>');
//	            return;
//	        }
//	
//	        $img.attr( 'src', src );
//	    }, thumbnailWidth, thumbnailHeight );
//	});
//
//    // 文件上传过程中创建进度条实时显示。
//	uploader.on( 'uploadProgress', function( file, percentage ) {
//	    var $li = $( '#'+file.id ),
//	        $percent = $li.find('.progress span');
//	
//	    // 避免重复创建
//	    if ( !$percent.length ) {
//	        $percent = $('<p class="progress"><span></span></p>')
//	                .appendTo( $li )
//	                .find('span');
//	    }
//	
//	    $percent.css( 'width', percentage * 100 + '%' );
//	});
//	
//	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
//	uploader.on( 'uploadSuccess', function( file ) {
//		$( '#'+file.source.ruid ).hide();
//	    $( '#'+file.id ).addClass('upload-state-done');
//	});
//	
//	// 文件上传失败，显示上传出错。
//	uploader.on( 'uploadError', function( file ) {
//	    var $li = $( '#'+file.id ),
//	        $error = $li.find('div.error');
//	
//	    // 避免重复创建
//	    if ( !$error.length ) {
//	        $error = $('<div class="error"></div>').appendTo( $li );
//	    }
//	
//	    $error.text('上传失败');
//	});
//	
//	// 完成上传完了，成功或者失败，先删除进度条。
//	uploader.on( 'uploadComplete', function( file ) {
//	    $( '#'+file.id ).find('.progress').remove();
//	});
//});
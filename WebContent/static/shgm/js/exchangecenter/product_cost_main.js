$(function() {

	// 优化后去除上面部分
	$(".box").css({
		"height" : $(document).height(),
		"width" : $(window).width()
	});
	$(window).resize(function() {
		$(".box").css({
			"height" : $(document).height(),
			"width" : $(window).width()
		});
	});
	$("#serve_list").click(function() {
		var top = $(this).offset().top;
		$(".serve_box").css({
			"top" : top
		});
		$("#server_box_bg").show();
	});
	$("#box_close").click(function() {
		$(".box").hide();
	});

	
	
	function showProvince(){
		$(".bg_click").show();
		$(".change_city_bg").show();
		$(".change_city_head button").hide();
		$(".change_city_box").css({
			"top" : "20px",
			"left" : "-95px"
		});
	}
	
	function showCity(node){
		$(".city1_box").hide();
		$(".abc_input").val("").blur();
		$(".limiter").appendTo($(node).parent());
		var proid = $(node).attr("id");
		if ($("#city" + proid)[0]) {
			$("#city" + proid).toggle().siblings().hide();
		}
		$(".top_arrow").css({
			"left" : $(node).position().left
		});
	}
	

	
	/**
	 * 给直辖市和城市增加点击事件，点击时查询商铺
	 */
	function addCityEvent(){
		$(".pro_body_bg span,.pro_city3_data .off").click(function() {
			$(".bg_click").hide();
			$(".toggle_city p").text($(this).text());
			$(this).parents(".pro_city3_list2").hide();
			$(".change_city_bg,.pro_city3_list2").hide();
			$(".ie_bg1").remove();
			// 增加设置cookie
			var cityid = $(this).attr("id");
			var cityname = $(this).text();
			if($("#shopAddress")) $("#shopAddress").html("");
			$.post(path+"/exchangecenter/product/geto2oshopbycity."+Utils.webType, 
					{ "searchDataObj.categoryId": $("#o2oCategoryId").val(), "searchDataObj.cityAddressNo": cityid,"searchDataObj.cityName":cityname },
					   function(data){
						if(data){
							var newhtml = "<select class='log_input_select' id='log_selectid'  onchange='showAddress(this)' >"+
							"<option value='0'>选择店铺</option>";
							var shopArray = eval("("+data+")");
							//||$("#pint").text()=='库存0件'
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
								$(".xq_js_div").html("");
							};
						}else{
							$("#movieSpan").html("很抱歉，暂无可选的店铺");
							$(".xq_js_div").html("");
						}
			});
			
			
			
		}).hover(function() {
			$(this).css({
				"background-color" : "#F1F4F9"
			});
		}, function() {
			$(this).css({
				"background-color" : ""
			});
		});
	}
	/**
	 * 给省份增加点击事件，
	 * 对直辖市点击后查询商铺；
	 * 对于省份点击后查询下级城市
	 */
	function addProvinceEvent(){

		//直辖市
		//addCityEvent();
		
		//省份
		$(".pro_city3_list1 a:not('.off')").click(function() {
			var self = this;
			var provinceNo = $(this).attr("id");
			var categoryId = $("#o2oCategoryId").val();
			var id = "city"+provinceNo;
			var pNode = $(".limiter");
			var node = $(".limiter #"+id);
			if(!node[0]){
				$.post(path+"/exchangecenter/product/geto2ocitybycategory."+Utils.webType, 
						{ "searchDataObj.categoryId": categoryId, "searchDataObj.provinceNo":provinceNo},
						   function(data){
							if(data){
								var shopArray = eval("("+data+")");
								var newHtml = "";
								newHtml +='<div class="pro_city3_list2 hide" id="'+id+'" style="display: none;">';
								newHtml += '<div class="top_arrow" style="left: 248px;"></div>';
								newHtml +='<div class="top_arrow top_arrow1" style="left: 248px;"></div>';
								newHtml +='<div class="pro_body_bg">';
								newHtml +='<div class="gray" style="margin:5px 0 5px 10px;"></div>';
									
								if(shopArray.length>0){
									for(var i =  0; i<shopArray.length;i++){
										var anObj = shopArray[i];
										var name = anObj.cityName;
										newHtml +='<span id="'+anObj.cityAddressNo+'">'+name+'</span>';
									}
								}else{
									newHtml+="暂无开通城市";
								}
								newHtml +="</div></div>";
								pNode.html(newHtml);
							}else{
								pNode.html("暂无开通城市");
							}
							addCityEvent();
							showCity(self);
				});
			}else{
				//曾经生成过，不再生成
				showCity(self);
			}
			
		}).hover(function() {
			$(this).css({
				"background-color" : "#F1F4F9"
			});
		}, function() {
			$(this).css({
				"background-color" : ""
			});
		});
		
	}
	$(".toggle_city .toggle_btn").click(function() {
		if($(".pro_city3_data")[0]){
			//已经生成过，就不再生成
			showProvince();
		}else{
			$.post(path+"/exchangecenter/product/geto2oprovincebycategory."+Utils.webType, 
					{ "searchDataObj.categoryId": $("#o2oCategoryId").val()},
					   function(data){
						if(data){
							var shopArray = eval("("+data+")");
							var maxCol=6;
							var newHtml = "";
							var pNode = $(".pro_city3_list1");
							var limiter = $(".limiter");
							if(shopArray.length>0){
								for(var i =  0; i<shopArray.length;i++){
									if(i%maxCol==0){
										if(i>0) newHtml+='</div>';
										newHtml +='<div class="pro_city3_data">';
									}
									var anObj = shopArray[i];
									var level = "";
									if(anObj.addressLevel-4==0) level = 'class="off"';
									var name = anObj.provinceName;
									newHtml += '<a style="overflow: hidden;" id="'+anObj.provinceNo+'" name="'+name+'" '+level+' >'+name+'</a>';
								}
								if(!limiter[0]){
									newHtml += '<div class="limiter"></div>';
								}
								newHtml+='</div>';
								pNode.html(newHtml);
							}else{
								pNode.html("暂无开通省份");
							}
							addProvinceEvent();
						}else{
							pNode.html("暂无开通省份");
						}
			});
			showProvince();
		}

	});

	$(".close_change_city").click(function() {
		$(".bg_click").hide();
		$(".pro_city3_list2").hide();
		$(".abc_input").val("").blur();
		$(".city1_box").hide();
		$(".change_city_bg").hide();
		$(".ie_bg1").remove();
	});
	// 以上为关闭按钮

	
	// 以上为省份列表（除直辖市）点击显示市
	
	// 以上为直辖市点击直接切换城市文本-------城市切换结束
	/* 城市搜索优化结束 */

});

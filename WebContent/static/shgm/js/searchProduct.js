// category click highlight
//点击最新、销量、积分等搜索
$(document).ready(
	function() { 
		$("#category li a").bind("click",function(){
			$(this).addClass("current");
			$("#category li a").not($(this)).removeClass("current");
		});
		
		// 关键字类别
		var $keyword_class = $("input[name='keywordGBK']");
		$keyword_class.keydown(
				function(event){
					// enter button
					if(event.keyCode == 13){
						//alert("enter searching...");
						search_products();
					}
				}
		); 
		
		// 默认排序
		var sortField = $("#strSortField").val();
		var orderType = $("#strOrderType").val();
		if(sortField == 'shelfDate'){
			$("#shelfDate").addClass("select").siblings().removeClass("select");
			//$('.sch_tabsul .tabsa').removeClass('asc desc');
			$(this).removeClass('asc desc');
			if(orderType == 'asc'){
				$("#shelfDate a").addClass(orderType);
				$("#shelfDate a").attr('title', '上架时间按最久排序');
			}else if(orderType == 'desc'){
				$("#shelfDate a").addClass(orderType);
				$("#shelfDate a").attr('title', '上架时间按最新排序');
			}
		}else if(sortField == 'saleCount'){
			$("#saleCount").addClass("select").siblings().removeClass("select");
			//$('.sch_tabsul .tabsa').removeClass('asc desc');
			$(this).removeClass('asc desc');
			if(orderType == 'asc'){
				$("#saleCount a").addClass(orderType);
				$("#saleCount a").attr('title', '销量从低到高排序');
			}else if(orderType == 'desc'){
				$("#saleCount a").addClass(orderType);
				$("#saleCount a").attr('title', '销量从高到低排序');
			}
		}else if(sortField == 'salePrice'){
			$("#salePrice").addClass("select").siblings().removeClass("select");
			//$('.sch_tabsul .tabsa').removeClass('asc desc');
			$(this).removeClass('asc desc');
			if(orderType == 'asc'){
				$("#salePrice a").addClass(orderType);
				$("#salePrice a").attr('title', '积分从低到高排序');
			}else if(orderType == 'desc'){
				$("#salePrice a").addClass(orderType);
				$("#salePrice a").attr('title', '积分从高到低排序');
			}
		}
		// 排序选择
		var $sortFieldClass = $("ul.sch_tabsul a");
		$sortFieldClass.bind(
			"click",
			function(){
				var orderType = $(this).hasClass('asc') ? "desc" : "asc";
				var sortField = $(this).parent("li").attr("id");
				var title = $(this).attr("title");
				//第一次点击，按默认排序
				if(title == '点击上架时间按最新排序' || title == '点击销量从高到低排序'){
					orderType = "desc";
				}else if(title == '点击积分从低到高排序'){
					orderType = "asc";
				}

				//重置原有提示
				$('#shelfDate a').attr('title','点击上架时间按最新排序');
				$('#saleCount a').attr('title','点击销量从高到低排序');
				$('#salePrice a').attr('title','点击积分从低到高排序');	
				//$('.sch_tabsul .tabsa').removeClass('asc desc');
				$('#shelfDate a').attr('class','tabsa desc');
				$('#saleCount a').attr('class','tabsa desc');
				$('#salePrice a').attr('class','tabsa asc');

				//设置新的提示
				$(this).removeClass('asc desc');
				$(this).addClass(orderType);
				$(this).parent("li").addClass("select").siblings().removeClass("select");
				var strTmp = $(this).html();
				
				if( orderType=="desc" ){
					if(sortField!='shelfDate'){
						$(this).attr('title',strTmp+'从高到低排序');
					}else{
						$(this).attr('title','上架时间按最新排序');
					}
				}else{
					if(sortField!='shelfDate'){
						$(this).attr('title',strTmp+'从低到高排序');
					}else{
						$(this).attr('title','上架时间按最久排序');
					}
				}
				

				var keyword = $("#keyword").val();
				var scoreRegion = $("#scoreRegion").val();
				var categoryPram=$("#categoryPram").val();
				var pageIndex=$("#pageIndex").val();
				var brandId = $("#brandId").val();
				var categoryId = $("#categoryId").val();
				// 排序字段和规则
				$("#strSortField").val(sortField);
				$("#strOrderType").val(orderType);
				// 兑换渠道
				var exchangeChannel = $("#exchangeChannel").val();
				if(exchangeChannel == "" || exchangeChannel == "undefined"){
					exchangeChannel = "net";
				}
				//alert("search_sort keyword:" + keyword + " category:" + categoryPram + " scoreRegion:" + scoreRegion + " sortField:" + sortField + " orderType:" + orderType);
				$.post(frontPath+"/exchangecenter/search/josonProduct."+Utils.webType,
					{keyword : keyword, categoryPram : categoryPram, scoreRegion : scoreRegion,
					strSortField : sortField, strOrderType : orderType,
					exchangeChannel : exchangeChannel, categoryId:categoryId, 
					brandId:brandId, pageIndex : pageIndex},
					function(data){
						if(!data){
							return false;
						}
						var json = jQuery.parseJSON(data);
						var content = "";
						for(var i = 1; i <=json.length; i++){
							if(i%4==0){
								content +='<li class="prolist-li">';
							}else{
								content +='<li>';
							}
							content +='<div class="product_bk"><a href="'+frontPath+json[i-1].url+'"  target="_blank"><img src='+json[i-1].pictureUrl+'  width="236"   height="236"/></a></div>';
					        content +='<div class="jf_lb">';
					        content +='<a href="'+frontPath+json[i-1].url+'" class="jf_ats" title="' +json[i-1].title + '"  target="_blank">' +json[i-1].titleStyle+' </a>';
					        content +=' <p class="font-yh fl"><span class="jf_color2 ml10">'+json[i-1].price+'</span> 积分</p>';
					        content +='<span class="sales">已兑换'+json[i-1].saleCount+'件</span>';
					        content +='</div>';
					        content +='</li>';
						}
									
						$("#searchRe").html(content);
						//console.info(content);
					}
				);
			}
		);
	}	
);

// search main function
var search_products = function() {
	var scoreRegion = ltrim($("#jffw").text());
	var keyword = ltrim($("#keywordGBK").val());
	
	if(keyword == "输入品牌或商品进行搜索" || keyword == ""){
		if(scoreRegion == "积分范围"){
			//alert("keyword:" + keyword + " priceRegion:" + priceRegion);
			var $keyword_class = $(".searchtext");
			$("#keywordGBK").val("输入品牌或商品进行搜索");
			$keyword_class.focus();
			return;
		}
		keyword = "";
	}

	// 兑换渠道
	var exchangeChannel = $("#exchangeChannel").val();
	if(exchangeChannel == "" || exchangeChannel == "undefined"){
		exchangeChannel = "net";
	}
	
	//alert("search_products keyword:" + keyword + " scoreRegion:" + scoreRegion);	
	//scoreRegion=encodeURIComponent(encodeURIComponent(scoreRegion));
	//alert("search_products keyword:" + keyword + " scoreRegion:" + scoreRegion);
	if (window.searchInNewTab) {
		var url = frontPath+'/exchangecenter/search/product.'+Utils.webType+ '?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel;
		var enUrl = encodeURI(url);
		window.open(enUrl, "_blank");
	} else {
		var url = frontPath+'/exchangecenter/search/product.'+Utils.webType+ '?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel;
		var enUrl = encodeURI(url);
		parent.window.location.href = enUrl;
	}
	/*
	if (window.searchInNewTab) {
		window.open(frontPath+'/exchangecenter/search/product?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel, "_blank");
	} else {
		parent.window.location.href = frontPath+'/exchangecenter/search/product?keyword='+keyword
			+"&scoreRegion="+scoreRegion
			+"&exchangeChannel="+exchangeChannel;
	}*/
};

//分类查询
function searchByCategory(categoryName){
	//点击确定的分类，清空文本框pageIndex原来的值，设置默认值为1，
	$("#categoryPram").val(categoryName);
	var pageIndex = 1;
	$("#pageIndex").val(pageIndex);
	var keyword = $("#keyword").val();
	var scoreRegion = $("#scoreRegion").val();
	if(keyword == "输入品牌或商品进行搜索" || keyword == ""){
//		if(scoreRegion == "积分范围"){
//			//alert("keyword:" + keyword + " priceRegion:" + priceRegion);
//			var $keyword_class = $(".searchtext");
//			$keyword_class.focus();
//			return;
//		}
		keyword = "";
	}

	// 兑换渠道
	var exchangeChannel = $("#exchangeChannel").val();
	if(exchangeChannel == "" || exchangeChannel == "undefined"){
		exchangeChannel = "net";
	}
	var sortField = $("#strSortField").val();
	var orderType = $("#strOrderType").val();
	var brandId = $("#brandId").val();
	var categoryId = $("#categoryId").val();
	//alert("searchByCategory keyword:" + keyword + " category:" + categoryName + " scoreRegion:" + scoreRegion);
	// 刷新搜索结果
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/josonProduct."+Utils.webType,
		data:{categoryPram : categoryName, scoreRegion : scoreRegion,
			keyword : keyword, exchangeChannel : exchangeChannel,
			strSortField : sortField, strOrderType : orderType,
			categoryId:categoryId, brandId:brandId, pageIndex : pageIndex},
		success:function(data){
			//alert(data);
			//console.info(data);
			if(!data){
				return false;
			}
			var json = jQuery.parseJSON(data);
			var content = "";
			for(var i = 1; i <=json.length; i++){
				if(i%4==0){
					content +='<li class="prolist-li">';
				}else{
					content +='<li>';
				}
				content +='<div class="product_bk"><a href="'+frontPath+json[i-1].url+'"  target="_blank"><img src='+json[i-1].pictureUrl+'  width="236"   height="236"/></a></div>';
		        content +='<div class="jf_lb">';
		        content +='<a href="'+frontPath+json[i-1].url+'" class="jf_ats" title="' +json[i-1].title + '"  target="_blank">' +json[i-1].titleStyle+' </a>';
		        content +=' <p class="font-yh fl"><span class="jf_color2 ml10">'+json[i-1].price+'</span> 积分</p>';
		        content +='<span class="sales">已兑换'+json[i-1].saleCount+'件</span>';
		        content +='</div>';
		        content +='</li>';
			}			
			$("#searchRe").html(content);
		}
	});
	
	//刷新分页条
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/josonPage."+Utils.webType,
		data:{categoryPram : categoryName, scoreRegion : scoreRegion, 
			keyword : keyword, exchangeChannel : exchangeChannel,
			strSortField : sortField, strOrderType : orderType,
			categoryId:categoryId, brandId:brandId, pageIndex : pageIndex},
		success:function(data){
			//console.info(data);
			if(!data){
				return false;
			}
			var json = jQuery.parseJSON(data);
			//替换分页条
			var i=json.length;
			
			var pageIndex=json[i-1].pageIndex;
			var pageCount=json[i-1].pageCount;
			var resultCount = json[i-1].resultCount;
			if(pageCount == 0){
				pageIndex = 0;
			}
			$("#pageCount").val(pageCount);
			var pageContent = '<li class="page_tip"></li>';	
			var pageButton = '';
			if(pageIndex > 1){
				pageContent += '<li><a href="javascript:toFirst();">首页</a></li>';
				pageContent += '<li><a href="javascript:toBefore();">上一页</a></li>';
				pageButton += '<input class="sch_input02 sch_input04" type="button" name="input2" value="上一页" onclick="toBefore();"/>';
			}
			else{
				pageContent += '<li><a>首页</a></li>';
				pageContent += '<li><a>上一页</a></li>';
				pageButton = '<input class="sch_input02 sch_input04" type="button" name="input2" value="上一页"/>';
			}
			if(pageIndex < pageCount){
				pageContent += '<li><a href="javascript:toNext();">下一页</a></li>';
				pageContent += '<li><a href="javascript:toLast();">末页</a></li>';
				pageButton += '<input class="sch_input02 sch_input04 " type="button" name="input2" value="下一页" onclick="toNext();"/>';
			}
			else{
				pageContent += '<li><a>下一页</a></li>';
				pageContent += '<li><a>末页</a></li>';
				pageButton += '<input class="sch_input02 sch_input04 " type="button" name="input2" value="下一页"/>';
			}
			pageContent += '<li class="page_tip">当前页数&nbsp;'+pageIndex+' &nbsp;|&nbsp;共'+pageCount+'页&nbsp;</li>';
			pageContent += '<li class="page_tip">转到&nbsp;&nbsp;</li>';
			pageContent += '<li><span class="page_tip2"><input name="toNumPage" id="toNumPage" type="text" size="6" maxlength="6" onkeypress="javascript:return goToPage(event);" /></span></li>';
			pageContent += '<li id="page_go"><a href="javascript:void(0);" onclick="javascript:toPage();">Go</a></li>';
			// 替换底栏分页按钮
			$("#pageContent").html(pageContent);
			//替换中间分页的按钮事件
			$("#pageButton").html(pageButton);
			
			//替换中间的分页
			var middlePage = '<span class="log-color-red01">'+pageIndex+'</span>/'+pageCount+'';
			$("#middlePage").html(middlePage);
			
			// 总数更新
			var contentTaltolCount = '<p><span class="sealeft2">'+ keyword + '&nbsp' + categoryName
				+ '</span>&nbsp;共找到<strong class="seatext_09">&nbsp;'+resultCount+'&nbsp;</strong>件相关商品</p>';
			$("#totalCount").html(contentTaltolCount);
		}
	});
	/*
	//替换分类栏
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/product!josonCategory",
		data:{categoryPram : categoryName, scoreRegion : scoreRegion, keyword : keyword},
		success:updateCategory(data)});
	*/
}
//积分筛选
function searchByPriceRegion(){
	// keyword
	var keyword = $("#keyword").val();
	// scoreRegion
	var minValue = $.trim($("#searchMin").val());
	var maxValue = $.trim($("#searchMax").val());
	if(isNaN(minValue) || isNaN(maxValue)){
		alert("输入积分范围必须为数值");
    	return ;
	}
	var scoreRegion = "";
	if(minValue == ""){
		if(maxValue == ""){
			return;
		}
		else{
			scoreRegion = "0～" + maxValue;
		}
	}
	else{
		if(maxValue == ""){
			scoreRegion = minValue + "以上";
		}
		else if(maxValue - minValue < 0){
			alert("积分范围输入有误");
			return;
		}
		else{
			scoreRegion = minValue + "～" + maxValue;
		}
	}
	// 更新隐藏域
	$("#scoreRegion").val(scoreRegion);
	
	if(keyword == "输入品牌或商品进行搜索" || keyword == ""){
		if(scoreRegion == "积分范围"){
			//alert("keyword:" + keyword + " priceRegion:" + priceRegion);
			var $keyword_class = $(".searchtext");
			$keyword_class.focus();
			return;
		}
		keyword = "";
	}
	
	// 更新页头
	var scoreRegionGBK = scoreRegion;
	$("#jffw").html(scoreRegionGBK);
	$("#pageIndex").val(1);//点击确定，清空文本框pageIndex原来的值，设置默认值为1，
	//alert("searchByPriceRegion keyword:" + keyword + " scoreRegion:" + scoreRegion);
	
	// 兑换渠道
	var exchangeChannel = $("#exchangeChannel").val();
	if(exchangeChannel == "" || exchangeChannel == "undefined"){
		exchangeChannel = "net";
	}
	
	if (window.searchInNewTab) {
		var url = frontPath+'/exchangecenter/search/product.'+Utils.webType+ '?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel;
		var enUrl = encodeURI(url);
		window.open(enUrl, "_blank");
	} else {
		var url = frontPath+'/exchangecenter/search/product.'+Utils.webType+ '?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel;
		var enUrl = encodeURI(url);
		parent.window.location.href = enUrl;
	}
	/*
	if (window.searchInNewTab) {
		window.open(frontPath+'/exchangecenter/search/product?keyword='+keyword
				+"&scoreRegion="+scoreRegion
				+"&exchangeChannel="+exchangeChannel, "_blank");
	} else {
		parent.window.location.href = frontPath+'/exchangecenter/search/product?keyword='+keyword
			+"&scoreRegion="+scoreRegion
			+"&exchangeChannel="+exchangeChannel;
	}*/
}
//积分筛选
function searchByPriceRegionBak(){
	// keyword
	var keyword = $("#keyword").val();
	if("输入品牌或商品进行搜索" == keyword){
		keyword = "";
	}
	// scoreRegion
	var minValue = $.trim($("#searchMin").val());
	var maxValue = $.trim($("#searchMax").val());
	if(isNaN(minValue) || isNaN(maxValue)){
		alert("输入积分范围有误");
    	return ;
	}
	var scoreRegion = "";
	if(minValue == ""){
		if(maxValue == ""){
			return;
		}
		else{
			scoreRegion = "0～" + maxValue;
		}
	}
	else{
		if(maxValue == ""){
			scoreRegion = minValue + "以上";
		}
		else{
			scoreRegion = minValue + "～" + maxValue;
		}
	}
	// 更新隐藏域
	$("#scoreRegion").val(scoreRegion);
	// 更新页头
	var scoreRegionGBK = scoreRegion;
	$("#jffw").html(scoreRegionGBK);
	$("#pageIndex").val(1);//点击确定，清空文本框pageIndex原来的值，设置默认值为1，
	//alert("searchByPriceRegion keyword:" + keyword + " scoreRegion:" + scoreRegion);
	
	// 刷新搜索结果
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/josonProduct."+Utils.webType,
		//dataType:"json",
		data:{scoreRegion : scoreRegion, keyword : keyword},
		success:function(data){
			//console.info("SearchResult:" + data);
			var json = jQuery.parseJSON(data);
			var content = "";
			for(var i = 1; i <=json.length; i++){
				if(i%4==0){
					content +='<li class="prolist-li">';
				}else{
					content +='<li>';
				}
				content +='<div class="product_bk"><a href='+frontPath+json[i-1].url+'><img src='+json[i-1].pictureUrl+'  width="236"   height="236"/></a></div>';
		        content +='<div class="jf_lb">';
		        content +='<a href='+frontPath+json[i-1].url+' class="jf_ats">' +json[i-1].title+' </a>';
		        content +=' <p class="font-yh fl"><span class="jf_color2 ml10">'+json[i-1].price+'</span> 积分</p>';
		        content +='<span class="sales">'+json[i-1].saleCount+'人兑换</span>';
		        content +='</div>';
		        content +='</li>';
			}			
			$("#searchRe").html(content);
			
			var contentTaltolCount="";//总过找到多少条数据
			//alert("keyword:" + keyword);
			contentTaltolCount ='<p><span class="sealeft2">'+ keyword + '</span>&nbsp;共找到<strong class="seatext_09">&nbsp;'+json.length+'&nbsp;</strong>件相关商品</p>';
			alert(contentTaltolCount);
			$("#totalCount").html(contentTaltolCount);
		}
	});
	
	//刷新分页条
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/josonPage."+Utils.webType,
		data:{scoreRegion : scoreRegion, keyword : keyword},
		success:function(data){
			//console.info(data);
			var json = jQuery.parseJSON(data);
			//替换分页条
			var i=json.length;
			
			var pageIndex=json[i-1].pageIndex;
			var pageCount=json[i-1].pageCount;
			if(pageCount == 0){
				pageIndex = 0;
			}
			var pageContent = '<li class="page_tip"></li>';	
			if(pageIndex > 1){
				pageContent += '<li><a href="javascript:toFirst();">首页</a></li>';
				pageContent += '<li><a href="javascript:toBefore();">上一页</a></li>';
			}
			else{
				pageContent += '<li><a>首页</a></li>';
				pageContent += '<li><a>上一页</a></li>';
			}
			if(pageIndex < pageCount){
				pageContent += '<li><a href="javascript:toNext();">下一页</a></li>';
				pageContent += '<li><a href="javascript:toLast();">末页</a></li>';
			}
			else{
				pageContent += '<li><a>下一页</a></li>';
				pageContent += '<li><a>末页</a></li>';
			}
			pageContent += '<li class="page_tip">当前页数&nbsp;'+pageIndex+' &nbsp;|&nbsp;共'+pageCount+'页&nbsp;</li>';
			pageContent += '<li class="page_tip">转到&nbsp;&nbsp;</li>';
			pageContent += '<li><span class="page_tip2"><input name="toNumPage" id="toNumPage" type="text" size="6" maxlength="6" /></span></li>';
			pageContent += '<li id="page_go"><a href="javascript:void(0);" onclick="javascript:toPage();">Go</a></li>';
			$("#pageContent").html(pageContent);
			
			//替换中间分页的按钮事件
			var pageButton = '<input class="sch_input02 sch_input03" type="button" name="input2" value="上一页" onclick="toBefore();"/>';
			pageButton += '<input class="sch_input02 sch_input04 " type="button" name="input2" value="下一页" onclick="toNext();"/>';
			$("#pageButton").html(pageButton);
			
			//替换中间的分页
			var middlePage = '<span class="log-color-red01">'+pageIndex+'</span>/'+pageCount+'';
			$("#middlePage").html(middlePage);
		}
	});
	
	//替换分类栏
	$.ajax({
		type:"POST",
		url:frontPath+"/exchangecenter/search/josonCategory."+Utils.webType,
		data:{scoreRegion : scoreRegion, keyword : keyword},
		success:function(data){
			var json = jQuery.parseJSON(data);
			var contentCategory = "";//商品分类
			for(var i = 1; i <=json.length; i++){
				contentCategory +='<li><a href="javascript:searchByCategory(\''+json[i-1].categoryName+'\');">'+json[i-1].categoryName+'<span>('+json[i-1].countCategory+')</span></a> </li>';
			}
			
			$("#category").html(contentCategory);
		}
	});
}